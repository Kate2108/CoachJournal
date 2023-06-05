package ru.itis.semesterworkspring.models;

import lombok.*;
import javax.persistence.*;

@ToString(exclude = {"account"})
@EqualsAndHashCode(exclude = {"account"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    @Column(columnDefinition = "varchar(255) default 'no category'")
    private String category;
    @Column(columnDefinition = "integer default 0")
    private int experience;
    @Column(columnDefinition = "varchar(255) default 'no class'")
    private String sportClass;
}
