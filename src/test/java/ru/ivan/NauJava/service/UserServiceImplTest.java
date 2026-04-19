package ru.ivan.NauJava.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ivan.NauJava.model.Chat;
import ru.ivan.NauJava.model.ChatParticipant;
import ru.ivan.NauJava.model.User;
import ru.ivan.NauJava.repository.ChatParticipantRepository;
import ru.ivan.NauJava.repository.ChatRepository;
import ru.ivan.NauJava.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class UserServiceImplTest {
    private final UserRepository userRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatRepository chatRepository;
    private final UserService userService;

    @Autowired
    UserServiceImplTest(UserRepository userRepository,
                        ChatParticipantRepository chatParticipantRepository,
                        ChatRepository chatRepository,
                        UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.chatParticipantRepository = chatParticipantRepository;
        this.chatRepository = chatRepository;
        this.userService = userService;
    }

    @Test
    void deleteUser() {
        String username = UUID.randomUUID().toString();
        User user = new User();
        user.setUsername(username);
        userRepository.save(user);

        Chat chat1 = new Chat();
        chat1.setChatName("1");
        chat1.setChatType("1");
        chatRepository.save(chat1);

        Chat chat2 = new Chat();
        chat2.setChatName("2");
        chat2.setChatType("2");
        chatRepository.save(chat2);

        ChatParticipant chatParticipant1 = new ChatParticipant();
        chatParticipant1.setChat(chat1);
        chatParticipant1.setUser(user);
        chatParticipantRepository.save(chatParticipant1);

        ChatParticipant chatParticipant2 = new ChatParticipant();
        chatParticipant2.setChat(chat2);
        chatParticipant2.setUser(user);
        chatParticipantRepository.save(chatParticipant2);

        userService.deleteUser(username);

        Assertions.assertTrue(userRepository.findByUserId(user.getId()).isEmpty());
        List<ChatParticipant> expected = (List<ChatParticipant>) chatParticipantRepository.findAllById(
                List.of(
                        new Long[]{chatParticipant1.getId(),
                                chatParticipant2.getId()}));
        Assertions.assertTrue(expected.isEmpty());
    }
}