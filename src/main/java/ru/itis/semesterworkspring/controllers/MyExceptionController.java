package ru.itis.semesterworkspring.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyExceptionController implements ErrorController {
    private static final String HEADER = "X-Requested-With";
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        if (isAjax(request)) {
            return "{\"error\": \"Server error. Try again later\"}";
        }
        else {
            Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
            if (status != null) {
                int statusCode = Integer.parseInt(status.toString());

                if (statusCode == HttpStatus.NOT_FOUND.value()) {
                    return "code-pages/error-404";
                }
                else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                    return "code-pages/error-500";
                }
            }
            return "code-pages/error";
        }
    }
    private boolean isAjax(HttpServletRequest request) {
        return (request.getHeader(HEADER) != null && "XMLHttpRequest".equals(request.getHeader(HEADER)));
    }
}
