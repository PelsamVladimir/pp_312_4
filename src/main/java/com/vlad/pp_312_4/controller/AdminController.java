package com.vlad.pp_312_4.controller;

import com.vlad.pp_312_4.model.Role;
import com.vlad.pp_312_4.model.User;
import com.vlad.pp_312_4.service.RoleService;
import com.vlad.pp_312_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/")
    public String start(){
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String allUsers(Principal principal, ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userNew", new User());
        model.addAttribute("userThis", userService.loadUserByUsername(principal.getName()));
        model.addAttribute("roles", roleService.allRoles());
        return "/admin";
    }

    @PostMapping(value = "/admin")
    public String postAddUser(@ModelAttribute User userNew, @RequestParam("select-roles") String[] roleAdmin) {
        Set<Role> roles = new HashSet<>();
        for (String role : roleAdmin) {
            roles.add(roleService.getRoleByName(role));
        }
        userNew.setRoles(roles);
        userService.addUser(userNew);

        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/{id}/edit")
    public String postEditUser(@ModelAttribute("user") User user, @RequestParam(value = "select-roles", required = false) String[] roleAdmin) {
        Set<Role> roles = new HashSet<>();

        for (String role : roleAdmin) {
            roles.add(roleService.getRoleByName(role));
        }
        user.setRoles(roles);
        userService.editUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/hello")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @PostMapping("/admin/{id}/delete")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        return "redirect:/admin";
    }

}
