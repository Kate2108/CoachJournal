package ru.itis.semesterworkspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.itis.semesterworkspring.dto.AchievementDto;
import ru.itis.semesterworkspring.models.Achievement;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    @Query(nativeQuery = true)
    List<AchievementDto> findAllByAccountsId(Long id);
    List<Achievement> findAll();
    @Query(nativeQuery = true)
    AchievementDto findByAccountId(Long id, Long achId);
    @Modifying
    @Query(nativeQuery = true, value = "update account_achievements set current_value = current_value + 1 " +
            "where account_id = :accountId and ach_id = :achId")
    int updateScore(Long accountId, Long achId);
}
