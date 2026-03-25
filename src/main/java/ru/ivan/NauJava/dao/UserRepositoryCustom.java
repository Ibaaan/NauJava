package ru.ivan.NauJava.dao;

import ru.ivan.NauJava.model.Chat;
import ru.ivan.NauJava.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByUsername(String username);
    List<Chat> findByUser(User user);
}
