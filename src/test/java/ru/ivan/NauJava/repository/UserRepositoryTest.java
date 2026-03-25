package ru.ivan.NauJava.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ivan.NauJava.model.Chat;
import ru.ivan.NauJava.model.ChatParticipant;
import ru.ivan.NauJava.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@SpringBootTest
class UserRepositoryTest {
    private final UserRepository userRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatRepository chatRepository;

    @Autowired
    UserRepositoryTest(UserRepository userRepository, ChatParticipantRepository chatParticipantRepository, ChatRepository chatRepository) {
        this.userRepository = userRepository;
        this.chatParticipantRepository = chatParticipantRepository;
        this.chatRepository = chatRepository;
    }

    @Test
    void findByUsernameSuccess() {
        String userName = UUID.randomUUID().toString();
        User user = new User();
        user.setUsername(userName);
        userRepository.save(user);
        Optional<User> foundUser = userRepository.findByUsername(userName);
        boolean isUserFound;
        if (foundUser.isPresent()) {
            isUserFound = true;
            Assertions.assertEquals(foundUser.get().getId(), user.getId());
        } else {
            isUserFound = false;
        }

        Assertions.assertTrue(isUserFound);
    }

    @Test
    void findByUsernameFailure() {
        String userName = UUID.randomUUID().toString();
        Optional<User> foundUser = userRepository.findByUsername(userName);
        boolean isUserFound = foundUser.isPresent();
        Assertions.assertFalse(isUserFound);
    }

    @Test
    void findByUser(){
        String userName = UUID.randomUUID().toString();
        User user = new User();
        user.setUsername(userName);
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

        List<Chat> foundChats = userRepository.findByUserId(user.getId());
        Assertions.assertEquals(2, foundChats.size());
        Assertions.assertEquals(chat1.getId(), foundChats.getFirst().getId());
        Assertions.assertEquals(chat2.getId(), foundChats.getLast().getId());
        Assertions.assertInstanceOf(Chat.class, foundChats.getFirst());
    }
}
