package org.example.assignment.config;

import org.example.assignment.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(basePackages = "org.example.assignment.controller.web")
public class GlobalUserAdvice {

    @ModelAttribute("userName")
    public String bindUserName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }
        Object principal = auth.getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getName();
        }
        return null;
    }


    @ModelAttribute("isLoggedIn")
    public boolean isLoggedIn(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof User;
    }
}
