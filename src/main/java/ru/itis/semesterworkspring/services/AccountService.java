package ru.itis.semesterworkspring.services;

import ru.itis.semesterworkspring.dto.AccountDto;
import ru.itis.semesterworkspring.dto.forms.AccountUpdateForm;
import ru.itis.semesterworkspring.models.Account;

import java.util.List;

public interface AccountService {
    boolean exists(String email);
    Account findAccountById(Long id);
    List<AccountDto> getAll();
    void deleteAccount(Long id);
    AccountDto changeRole(Long id, AccountUpdateForm form);
}
