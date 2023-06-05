package ru.itis.semesterworkspring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.semesterworkspring.converters.TrainingConverter;
import ru.itis.semesterworkspring.dto.forms.TrainingForm;
import ru.itis.semesterworkspring.models.Account;
import ru.itis.semesterworkspring.models.Day;
import ru.itis.semesterworkspring.models.Training;
import ru.itis.semesterworkspring.repositories.DayRepository;
import ru.itis.semesterworkspring.repositories.TrainingRepository;
import ru.itis.semesterworkspring.services.TrainingService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainingConverter trainingConverter;
    private final DayRepository dayRepository;
    @Override
    public List<Training> findAllAccountTrainings(Long id) {
        return trainingRepository.findAllByAccountId(id);
    }
    @Override
    public boolean addTraining(TrainingForm form, Long id) {
        Training training = trainingConverter.convert(form);
        if (checkGroupDayTraining(id, form.getDay(), form.getGroup()) == null){
            Day day = dayRepository.findDayByName(form.getDay());
            training.setDay(day);
            training.setAccount(Account.builder().id(id).build());
            trainingRepository.save(training);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTraining(Long id, String day, String group) {
        Training training = checkGroupDayTraining(id, day, group);
        if (training != null){
            trainingRepository.delete(training);
            return true;
        }
        return false;
    }

    @Override
    public int countOfAccountsWithSameCategoryAndLowerCountOfTrainings(Long id, String category) {
        return trainingRepository.countOfAccountsWithSameCategoryAndLowerCountOfTrainings(id, category);
    }

    private Training checkGroupDayTraining(Long id, String day, String group) {
        return trainingRepository.findTrainingByAccountIdAndDay(id, day, group);
    }
}
