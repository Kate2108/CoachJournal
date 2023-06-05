package ru.itis.semesterworkspring.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.semesterworkspring.validation.annotations.AccountExist;
import ru.itis.semesterworkspring.validation.annotations.MatchingFields;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MatchingFields(fields = {"password", "repeatPassword"}, message = "Passwords do not match. Please try again.")
@AccountExist
public class SignUpForm {

    @NotBlank(message = "Username can't be empty")
    @Size(min = 3, max = 12, message = "Length of the username should be more than {min} and less than {max}")
    @Pattern(regexp="[a-zA-Zа-яА-Я0-9_.\\-]+", message = "Incorrect username")
    private String username;

    @NotBlank(message = "Gender can't be empty")
    private String gender;

    @NotBlank(message = "Country can't be empty")
    private String country;

    @NotBlank(message = "Email can't be empty")
    @Pattern(regexp="[A-Za-z0-9_+\\-.]+@[A-Za-z0-9]+\\.[A-Za-z]{2,6}", message = "Incorrect email")
    private String email;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 4, max = 16, message = "Length of the password should be more than {min} and less than {max}")
    private String password;

    @NotBlank(message = "Password can't be empty")
    @Size(min = 4, max = 16, message = "Length of the password should be more than {min} and less than {max}")
    private String repeatPassword;
}
