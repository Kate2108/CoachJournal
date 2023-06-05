package ru.itis.semesterworkspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.semesterworkspring.models.Training;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    @Query(nativeQuery = true, value = "select * from training as tr " +
            "join days as d on tr.day_id = d.id where account_id = :id " +
            "order by tr.day_id, tr.start_hour, tr.start_minute")
    List<Training> findAllByAccountId(Long id);

    @Query(nativeQuery = true, value = "select * from training where account_id = :id " +
            "and current_group = :group and day_id = (select id from days where name = :day)")
    Training findTrainingByAccountIdAndDay(Long id, String day, String group);

    @Query(nativeQuery = true, value ="select count(*) as total_accounts from " +
            "(select a.id, p.category, COUNT(t.id) AS total_trainings " +
            "from account a inner join profile p on a.id = p.account_id " +
            "left join training t on a.id = t.account_id " +
            "where p.category = :category  group by a.id, p.category) " +
            "as subquery where subquery.total_trainings < " +
            "(select count(t.id) AS total_trainings " +
            "from account a left join training t on a.id = t.account_id " +
            "where a.id = :id)")
    int countOfAccountsWithSameCategoryAndLowerCountOfTrainings(Long id, String category);
}
