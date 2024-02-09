package ru.shadrag.hw6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shadrag.hw6.components.Status;
import ru.shadrag.hw6.models.User;
import ru.shadrag.hw6.services.interfaces.TasksService;
import ru.shadrag.hw6.models.Task;
import ru.shadrag.hw6.services.interfaces.UsersService;

import java.util.Date;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    private boolean onIndexPage;
    private final TasksService tasksService;

    private final UsersService usersService;

    @Autowired
    public TasksController(TasksService tasksService, UsersService usersService) {
        this.tasksService = tasksService;
        this.usersService = usersService;
    }

    @RequestMapping(value = "/all-tasks", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("buttonStat", Status.ALL_TASKS);
        model.addAttribute("tasks", tasksService.getAllTasks());
        onIndexPage = true;
        return "tasks-views/all-tasks";
    }

    @RequestMapping(value = "/all-tasks/{id}/{status}", method = RequestMethod.GET)
    public String updateStatus(@PathVariable("status") Status status, @PathVariable("id") Long id) {
        Task task = tasksService.getTaskById(id);
        Status sortStatus = task.getStatus();
        task.setStatus(status);
        task.setDate(new Date());
        tasksService.updateTask(task.getId(), task, task.getUser());
        if (onIndexPage)
            return "redirect:/tasks/all-tasks";
        else
            return "redirect:/tasks/sort-tasks/" + sortStatus;
    }

    @RequestMapping(value = "/update-task/{id}", method = RequestMethod.GET)
    public String updateTask(@PathVariable("id") Long id, @RequestParam(value = "userId", defaultValue = "-1") Long userId, Model model) {
        User tmpUser = usersService.getUserById(userId);
        Task tmpTask = tasksService.getTaskById(id);
        if (tmpUser != null)
            tmpTask.setUser(tmpUser);
        model.addAttribute("user", userId);
        model.addAttribute("task", tmpTask);
        model.addAttribute("users", usersService.getAllUsers());
        return "tasks-views/update-task";
    }

    @RequestMapping(value = "/update-task/{id}", method = RequestMethod.POST)
    public String updateTask(@ModelAttribute("task") Task task, @PathVariable("id") Long id, @RequestParam(value = "userId") Long userId) {
        tasksService.updateTask(id, task, usersService.getUserById(userId));
        if (onIndexPage)
            return "redirect:/tasks/all-tasks";
        else
            return "redirect:/tasks/sort-tasks/" + tasksService.getTaskById(id).getStatus();
    }


    @RequestMapping(value = "/create-task", method = RequestMethod.GET)
    public String createTask(Model model) {
        model.addAttribute("task", new Task());
        return "tasks-views/create-task";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String createTask(@ModelAttribute Task task) {
        tasksService.createTask(task);
        return "redirect:/tasks/all-tasks";
    }

    @RequestMapping(value = "/delete-task/{id}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable("id") Long id) {
        tasksService.deleteTask(id);
        return "redirect:/tasks/all-tasks";
    }

    @RequestMapping(value = "/sort-tasks/{status}", method = RequestMethod.GET)
    public String sortTaskByStatus(@PathVariable("status") Status status, Model model) {
        onIndexPage = false;
        model.addAttribute("buttonStat", status);
        model.addAttribute("tasks", tasksService.getAllTasks().stream().filter(it -> it.getStatus() == status));
        return "tasks-views/all-tasks";
    }

}
