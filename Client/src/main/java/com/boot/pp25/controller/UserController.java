package com.boot.pp25.controller;

import com.boot.pp25.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ModelAndView userGet(Authentication auth) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usersPage/users");
        modelAndView.addObject("user", auth.getPrincipal());
        modelAndView.addObject("rolesAuth", ((User) auth.getPrincipal()).getRoles()
                .stream().map(Objects::toString).collect(Collectors.joining(" ")));
        return modelAndView;
    }
}
