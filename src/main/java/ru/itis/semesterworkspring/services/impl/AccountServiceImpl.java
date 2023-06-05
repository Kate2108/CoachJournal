package ru.itis.semesterworkspring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.semesterworkspring.dto.AccountDto;
import ru.itis.semesterworkspring.dto.forms.AccountUpdateForm;
import ru.itis.semesterworkspring.repositories.AccountRepository;
import ru.itis.semesterworkspring.services.AccountService;
import ru.itis.semesterworkspring.models.Account;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    @Override
    public boolean exists(String email) {
        return accountRepository.findByEmail(email).isPresent();
    }

    @Override
    public Account findAccountById(Long id){
        return accountRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Override
    public List<AccountDto> getAll() {
        return AccountDto.from(accountRepository.findAll());
    }

    @Override
    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }
    @Override
    @Transactional
    public AccountDto changeRole(Long id, AccountUpdateForm form){
        Account account = findAccountById(id);
        if(form.getRole().equals("ADMIN")){
            account.setRole(Account.Role.ADMIN);
        }
        else if(form.getRole().equals("USER")){
            account.setRole(Account.Role.USER);
        }
        return AccountDto.from(account);
    }
}
