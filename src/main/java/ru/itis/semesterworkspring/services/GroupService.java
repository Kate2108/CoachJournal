package ru.itis.semesterworkspring.services;

import ru.itis.semesterworkspring.models.Group;

import java.util.List;

public interface GroupService {
    List<Group> findAllAccountGroups(Long id);
    void addGroup(Group group);
    Group findGroupByName(Long id, String name);
    void deleteGroup(Long id, String name);
    void updateGroup(Long id, String name, int newCount);
}
