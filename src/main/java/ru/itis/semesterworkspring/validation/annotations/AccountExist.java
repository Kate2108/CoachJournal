package ru.itis.semesterworkspring.validation.annotations;

import ru.itis.semesterworkspring.validation.validators.AccountExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountExistValidator.class)
public @interface AccountExist {
    String message() default "Account with that email already exists";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
