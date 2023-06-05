package ru.itis.semesterworkspring.dto.forms;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInForm {
    @NotBlank(message = "Enter the email")
    private String email;
    @NotBlank(message = "Enter the password")
    private String password;
}
