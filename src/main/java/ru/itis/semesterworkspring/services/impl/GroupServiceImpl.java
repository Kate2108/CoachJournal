package ru.itis.semesterworkspring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.semesterworkspring.models.Group;
import ru.itis.semesterworkspring.repositories.GroupRepository;
import ru.itis.semesterworkspring.services.GroupService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    @Override
    public List<Group> findAllAccountGroups(Long id) {
        return groupRepository.findAll(id);
    }
    @Override
    public void addGroup(Group group) {
        groupRepository.saveGroup(group);
    }
    @Override
    public Group findGroupByName(Long id, String name) {
        return groupRepository.findGroupByName(id, name);
    }
    @Override
    public void deleteGroup(Long id, String name) {
        groupRepository.deleteGroup(id, name);
    }
    @Override
    public void updateGroup(Long id, String name, int newCount) {
        groupRepository.updateGroup(id, name, newCount);
    }
}
