package ru.shadrag.hw6.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shadrag.hw6.models.Task;
import ru.shadrag.hw6.models.User;
import ru.shadrag.hw6.services.interfaces.UsersService;

@Controller
public class AppController {

    UsersService usersService;

    @GetMapping("/admin-login")
    public String adminEndpoint() {
        return "redirect:/admin/all-users";
    }

    @GetMapping("/user-login")
    public String userEndpoint(Model model) {
        model.addAttribute("user", new User());
        return "users-views/login-page";
    }

    @PostMapping("/user-login")
    public String userEndpoint(@ModelAttribute User user, Model model) {
        User registreUser = usersService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (registreUser == null) {
            return "redirect:/registration-page";
        }
        model.addAttribute("user", registreUser);
        return "redirect:/users/user-tasks";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String createTask(Model model) {
        model.addAttribute("user", new User());
        return "users-views/registration-page";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createTask(@ModelAttribute User user, Model model) {
        model.addAttribute("user", usersService.createUser(user));
        return "redirect:/users/user-tasks";
    }
}
