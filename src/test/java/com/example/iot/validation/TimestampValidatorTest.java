package com.example.iot.validation;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimestampValidatorTest {

    private final TimestampValidator validator = new TimestampValidator();


    @Test
    void shouldPassForNullTimestamp() {
        assertTrue(validator.isValid(null, null));
    }

    @Test
    void shouldPassForValidTimestamp() {
        assertTrue(validator.isValid(LocalDateTime.now().minusMinutes(1), null));
    }

    @Test
    void shouldFailForFutureTimestamp() {
        assertFalse(validator.isValid(LocalDateTime.now().plusMinutes(1), null));
    }
}
