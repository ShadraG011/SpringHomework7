package ru.shadrag.hw6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shadrag.hw6.models.User;
import ru.shadrag.hw6.repositories.UsersRepository;
import ru.shadrag.hw6.services.interfaces.UsersService;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return usersRepository.findAll().stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst().orElse(null);
    }

    @Override
    public User createUser(User user) {
        return usersRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User deletedUser = getUserById(id);
        usersRepository.delete(deletedUser);
    }
}
