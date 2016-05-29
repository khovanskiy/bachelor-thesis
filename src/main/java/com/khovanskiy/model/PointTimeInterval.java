package com.khovanskiy.model;

import com.khovanskiy.util.InstantInterval;
import lombok.Data;

/**
 * @author victor
 */
@Data
public class PointTimeInterval {
    /**
     * ID транспортного узла
     */
    Ref<? extends Point> point;

    /**
     * Временной интервал
     */
    InstantInterval timeInterval;
}
