package ru.itis.semesterworkspring.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.semesterworkspring.dto.forms.SignInForm;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignInController {
    @GetMapping("/signIn")
    public String getSignInPage(Authentication authentication, Model model, HttpServletRequest request){
        if (authentication != null){
            return "redirect:/main";
        }
        model.addAttribute("error", request.getParameterMap().containsKey("error"));
        model.addAttribute("signInForm", new SignInForm());
        return "signIn";
    }
}
