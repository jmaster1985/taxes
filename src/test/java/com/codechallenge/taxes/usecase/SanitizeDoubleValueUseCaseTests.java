package com.codechallenge.taxes.usecase;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SanitizeDoubleValueUseCaseTests {

    @Test
    public void sanitizeDoubleValueUseCaseTest() {
        assertEquals(2.00, SanitizeDoubleValueUseCase.run(1.99999999998));
        assertEquals(2.00, SanitizeDoubleValueUseCase.run(2.00000000001));
    }
}
