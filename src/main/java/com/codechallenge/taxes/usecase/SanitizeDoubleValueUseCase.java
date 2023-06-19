package com.codechallenge.taxes.usecase;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SanitizeDoubleValueUseCase {
    public static Double run(Double value) {
        BigDecimal valueAsBigDecimal = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        return valueAsBigDecimal.doubleValue();
    }
}
