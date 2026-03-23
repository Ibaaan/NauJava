package ru.ivan.NauJava.dao;

import ru.ivan.NauJava.model.Chat;
import ru.ivan.NauJava.model.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<Chat> findByUser(User user);
}
