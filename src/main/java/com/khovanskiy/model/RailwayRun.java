package com.khovanskiy.model;

import java.util.List;

/**
 * @author victor
 */
public abstract class RailwayRun<B extends TransportRun<B, RailwayWaypoint, StationPoint>>
        extends TransportRun<B, RailwayWaypoint, StationPoint> {

    public void fillRailwayRun(RailwayRun run,
                               Ref<RailwayCarrier> carrierId,
                               int saleDepth,
                               List<? extends Carriage> carriages) {

        run.carrierId = carrierId;
        run.saleDepth = saleDepth;
        run.carriages = carriages;
    }
}
