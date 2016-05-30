package com.khovanskiy.util;

import com.khovanskiy.model.SeatAttribute;
import com.khovanskiy.model.TrainBrand;
import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

/**
 * @author victor
 */
@Data
public class MapConfiguration {

    /**
     * Пространственный разброс по X
     */
    private Pair<Integer, Integer> spreadX = Pair.of(0, 500);
    /**
     * Пространственный разброс по Y
     */
    private Pair<Integer, Integer> spreadY;
    /**
     * Максимальное расстояние между соседними станциями
     */
    private int maxNeighboursRadius;
    /**
     * Максимальное количество соседних станций
     */
    private int maxNeighboursCount;
    /**
     * Количество точек (станции, аэропорты и т.д.)
     */
    private int pointsCount;
    /**
     * Количество хабов
     */
    private int hubsCount;
    /**
     * Максимальное расстояние от центра хаба
     */
    private int maxHubsRadius;
    /**
     * Разброс количества станций, которые могут быть в хабе
     */
    private Pair<Integer, Integer> hubSizeSpread;
    /**
     * Количество поездов
     */
    private int maxTrainsCount;
    /**
     * Количество уникальных маршрутов
     */
    private int maxRunsCount;
    /**
     * Разброс количества точек, через которые проходит маршрут
     */
    private Pair<Integer, Integer> pointsInRunSpread;
    /**
     * Интервал времени, в пределах которого будут дублироваться уникальные маршруты со смещением по времени
     */
    private Pair<Integer, Integer> trainWaitingSpread;
    private InstantInterval timeInterval = new InstantInterval();
    /**
     * Количество потоков
     */
    private int threadsCount;
    /**
     * Количество запросов (удачных)
     */
    private int queriesCount;
    /**
     * Бренды поездов.
     */
    private List<TrainBrand> trainBrands;
    /**
     * Названия категорий поездов.
     */
    private List<String> trainCategoryNames;
    /**
     * Названия железнодорожных перевозчиков.
     */
    private List<String> railwayCarrierNames;
    /**
     * Разброс количества вагонов.
     */
    private Pair<Integer, Integer> carriagesSpread;
    /**
     * Разброс количества купе.
     */
    private Pair<Integer, Integer> coupesSpread;
    /**
     * Разброс количества мест.
     */
    private Pair<Integer, Integer> seatsSpread;
    /**
     * Типы вагонов.
     */
    private SeatAttribute.CarriageType[] carriageTypes;
    /**
     * Подмененное текущее время.
     */
    private Instant fakeNow;
    /**
     * Seed графа для одинакового построения
     */
    private long graphSeed;

    public static MapConfiguration getDefaultConfiguration() {
        MapConfiguration config = new MapConfiguration();
        config.setSpreadX(Pair.of(0, 500));
        config.setSpreadY(Pair.of(0, 500));
        config.setMaxNeighboursRadius(1000);
        config.setMaxNeighboursCount(15);
        config.setPointsCount(50);
        config.setHubsCount(0);
        config.setMaxHubsRadius(100);
        config.setHubSizeSpread(Pair.of(2, 5));
        config.setMaxTrainsCount(500);
        config.setMaxRunsCount(500);
        config.setPointsInRunSpread(Pair.of(5, 15));
        config.setTrainWaitingSpread(Pair.of(0, 5));
        config.setThreadsCount(1);
        config.setQueriesCount(1000);
        config.setTrainBrands(Arrays.asList(new TrainBrand("B1"),
                new TrainBrand("B2"), new TrainBrand("B3")));
        config.setTrainCategoryNames(Arrays.asList("C1", "C2", "C3"));
        config.setRailwayCarrierNames(Arrays.asList("RA", "RB", "RC"));
        config.setCarriagesSpread(Pair.of(3, 5));
        config.setCoupesSpread(Pair.of(5, 10));
        config.setSeatsSpread(Pair.of(0, 5));
        config.setCarriageTypes(SeatAttribute.CarriageType.values());
        config.setFakeNow(LocalDate.of(2016, 2, 2).atStartOfDay().toInstant(ZoneOffset.UTC));
        config.setGraphSeed(55551);
        return config;
    }
}

