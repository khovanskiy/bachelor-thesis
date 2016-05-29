package com.khovanskiy.model;

import java.util.function.Predicate;

/**
 * Абстрактный фильтр маршрутов
 *
 * @author victor
 */
public interface RouteBuilderFilter<S> extends Predicate<S> {
    int getMaxTransfersCount();

    int getRequiredQuantity();
}
