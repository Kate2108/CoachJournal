package ru.itis.semesterworkspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.semesterworkspring.dto.AchievementDto;
import ru.itis.semesterworkspring.dto.forms.GroupForm;
import ru.itis.semesterworkspring.models.Group;
import ru.itis.semesterworkspring.security.details.UserDetailsImpl;
import ru.itis.semesterworkspring.services.AchievementService;
import ru.itis.semesterworkspring.services.GroupService;
import ru.itis.semesterworkspring.services.ProfileService;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/groups")
@Controller
@PropertySource("classpath:file.properties")
public class GroupController {
    @Autowired
    private Environment environment;
    private final GroupService groupService;
    private final AchievementService achievementService;
    private final ProfileService profileService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getGroups(@AuthenticationPrincipal UserDetailsImpl details, Model model, HttpServletRequest request) {
        Long accountId = details.getAccount().getId();
        String category = profileService.getProfileByAccountId(accountId).getCategory();

        model.addAttribute("addError", request.getParameterMap().containsKey("add-error"));
        model.addAttribute("deleteError", request.getParameterMap().containsKey("delete-error"));
        model.addAttribute("updateError", request.getParameterMap().containsKey("update-error"));

        model.addAttribute("groups", groupService.findAllAccountGroups(accountId));
        if (groupService.findAllAccountGroups(accountId).size() != 0){
            model.addAttribute("fact", profileService.countOfAccountsWithSameCategoryAndLowerCountOfGroupMembers(accountId, category));
        }

        model.addAttribute("form", new GroupForm());
        return "group";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public String addGroup(@AuthenticationPrincipal UserDetailsImpl details, GroupForm form) {

        if (form.getName().equals("") || groupService.findGroupByName(details.getAccount().getId(), form.getName()) != null){
            return "redirect:/groups?add-error";
        }
        groupService.addGroup(Group.builder()
                .name(form.getName())
                .countOfMembers(form.getCountOfMembers())
                .account(details.getAccount())
                .build());
        AchievementDto ach = achievementService.getAchievement(details.getAccount().getId(), environment.getProperty("GROUP_ADD_MULTI_ACH_ID", Long.class));
        if (ach.getCurrentValue() < ach.getRequiredValue()){
            if(ach.getCurrentValue() == 0){
                achievementService.updateScore(ach.getAccId(), environment.getProperty("GROUP_ADD_ACH_ID", Long.class));
            }
            achievementService.updateScore(ach.getAccId(), environment.getProperty("GROUP_ADD_MULTI_ACH_ID", Long.class));
        }
        return "redirect:/groups";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete")
    public String deleteGroup(@AuthenticationPrincipal UserDetailsImpl details, GroupForm form) {

        if (form.getDeleteName().equals("") || groupService.findGroupByName(details.getAccount().getId(), form.getDeleteName()) == null){
            return "redirect:/groups?delete-error";
        }
        groupService.deleteGroup(details.getAccount().getId(), form.getDeleteName());

        AchievementDto ach = achievementService.getAchievement(details.getAccount().getId(), environment.getProperty("GROUP_DELETE_ACH_ID", Long.class));
        if (ach.getCurrentValue() < ach.getRequiredValue()){
            achievementService.updateScore(ach.getAccId(), environment.getProperty("GROUP_DELETE_ACH_ID", Long.class));
        }

        return "redirect:/groups";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update")
    public String updateGroup(@AuthenticationPrincipal UserDetailsImpl details, GroupForm form) {

        if (form.getUpdateName().equals("") || groupService.findGroupByName(details.getAccount().getId(), form.getUpdateName()) == null){
            return "redirect:/groups?update-error";
        }
        groupService.updateGroup(details.getAccount().getId(), form.getUpdateName(), form.getNewCountOfMembers());

        return "redirect:/groups";
    }
}
