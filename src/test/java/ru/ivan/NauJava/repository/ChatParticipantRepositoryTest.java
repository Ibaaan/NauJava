package ru.ivan.NauJava.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ivan.NauJava.model.Chat;
import ru.ivan.NauJava.model.ChatParticipant;
import ru.ivan.NauJava.model.User;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatParticipantRepositoryTest {
    private final UserRepository userRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatRepository chatRepository;

    @Autowired
    ChatParticipantRepositoryTest(UserRepository userRepository, ChatParticipantRepository chatParticipantRepository, ChatRepository chatRepository) {
        this.userRepository = userRepository;
        this.chatParticipantRepository = chatParticipantRepository;
        this.chatRepository = chatRepository;
    }

    @Test
    void findByUser() {
        String userName = UUID.randomUUID().toString();
        User user = new User();
        user.setUsername(userName);
        userRepository.save(user);

        Chat chat1 = new Chat();
        chat1.setChatName("1");
        chat1.setChatType("1");
        chatRepository.save(chat1);
        System.out.println(chat1.getId());

        ChatParticipant chatParticipant1 = new ChatParticipant();
        chatParticipant1.setChat(chat1);
        chatParticipant1.setUser(user);
        chatParticipantRepository.save(chatParticipant1);

        List<ChatParticipant> participantList = chatParticipantRepository.findByUser(user);

        Assertions.assertEquals(1, participantList.size());
        Assertions.assertEquals(chatParticipant1.getId(), participantList.getFirst().getId());
    }
}