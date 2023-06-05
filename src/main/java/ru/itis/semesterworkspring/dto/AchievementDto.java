package ru.itis.semesterworkspring.dto;

import lombok.Data;
@Data
public class AchievementDto {
    public AchievementDto(int accId, String name, String description, int currentValue, int requiredValue) {
        this.accId = accId;
        this.name = name;
        this.description = description;
        this.currentValue = currentValue;
        this.requiredValue = requiredValue;
    }
    private long accId;
    private String name;
    private String description;
    private int currentValue;
    private int requiredValue;
}
