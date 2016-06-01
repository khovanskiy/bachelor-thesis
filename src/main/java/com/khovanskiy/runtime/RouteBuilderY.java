package com.khovanskiy.runtime;

import com.google.common.collect.MinMaxPriorityQueue;
import com.khovanskiy.model.Point;
import com.khovanskiy.model.Ref;
import com.khovanskiy.model.SchedulePage;
import com.khovanskiy.model.TrainRun;
import com.khovanskiy.model.Waypoint;
import com.khovanskiy.service.Repository;
import com.khovanskiy.util.MapGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author victor
 */
@Slf4j
public class RouteBuilderY {

    private Repository repository;

    public RouteBuilderY(Repository repository) {
        this.repository = repository;
    }

    private List<SchedulePage> fetchPages(Ref<? extends Point> ref, Instant from, Instant to) {
        List<SchedulePage> pages = new ArrayList<>(2);
        while (from.compareTo(to) <= 0) {
            repository.find(new SchedulePage.Id(ref.toString(), getDayOfInstant(from))).ifPresent(pages::add);
            from = from.plus(Duration.ofDays(1));
        }
        return pages;
    }

    private long getDayOfInstant(Instant instant) {
        return ZonedDateTime.ofInstant(instant, ZoneOffset.UTC).truncatedTo(ChronoUnit.DAYS).toInstant().toEpochMilli();
    }

    /**
     * Выделение хабов среди обычных точек
     */
    public void allocateHubs(List<MapGenerator.GeoPoint> geoPoints) {
        log.info("Выделение регионов...");
        Map<Ref<? extends Point>, HubInfo> mapOfApproachability = new HashMap<>();
        for (TrainRun trainRun : repository.findAll(TrainRun.class)) {
            for (int i = 0; i < trainRun.getWaypoints().size(); ++i) {
                // текущая маршрутная точка
                Waypoint curW = trainRun.getWaypoints().get(i);
                // следующая маршрутная точка

                // обновляем карту достижимости между станциями
                mapOfApproachability.computeIfAbsent(curW.getPoint(), key -> new HubInfo(0, key));
                if (i < trainRun.getWaypoints().size() - 1) {
                    Waypoint nextW = trainRun.getWaypoints().get(i + 1);
                    mapOfApproachability.compute(curW.getPoint(), (key, info) -> {
                        info.runsCount++;
                        info.neighbours.add(nextW.getPoint());
                        return info;
                    });
                }
            }
        }
        MinMaxPriorityQueue<HubInfo> candidates = MinMaxPriorityQueue.orderedBy(new Comparator<HubInfo>() {
            @Override
            public int compare(HubInfo o1, HubInfo o2) {
                return -Integer.compare(o1.runsCount, o2.runsCount);
            }
        }).maximumSize(15).create();
        for (HubInfo info : mapOfApproachability.values()) {
            if (info.isLocalMax(mapOfApproachability)) {
                candidates.add(info);
            }
        }
        candidates.forEach(System.out::println);

        log.info("Визуализация модели...");
        try (PrintWriter writer = new PrintWriter("/tmp/hubs.dot")) {
            writer.println("digraph Model {");
            writer.println("\trankdir = LR;");
            writer.println("node [style=filled, shape=circle, fillcolor=\"#ffffff\", fontcolor=\"#000000\"];");
            Map<Ref<? extends Point>, Integer> pointToId = new HashMap<>();
            final int[] nextPointId = {0};
            Function<Ref<? extends Point>, Integer> function = ref -> pointToId.compute(ref, (key, id) -> {
                if (id == null) {
                    id = nextPointId[0];
                    nextPointId[0]++;
                }
                return id;
            });
            for (Map.Entry<Ref<? extends Point>, HubInfo> entry : mapOfApproachability.entrySet()) {
                writer.print("p" + function.apply(entry.getKey()) + "[label=\"" + entry.getValue().getRunsCount() + "\"");
                if (candidates.contains(entry.getValue())) {
                    writer.print(", fillcolor=red");
                }
                MapGenerator.GeoPoint geoPoint = geoPoints.stream().filter(g -> g.getPoint().getId().equals(entry.getKey())).findFirst().get();
                writer.print(", pos=\"" + geoPoint.getX() + ", " + geoPoint.getY() + "!\"");
                writer.println("]");
                entry.getValue().getNeighbours().forEach(ref -> {
                    writer.println("p" + function.apply(entry.getKey()) + " -> p" + function.apply(ref));
                });
            }
            writer.println("}");
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Информация об выделенном хабе
     */
    @Data
    @AllArgsConstructor
    class HubInfo {
        /**
         * Количество рейсов, проходящих через него
         */
        protected int runsCount;
        /**
         * ID точки
         */
        protected final Ref<? extends Point> model;
        /**
         * Множество достижимых соседей без пересадок
         */
        protected final Set<Ref<? extends Point>> neighbours = new HashSet<>();

        public boolean isLocalMax(Map<Ref<? extends Point>, HubInfo> mapOfApproachability) {
            for (Ref<? extends Point> ref : neighbours) {
                if (mapOfApproachability.get(ref).getRunsCount() > this.getRunsCount()) {
                    return false;
                }
            }
            return true;
        }
    }
}
