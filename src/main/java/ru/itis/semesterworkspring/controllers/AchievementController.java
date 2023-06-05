package ru.itis.semesterworkspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.semesterworkspring.security.details.UserDetailsImpl;
import ru.itis.semesterworkspring.services.AchievementService;
@RequiredArgsConstructor
@Controller
@RequestMapping("/achievements")
public class AchievementController {
    private final AchievementService achievementService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getAchievementsPage(@AuthenticationPrincipal UserDetailsImpl details, Model model){
        Long accountId = details.getAccount().getId();
        if (achievementService.getAccountAchievements(accountId).isEmpty()){
            achievementService.makeBaseListOfAchievements(accountId);
        }
        model.addAttribute("achievements", achievementService.getAccountAchievements(accountId));
        return "achievements";
    }
}
