package com.codechallenge.taxes.usecase;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class RoundUpCalculatedTaxUseCase {

    /**
     * Rounds up the calculated tax amount to the nearest 0.05
     *
     * @param taxAmount a double by the product of net price and tax rate
     * @return rounded tax amount
     */
    public Double run(Double taxAmount) {
        String doubleAsString = String.valueOf(taxAmount);
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(doubleAsString));
        int intValue = bigDecimal.intValue();
        String decimalPartAsString = bigDecimal.subtract(new BigDecimal(intValue)).toPlainString().substring(2);

        Double result = taxAmount;

        if (decimalPartAsString.length() <= 2) {
            return result;
        }

        String firstDigit = decimalPartAsString.substring(0,1);
        String secondDigit = decimalPartAsString.substring(1,2);

        Integer secondDigitAsInt = Integer.valueOf(secondDigit);

        if (secondDigitAsInt >= 5) {
            result = intValue + Double.valueOf("0." + firstDigit) + 0.10;
        } else {
            result = intValue + Double.valueOf("0." + firstDigit) + 0.05;
        }

        BigDecimal resultAsBigDecimal = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
        return resultAsBigDecimal.doubleValue();
    }

}
