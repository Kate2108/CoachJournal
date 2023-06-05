package ru.itis.semesterworkspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.semesterworkspring.models.Profile;
import ru.itis.semesterworkspring.security.details.UserDetailsImpl;
import ru.itis.semesterworkspring.services.ProfileService;

@RequiredArgsConstructor
@RequestMapping("/profile")
@Controller
public class ProfileController {
    private final ProfileService profileService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    public String getProfile(@AuthenticationPrincipal UserDetailsImpl details, Model model) {
        Long accountId = details.getAccount().getId();
        if (profileService.getProfileByAccountId(accountId) == null){
            Profile profile = Profile.builder()
                    .account(details.getAccount())
                    .build();
            profileService.save(profile);
        }
        model.addAttribute("profile", profileService.getProfileByAccountId(accountId));
        return "profile";
    }
}
