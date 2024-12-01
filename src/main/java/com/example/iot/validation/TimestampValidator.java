package com.example.iot.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class TimestampValidator implements ConstraintValidator<ValidTimestamp, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Allow null (handled separately)
        }
        return !value.isAfter(LocalDateTime.now()); // Reject future timestamps
    }
}
