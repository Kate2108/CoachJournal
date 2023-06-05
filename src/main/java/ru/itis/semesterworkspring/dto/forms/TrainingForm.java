package ru.itis.semesterworkspring.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.semesterworkspring.validation.annotations.CorrectTraining;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@CorrectTraining
public class TrainingForm {
    private String day;
    private String deleteDay;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private String group;
    private String deleteGroup;
}
