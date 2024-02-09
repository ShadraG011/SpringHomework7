package ru.shadrag.hw6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shadrag.hw6.models.Task;

public interface TasksRepository extends JpaRepository<Task, Long> {
}
