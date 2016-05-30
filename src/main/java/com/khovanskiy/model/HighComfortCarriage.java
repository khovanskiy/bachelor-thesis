package com.khovanskiy.model;

import com.khovanskiy.util.Idx;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Вагон повышенной комфортности (СВ)
 *
 * @author victor
 */
public class HighComfortCarriage extends TrainCarriage<HighComfortCarriage> {
    @Getter
    @EqualsAndHashCode
    public static class Coupe {

    }

    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Seat extends Carriage.Seat<Seat> {


        public Seat(Idx seatIdx, String seatNum,
                    Idx<? extends Carriage> carriageIdx, String carriageNum) {
            this.idx = seatIdx;
            this.number = seatNum;
            this.carriageIdx = carriageIdx;
            this.carriageNumber = carriageNum;
        }
    }
}
