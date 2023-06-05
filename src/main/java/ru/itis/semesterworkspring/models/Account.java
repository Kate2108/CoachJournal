package ru.itis.semesterworkspring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import ru.itis.semesterworkspring.dto.AccountDto;

import javax.persistence.*;
import java.util.List;

@ToString(exclude = {"profile", "groups", "trainings", "accountAchievements"})
@EqualsAndHashCode(exclude = {"profile", "groups", "trainings", "accountAchievements"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
@SQLDelete(sql = "update account set state = 'DELETED' where id = ?")
@Where(clause = "state != 'DELETED'")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String country;
    @Enumerated(value = EnumType.STRING)
    private State state;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    public enum State {
        ACTIVE,
        DELETED
    }
    public enum Role {
        USER, ADMIN
    }
    @JsonIgnore
    @OneToOne(mappedBy = "account")
    private Profile profile;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Group> groups;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Training> trainings;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable( name = "account_achievements",
                joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "ach_id", referencedColumnName = "id")
    )
    private List<Achievement> accountAchievements;

    public boolean isActive() {
        return this.state == State.ACTIVE;
    }
}
