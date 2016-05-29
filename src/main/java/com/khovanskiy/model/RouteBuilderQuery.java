package com.khovanskiy.model;

import lombok.Data;
import lombok.NonNull;

import java.util.function.Predicate;

/**
 * @author victor
 */
@Data
public class RouteBuilderQuery<Q extends RouteBuilderQuery<Q, F, S>, F extends Predicate<S>, S> {
    /**
     * Отправление
     */
    protected PointTimeInterval departure;

    @NonNull
    /**
     * Прибытие
     */
    protected PointTimeInterval arrival;

    @NonNull
    /**
     * Требования к маршрутам
     */
    protected F filter;

    @NonNull
    /**
     * Порядок сортировки результатов поиска
     */
    protected ResultPresentation resultPresentation;

    /**
     * Номер запроса
     */
    public long requestId;
}
