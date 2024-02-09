package ru.shadrag.hw6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shadrag.hw6.models.Task;
import ru.shadrag.hw6.models.User;
import ru.shadrag.hw6.repositories.TasksRepository;
import ru.shadrag.hw6.services.interfaces.TasksService;

import java.util.List;
@Service
public class TasksServiceImpl implements TasksService {

    private final TasksRepository repository;

    @Autowired
    public TasksServiceImpl(TasksRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        return repository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task, User user) {
        Task oldTask = getTaskById(id);
        oldTask.setTitle(task.getTitle());
        oldTask.setDescription(task.getDescription());
        oldTask.setUser(user);

        return repository.save(oldTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task deletedTask = getTaskById(id);
        repository.delete(deletedTask);
    }
}
