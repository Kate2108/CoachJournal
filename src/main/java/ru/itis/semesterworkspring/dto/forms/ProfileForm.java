package ru.itis.semesterworkspring.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileForm {
    @NotBlank
    private String category;
    @Min(value = 0, message = "work experience cannot be less than 0")
    @Max(value = 60, message = "work experience cannot be more than 60")
    @NotNull
    private int experience;
    @NotBlank
    private String sportClass;
}
