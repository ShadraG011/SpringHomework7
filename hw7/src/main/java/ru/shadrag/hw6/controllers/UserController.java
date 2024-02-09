package ru.shadrag.hw6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shadrag.hw6.models.Task;
import ru.shadrag.hw6.models.User;
import ru.shadrag.hw6.services.interfaces.TasksService;
import ru.shadrag.hw6.services.interfaces.UsersService;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final UsersService usersService;

    private final TasksService tasksService;

    @Autowired
    public UserController(UsersService usersService, TasksService tasksService) {
        this.usersService = usersService;
        this.tasksService = tasksService;
    }

    @RequestMapping(value = "/user-tasks", method = RequestMethod.GET)
    public String getUserTasks(@ModelAttribute User user, Model model){
        if(user == null) {
            return "redirect:/user-login";
        }
        model.addAttribute("user", user);
        model.addAttribute("tasks", user.getUserTasksList());
        return "/users-views/user-tasks";

    }
}
