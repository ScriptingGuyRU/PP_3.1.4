package com.boot.pp25.controller;

import com.boot.pp25.model.Role;
import com.boot.pp25.model.User;
import com.boot.pp25.service.abstractServ.RoleServices;
import com.boot.pp25.service.abstractServ.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public ModelAndView mainAdminControllerGet(Authentication auth) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("authUser", auth.getPrincipal());
        modelAndView.addObject("userEmail", ((User) auth.getPrincipal()).getEmail());
        modelAndView.addObject("rolesAuth", ((User) auth.getPrincipal()).getRoles()
                .stream().map(Objects::toString).collect(Collectors.joining(" ")));
        modelAndView.setViewName("adminsPages/admin");
        return modelAndView;
    }

}
