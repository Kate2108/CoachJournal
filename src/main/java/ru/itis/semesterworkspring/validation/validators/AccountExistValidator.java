package ru.itis.semesterworkspring.validation.validators;

import lombok.RequiredArgsConstructor;
import ru.itis.semesterworkspring.dto.forms.SignUpForm;
import ru.itis.semesterworkspring.services.AccountService;
import ru.itis.semesterworkspring.validation.annotations.AccountExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class AccountExistValidator implements ConstraintValidator<AccountExist, SignUpForm> {
    private final AccountService accountService;

    @Override
    public boolean isValid(SignUpForm signUpForm, ConstraintValidatorContext constraintValidatorContext) {
        return !accountService.exists(signUpForm.getEmail());
    }
}
