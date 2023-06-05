package ru.itis.semesterworkspring.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.semesterworkspring.dto.AccountDto;
import ru.itis.semesterworkspring.dto.forms.AccountUpdateForm;
import ru.itis.semesterworkspring.dto.forms.SignUpForm;

import javax.validation.Valid;
import java.util.List;

@Tags(value = {
        @Tag(name = "Accounts")})
public interface AccountApi {
    @Operation(summary = "Get list of accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of accounts",
                    content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDto.class))
                    })
    })
    @GetMapping
    ResponseEntity<List<AccountDto>> getAccounts();

    @Operation(summary = "Add new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Added account",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDto.class))
                    })
    })
    @PutMapping
    ResponseEntity<AccountDto> addAccount(@Valid @RequestBody SignUpForm form);

    @Operation(summary = "Delete account")
    @DeleteMapping("/{account-id}")
    ResponseEntity<?> deleteAccount(@PathVariable("account-id") long id);

    @Operation(summary = "Update account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Updated account",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AccountUpdateForm.class))
                    })
    })
    @PatchMapping("/{account-id}")
    ResponseEntity<AccountDto> updateAccountRole(@PathVariable("account-id") long id, @RequestBody AccountUpdateForm form);
}
