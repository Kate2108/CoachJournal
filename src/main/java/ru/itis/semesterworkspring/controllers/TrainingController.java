package ru.itis.semesterworkspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.semesterworkspring.dto.forms.TrainingForm;
import ru.itis.semesterworkspring.security.details.UserDetailsImpl;
import ru.itis.semesterworkspring.services.GroupService;
import ru.itis.semesterworkspring.services.ProfileService;
import ru.itis.semesterworkspring.services.TrainingService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trainings")
public class TrainingController {
    private final TrainingService trainingService;
    private final ProfileService profileService;
    private final GroupService groupService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getTrainings(@AuthenticationPrincipal UserDetailsImpl details, Model model, HttpServletRequest request) {
        Long accountId = details.getAccount().getId();
        String category = profileService.getProfileByAccountId(accountId).getCategory();

        model.addAttribute("addGroupError", request.getParameterMap().containsKey("add-group-error"));
        model.addAttribute("addTimeError", request.getParameterMap().containsKey("add-time-error"));
        model.addAttribute("deleteError", request.getParameterMap().containsKey("delete-error"));
        model.addAttribute("deleteGroupError", request.getParameterMap().containsKey("delete-group-error"));

        model.addAttribute("trainings", trainingService.findAllAccountTrainings(accountId));

        if (trainingService.findAllAccountTrainings(accountId).size() != 0){
            model.addAttribute("fact", trainingService.countOfAccountsWithSameCategoryAndLowerCountOfTrainings(accountId,category));
        }

        model.addAttribute("form", new TrainingForm());
        return "schedule";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public String addTraining(@AuthenticationPrincipal UserDetailsImpl details, @Valid TrainingForm form, BindingResult result){
        if (form.getGroup().equals("") || groupService.findGroupByName(details.getAccount().getId(), form.getGroup()) == null){
            return "redirect:/trainings?add-group-error";
        }
        List<ObjectError> list = result.getGlobalErrors();
        if (list.size() != 0){
            return "redirect:/trainings?add-time-error";
        }
        if(!trainingService.addTraining(form, details.getAccount().getId())){
            return "redirect:/trainings?add-error";
        }
        return "redirect:/trainings";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/delete")
    public String deleteTraining(@AuthenticationPrincipal UserDetailsImpl details, TrainingForm form){
        if (form.getDeleteGroup().equals("") || groupService.findGroupByName(details.getAccount().getId(), form.getDeleteGroup()) == null){
            return "redirect:/trainings?delete-group-error";
        }
        if (!trainingService.deleteTraining(details.getAccount().getId(), form.getDeleteDay(), form.getDeleteGroup())){
            return "redirect:/trainings?delete-error";
        }
        return "redirect:/trainings";
    }
}
