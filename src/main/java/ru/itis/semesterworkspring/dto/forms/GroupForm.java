package ru.itis.semesterworkspring.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupForm {
    private String name;
    private String deleteName;
    private String updateName;
    private int countOfMembers;
    private int newCountOfMembers;
}
