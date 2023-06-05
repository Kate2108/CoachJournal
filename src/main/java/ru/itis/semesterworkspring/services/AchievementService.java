package ru.itis.semesterworkspring.services;

import ru.itis.semesterworkspring.dto.AchievementDto;

import java.util.List;

public interface AchievementService {
    void makeBaseListOfAchievements(Long id);
    List<AchievementDto> getAccountAchievements(Long id);
    AchievementDto getAchievement(Long accountId, Long achId);
    int updateScore(Long accountId, Long achId);
}
