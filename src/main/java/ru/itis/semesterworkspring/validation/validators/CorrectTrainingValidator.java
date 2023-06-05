package ru.itis.semesterworkspring.validation.validators;

import ru.itis.semesterworkspring.dto.forms.TrainingForm;
import ru.itis.semesterworkspring.validation.annotations.CorrectTraining;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectTrainingValidator implements ConstraintValidator<CorrectTraining, TrainingForm> {
    @Override
    public boolean isValid(TrainingForm form, ConstraintValidatorContext constraintValidatorContext) {
        if (form.getEndHour() < form.getStartHour()){
            return false;
        }
        return form.getEndHour() != form.getStartHour() || (form.getEndMinute() >= form.getStartMinute() && form.getEndMinute() - form.getStartMinute() >= 15);
    }
}
