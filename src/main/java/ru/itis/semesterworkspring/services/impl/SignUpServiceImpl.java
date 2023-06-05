package ru.itis.semesterworkspring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.semesterworkspring.dto.AccountDto;
import ru.itis.semesterworkspring.dto.forms.SignUpForm;
import ru.itis.semesterworkspring.models.Account;
import ru.itis.semesterworkspring.repositories.AccountRepository;
import ru.itis.semesterworkspring.services.SignUpService;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private SignUpForm form;

    @Override
    public AccountDto signUp() {
        return AccountDto.from(accountRepository.save(Account.builder()
                        .username(form.getUsername())
                        .email(form.getEmail())
                        .password(passwordEncoder.encode(form.getPassword()))
                        .gender(form.getGender())
                        .role(Account.Role.USER)
                        .state(Account.State.ACTIVE)
                        .country(form.getCountry())
                        .build()));
    }
    @Override
    public void prepareForm(SignUpForm signUpForm) {
        setForm(signUpForm);
    }
    private void setForm(SignUpForm form) {
        this.form = form;
    }
}
