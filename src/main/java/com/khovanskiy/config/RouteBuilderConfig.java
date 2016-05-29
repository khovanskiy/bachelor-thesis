package com.khovanskiy.config;

import lombok.Getter;

/**
 * @author victor
 */
@Getter
public class RouteBuilderConfig {
    private int maxTransfersCount;
    private int minTransferTime;
    private int maxTransferTime;
    private int maxCacheSize;
    private int maxNumberOfResult;
}
