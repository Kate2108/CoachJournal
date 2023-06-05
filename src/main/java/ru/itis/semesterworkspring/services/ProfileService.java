package ru.itis.semesterworkspring.services;

import ru.itis.semesterworkspring.dto.forms.ProfileForm;
import ru.itis.semesterworkspring.models.Profile;

public interface ProfileService {
    Profile getProfileByAccountId(Long id);
    void save(Profile profile);
    void update(ProfileForm profileForm, long id);
    int countOfAccountsWithSameCategoryAndLowerCountOfGroupMembers(Long userId, String category);
}
