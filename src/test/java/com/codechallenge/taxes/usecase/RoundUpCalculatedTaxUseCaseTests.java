package com.codechallenge.taxes.usecase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class RoundUpCalculatedTaxUseCaseTests {
    private final RoundUpCalculatedTaxUseCase roundUpCalculatedTaxUseCase;

    @Autowired
    public RoundUpCalculatedTaxUseCaseTests(RoundUpCalculatedTaxUseCase roundUpCalculatedTaxUseCase) {
        this.roundUpCalculatedTaxUseCase = roundUpCalculatedTaxUseCase;
    }

    @Test
    public void roundUpCalculatedTaxTest() {
        assertEquals(7.15, roundUpCalculatedTaxUseCase.run(7.125));
        assertEquals(1.5, roundUpCalculatedTaxUseCase.run(1.499));
        assertEquals(4.2, roundUpCalculatedTaxUseCase.run(4.1985));
        assertEquals(1.90, roundUpCalculatedTaxUseCase.run(1.899));
        assertEquals(0.60, roundUpCalculatedTaxUseCase.run(0.5625));
        assertEquals(1.99, roundUpCalculatedTaxUseCase.run(1.99));
        assertEquals(0.5, roundUpCalculatedTaxUseCase.run(0.5));
        assertEquals(2.0, roundUpCalculatedTaxUseCase.run(2.0));
    }
}
