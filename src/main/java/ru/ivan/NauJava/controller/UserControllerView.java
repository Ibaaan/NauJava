package ru.ivan.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ivan.NauJava.model.User;
import ru.ivan.NauJava.repository.UserRepository;

@Controller
@RequestMapping("/custom/user/view")
public class UserControllerView {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String userListView(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userList";
    }
}
