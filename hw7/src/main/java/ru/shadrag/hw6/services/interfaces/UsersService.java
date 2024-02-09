package ru.shadrag.hw6.services.interfaces;

import ru.shadrag.hw6.models.Task;
import ru.shadrag.hw6.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();
    User getUserById(Long id);

    User getUserByUsernameAndPassword(String username, String password);
    User createUser(User user);
    void deleteUser(Long id);
}
