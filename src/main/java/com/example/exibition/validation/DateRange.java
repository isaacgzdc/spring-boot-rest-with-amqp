package com.example.exibition.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRange {
	String fromDate();
	String toDate();
	String message() default "FROM date must be equals or before TO date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
