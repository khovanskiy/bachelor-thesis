package com.khovanskiy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author victor
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainConfig extends RailwayConfig<TrainConfig> {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Id implements Ref<TrainConfig> {
        String id;
    }
}
