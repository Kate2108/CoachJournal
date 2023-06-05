package ru.itis.semesterworkspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.semesterworkspring.dto.AchievementDto;
import ru.itis.semesterworkspring.dto.forms.ProfileForm;
import ru.itis.semesterworkspring.security.details.UserDetailsImpl;
import ru.itis.semesterworkspring.services.AchievementService;
import ru.itis.semesterworkspring.services.ProfileService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile/edit")
public class ProfileEditController {
    @Autowired
    private Environment environment;
    private final ProfileService profileService;
    private final AchievementService achievementService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getProfileEditPage(Model model) {
        model.addAttribute("profileForm", new ProfileForm());
        return "profile-edit";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public String edit(@AuthenticationPrincipal UserDetailsImpl details,
                       @Valid ProfileForm form,
                       Model model, BindingResult result) {
        if (result.hasErrors()){
            model.addAttribute("profileForm", form);
            return "profile-edit";
        }
        profileService.update(form, profileService.getProfileByAccountId(details.getAccount().getId()).getId());
        AchievementDto ach = achievementService.getAchievement(details.getAccount().getId(), environment.getProperty("PROFILE_ACH_ID", Long.class));
        if (ach.getCurrentValue() < ach.getRequiredValue()){
            achievementService.updateScore(ach.getAccId(), environment.getProperty("PROFILE_ACH_ID", Long.class));
        }
        return "redirect:/profile";
    }
}
