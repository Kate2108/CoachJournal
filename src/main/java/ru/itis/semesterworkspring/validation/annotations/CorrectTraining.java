package ru.itis.semesterworkspring.validation.annotations;

import ru.itis.semesterworkspring.validation.validators.AccountExistValidator;
import ru.itis.semesterworkspring.validation.validators.CorrectTrainingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CorrectTrainingValidator.class)
public @interface CorrectTraining {
    String message() default "Incorrect time of training";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
