package ru.ivan.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ivan.NauJava.model.Chat;
import ru.ivan.NauJava.model.User;
import ru.ivan.NauJava.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/custom/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findByUsername")
    public Optional<User> findByName(@RequestParam String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping("/findByUserId")
    public List<Chat> findByName(@RequestParam Long userId) {
        return userRepository.findByUserId(userId);
    }
}
