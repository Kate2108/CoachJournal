package ru.itis.semesterworkspring.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.semesterworkspring.dto.forms.ConfirmationForm;
import ru.itis.semesterworkspring.dto.forms.SignUpForm;
import ru.itis.semesterworkspring.services.SignUpService;
import ru.itis.semesterworkspring.utils.ConfirmationCodeGenerator;
import ru.itis.semesterworkspring.utils.ElasticEmailApi;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signUp")
public class SignUpController {
    private final SignUpService signUpService;
    private final ConfirmationCodeGenerator generator;
    private final ElasticEmailApi sender;

    @GetMapping
    public String getSignUpPage(Model model, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()){
            return "redirect:/main";
        }

        model.addAttribute("signUpForm", new SignUpForm());
        return "signUp";
    }

    @PostMapping
    public String signUp(@Valid SignUpForm form, BindingResult result, Model model){

        if (result.hasErrors()){
            List<ObjectError> list = result.getGlobalErrors();
            if (list.size() != 0){
                List<String> errors = new ArrayList<>();
                for (ObjectError objectError : list) {
                    errors.add(objectError.getDefaultMessage());
                }
                model.addAttribute("errors", errors);
            }
            model.addAttribute("signUpForm", form);
            return "signUp";
        }
        signUpService.prepareForm(form);
        generator.generateCode();

        System.out.println(generator.getCode());

        sender.sendEmail(form.getEmail(), String.valueOf(generator.getCode()));

        return "redirect:/signUp/confirmation";
    }
    @GetMapping("/confirmation")
    public String getConfPage(Model model, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated() || generator.getCode() == 0){
            return "redirect:/main";
        }

        model.addAttribute("form", new ConfirmationForm());
        return "confirm-email";
    }

    @PostMapping("/confirmation")
    public String confirm(ConfirmationForm form, Model model){
        if(form.getCode() == generator.getCode()){
            generator.setCode(0);
            signUpService.signUp();
            return "redirect:/signIn";
        }
        model.addAttribute("error", "Incorrect code");
        return "redirect:/signUp/confirmation";
    }
}
