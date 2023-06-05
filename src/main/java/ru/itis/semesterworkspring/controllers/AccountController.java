package ru.itis.semesterworkspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.semesterworkspring.controllers.api.AccountApi;
import ru.itis.semesterworkspring.dto.AccountDto;
import ru.itis.semesterworkspring.dto.forms.AccountUpdateForm;
import ru.itis.semesterworkspring.dto.forms.SignUpForm;
import ru.itis.semesterworkspring.services.AccountService;
import ru.itis.semesterworkspring.services.SignUpService;

import javax.validation.Valid;
import java.util.List;

//http://localhost:80/swagger-ui.html
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AccountController implements AccountApi {
    private final SignUpService signUpService;
    private final AccountService accountService;

    @Override
    public ResponseEntity<AccountDto> addAccount(@Valid @RequestBody SignUpForm form) {
        signUpService.prepareForm(form);
        return new ResponseEntity<>(signUpService.signUp(), HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.ok(accountService.getAll());
    }
    @Override
    public ResponseEntity<?> deleteAccount(@PathVariable("account-id") long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.accepted().build();
    }
    @Override
    public ResponseEntity<AccountDto> updateAccountRole(@PathVariable("account-id") long id, @RequestBody AccountUpdateForm form){
        return new ResponseEntity<>(accountService.changeRole(id, form), HttpStatus.ACCEPTED);
    }
}
