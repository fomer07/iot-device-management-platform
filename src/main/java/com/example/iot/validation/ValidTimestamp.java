package com.example.iot.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TimestampValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
//TODO what is @interface
public @interface ValidTimestamp {
    String message() default "Invalid timestamp";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
