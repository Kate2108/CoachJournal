package ru.itis.semesterworkspring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.semesterworkspring.models.Account;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "account")
public class AccountDto {
    @Schema(description = "Id of account", example = "1")
    private Long id;
    @Schema(description = "Email of account", example = "example@example.ru")
    private String email;
    @Schema(description = "Username of account", example = "username")
    private String username;
    @Schema(description = "Gender", example = "female")
    private String gender;
    @Schema(description = "Country", example = "Russia")
    private String country;

    public static AccountDto from(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .email(account.getEmail())
                .username(account.getUsername())
                .gender(account.getGender())
                .country(account.getCountry())
                .build();
    }
    public static List<AccountDto> from(List<Account> accounts){
        return accounts.stream().map(AccountDto::from).collect(Collectors.toList());
    }
}
