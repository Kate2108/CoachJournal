package ru.itis.semesterworkspring.services;

import ru.itis.semesterworkspring.dto.forms.TrainingForm;
import ru.itis.semesterworkspring.models.Training;

import java.util.List;

public interface TrainingService {
    List<Training> findAllAccountTrainings(Long id);
    boolean addTraining(TrainingForm form, Long id);
    boolean deleteTraining(Long id, String day, String group);
    int countOfAccountsWithSameCategoryAndLowerCountOfTrainings(Long id, String category);
}
