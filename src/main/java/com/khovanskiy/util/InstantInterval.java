package com.khovanskiy.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.time.Instant;

/**
 * Интервал между двумя точками во времени
 *
 * @author victor
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Wither
public class InstantInterval {
    /**
     * Дата и время начала
     */
    Instant since;

    /**
     * Дата и время конца
     */
    Instant till;
}
