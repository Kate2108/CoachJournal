package ru.itis.semesterworkspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.semesterworkspring.models.Day;

public interface DayRepository extends JpaRepository<Day, Long> {
    Day findDayByName(String name);
}
