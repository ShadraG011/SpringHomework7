package ru.shadrag.hw6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shadrag.hw6.models.User;

public interface UsersRepository extends JpaRepository<User, Long> {
}
