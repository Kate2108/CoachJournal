package ru.itis.semesterworkspring.models;

import javax.persistence.*;
import lombok.*;

@ToString(exclude = {"account"})
@EqualsAndHashCode(exclude = {"account"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "integer default 0")
    private int countOfMembers;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}
