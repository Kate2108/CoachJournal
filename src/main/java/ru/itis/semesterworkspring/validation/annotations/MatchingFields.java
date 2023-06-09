package ru.itis.semesterworkspring.validation.annotations;

import ru.itis.semesterworkspring.validation.validators.MatchingFieldsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatchingFieldsValidator.class)
public @interface MatchingFields {
    String[] fields() default {};
    String message() default "Fields do not match";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
