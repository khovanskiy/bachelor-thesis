package com.khovanskiy.model;

import com.khovanskiy.util.Timeline;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author victor
 */
@Data
public class SchedulePage extends Model<SchedulePage> {
    /**
     * Расписание прибытий
     */
    Timeline<StopLight> arrivalTimeline = new Timeline<>();
    /**
     * Расписание отправлений
     */
    Timeline<StopLight> departureTimeline = new Timeline<>();

    @Data
    @AllArgsConstructor
    public static final class Id implements Ref<SchedulePage> {
        String id1;
        long id2;
    }
}
