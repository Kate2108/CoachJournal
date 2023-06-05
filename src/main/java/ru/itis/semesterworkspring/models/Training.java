package ru.itis.semesterworkspring.models;

import javax.persistence.*;
import lombok.*;

@ToString(exclude = {"account", "day"})
@EqualsAndHashCode(exclude = {"account", "day"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "day_id", referencedColumnName = "id")
    private Day day;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private String currentGroup;
}
