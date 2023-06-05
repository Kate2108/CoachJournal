package ru.itis.semesterworkspring.services;

import ru.itis.semesterworkspring.dto.AccountDto;
import ru.itis.semesterworkspring.dto.forms.SignUpForm;

public interface SignUpService {
    AccountDto signUp();
    void prepareForm(SignUpForm signUpForm);
}
