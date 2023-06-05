package ru.itis.semesterworkspring.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.semesterworkspring.dto.forms.TrainingForm;
import ru.itis.semesterworkspring.models.Training;
@Component
public class TrainingConverter implements Converter<TrainingForm, Training> {
    @Override
    public Training convert(TrainingForm trainingForm) {
        Training training = new Training();

        training.setStartHour(trainingForm.getStartHour());
        training.setStartMinute(trainingForm.getStartMinute());
        training.setEndHour(trainingForm.getEndHour());
        training.setEndMinute(trainingForm.getEndMinute());
        training.setCurrentGroup(trainingForm.getGroup());

        return training;
    }
}
