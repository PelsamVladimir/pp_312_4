package com.vlad.pp_312_4.controller;

import com.vlad.pp_312_4.model.User;
import com.vlad.pp_312_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping(value = "/")
//    public String start() {
//        return "redirect:/login";
//    }

//        @GetMapping("/user")
//    public String getUser(Principal principal, ModelMap modelMap) {
//        modelMap.addAttribute("userThis", userService.loadUserByUsername(principal.getName()));
//        return "user";
//    }
//}
//    private final UserService userService;
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//@GetMapping("/user")
//public String allUsers(Principal principal, ModelMap model) {
//    model.addAttribute("user", userService.());
//    model.addAttribute("userNew", new User());
//    model.addAttribute("userThis", userService.loadUserByUsername(principal.getName()));
//    model.addAttribute("roles", roleService.allRoles());
//    return "/admin";
//}
    @GetMapping("/user")
    public ModelAndView User() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}