package ru.ivan.NauJava.service;

import ru.ivan.NauJava.model.User;

public interface UserService {
    void addUser(User user);
    void deleteUser(String username);
}
