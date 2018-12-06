package com.jaytech.springbootjpa;

import com.jaytech.springbootjpa.controllers.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Phone {
    String message() default "you should input a valid phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean onlyNumber() default false;
}
