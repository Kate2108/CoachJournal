package ru.itis.semesterworkspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.semesterworkspring.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findProfileByAccountId(Long id);
    Profile findProfileById(Long id);
    @Query(nativeQuery = true, value = "select count(p.account_id) from profile p " +
            "inner join account u ON p.account_id = u.id " +
            "inner join (" +
            "select account_id, sum(count_of_members) as total_members from groups " +
            "group by account_id) g on p.account_id = g.account_id " +
            "where p.category = :category " +
            "and g.total_members < (select sum(count_of_members) from groups where account_id = :id group by p.category)")
    int countOfAccountsWithSameCategoryAndLowerCountOfGroupMembers(Long id, String category);

}
