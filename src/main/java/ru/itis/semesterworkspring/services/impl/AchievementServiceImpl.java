package ru.itis.semesterworkspring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.semesterworkspring.dto.AchievementDto;
import ru.itis.semesterworkspring.models.Account;
import ru.itis.semesterworkspring.models.Achievement;
import ru.itis.semesterworkspring.repositories.AchievementRepository;
import ru.itis.semesterworkspring.services.AccountService;
import ru.itis.semesterworkspring.services.AchievementService;


import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository achievementRepository;
    private final AccountService accountService;
    @Transactional
    @Override
    public void makeBaseListOfAchievements(Long id){
        List<Achievement> list = achievementRepository.findAll();
        Account account = accountService.findAccountById(id);
        account.setAccountAchievements(list);
    }
    @Override
    public List<AchievementDto> getAccountAchievements(Long id){
        return achievementRepository.findAllByAccountsId(id);
    }
    @Override
    public AchievementDto getAchievement(Long accountId, Long achId){
        return achievementRepository.findByAccountId(accountId, achId);
    }
    @Override
    @Transactional
    public int updateScore(Long accountId, Long achId) {
        return achievementRepository.updateScore(accountId, achId);
    }
}
