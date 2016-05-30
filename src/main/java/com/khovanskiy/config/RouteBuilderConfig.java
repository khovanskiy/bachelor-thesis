package com.khovanskiy.config;

import lombok.Data;

/**
 * @author victor
 */
@Data
public class RouteBuilderConfig {
    private int maxTransfersCount;
    private int minTransferTime;
    private int maxTransferTime;
    private int maxCacheSize;
    private int maxNumberOfResult;
}
