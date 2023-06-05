package ru.itis.semesterworkspring.models;

import javax.persistence.*;
import lombok.*;
import ru.itis.semesterworkspring.dto.AchievementDto;

import java.util.List;

@ToString(exclude = {"accounts"})
@EqualsAndHashCode(exclude = {"accounts"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "achievements")
@NamedNativeQuery(name = "Achievement.findAllByAccountsId",
        query = "select * from account_achievements " +
                "join achievements a on account_achievements.ach_id = a.id " +
                "where account_id = :id",
        resultSetMapping = "Mapping.AchievementDto")
@NamedNativeQuery(name = "Achievement.findByAccountId",
        query = "select * from account_achievements " +
                "join achievements a on account_achievements.ach_id = a.id " +
                "where account_id = :id and ach_id = :achId",
        resultSetMapping = "Mapping.AchievementDto")
@SqlResultSetMapping(name = "Mapping.AchievementDto",
        classes = @ConstructorResult(targetClass = AchievementDto.class,
                columns = {@ColumnResult(name = "account_id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "description"),
                        @ColumnResult(name = "current_value"),
                        @ColumnResult(name = "rValue")}))
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "rvalue", nullable = false)
    private int requiredValue;
    @Column(nullable = false)
    private String description;
    @ManyToMany(mappedBy = "accountAchievements")
    private List<Account> accounts;
}
