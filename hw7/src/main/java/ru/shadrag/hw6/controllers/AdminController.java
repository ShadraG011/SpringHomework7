package ru.shadrag.hw6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shadrag.hw6.models.Task;
import ru.shadrag.hw6.models.User;
import ru.shadrag.hw6.services.interfaces.TasksService;
import ru.shadrag.hw6.services.interfaces.UsersService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UsersService usersService;

    private final TasksService tasksService;

    @Autowired
    public AdminController(UsersService usersService, TasksService tasksService) {
        this.usersService = usersService;
        this.tasksService = tasksService;
    }

    @RequestMapping(value = "/all-users", method = RequestMethod.GET)
    public String indexUsers(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "users-views/all-users";
    }

    @RequestMapping(value = "/set-task", method = RequestMethod.GET)
    public String setTaskForUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("tasks", tasksService.getAllTasks().stream().filter(it -> it.getUser() == null).toList());
        model.addAttribute("user", usersService.getUserById(id));
        return "user-tasks";
    }

    @RequestMapping(value = "/set-task", method = RequestMethod.POST)
    public String setTaskForUser(@RequestParam("id") Long id, @RequestParam("taskId") Long taskId) {
        User user = usersService.getUserById(id);
        Task task = tasksService.getTaskById(taskId);
        user.getUserTasksList().add(tasksService.getTaskById(taskId));
        tasksService.updateTask(taskId, task, user);
        return "redirect:/users/all-users";
    }


    @RequestMapping(value = "/create-user", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "users-views/create-user";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String createUser(@ModelAttribute User user) {
        usersService.createUser(user);
        return "redirect:/users/all-users";
    }

    @RequestMapping(value = "/delete-user", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") Long id) {
        usersService.deleteUser(id);
        return "redirect:/users/all-users";
    }
}
