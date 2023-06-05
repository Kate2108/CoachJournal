package ru.itis.semesterworkspring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.semesterworkspring.dto.forms.ProfileForm;
import ru.itis.semesterworkspring.models.Profile;
import ru.itis.semesterworkspring.repositories.ProfileRepository;
import ru.itis.semesterworkspring.services.ProfileService;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    @Override
    public Profile getProfileByAccountId(Long id) {
        return profileRepository.findProfileByAccountId(id);
    }
    @Override
    public void save(Profile profile) {
        profile.setCategory("Second");
        profile.setExperience(0);
        profile.setSportClass("Candidate for master of Sports");
        profileRepository.save(profile);
    }

    @Override
    @Transactional
    public void update(ProfileForm profileForm, long id) {
        Profile profile = profileRepository.findProfileById(id);

        profile.setCategory(profileForm.getCategory());
        profile.setSportClass(profileForm.getSportClass());
        profile.setExperience(profileForm.getExperience());
    }
    @Override
    public int countOfAccountsWithSameCategoryAndLowerCountOfGroupMembers(Long id, String category){
        return profileRepository.countOfAccountsWithSameCategoryAndLowerCountOfGroupMembers(id, category);
    }
}
