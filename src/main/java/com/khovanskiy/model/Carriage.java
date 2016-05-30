package com.khovanskiy.model;

import com.khovanskiy.util.Idx;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author victor
 */
public abstract class Carriage<C extends Carriage<C>> {
    protected Idx<C> idx;
    /**
     * Тип основной
     */
    protected String carriageNumber;
    /**
     * Основной тип
     */
    protected CarriageType mainType;

    public interface Type {

    }

    @Getter
    @EqualsAndHashCode
    public static abstract class Seat<S extends Seat<S>> {
        String carriageNumber;
    }
}
