package com.khovanskiy.model;

import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

/**
 * Маршрутная точка
 *
 * @author victor
 */
@Getter
public class Waypoint<W extends Waypoint<W, P>, P extends Point<P>> {
    /**
     * ID станции
     */
    private Ref<P> point;
    /**
     * Время прибытия
     */
    private Instant arrival;
    /**
     * Время отправления
     */
    private Instant departure;

    public boolean isNullStop() {
        return Objects.equals(arrival, departure);
    }
}
