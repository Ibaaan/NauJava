package ru.ivan.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ivan.NauJava.model.ChatParticipant;
import ru.ivan.NauJava.model.User;

import java.util.List;

public interface ChatParticipantRepository extends CrudRepository<ChatParticipant, Long> {
    List<ChatParticipant> findByUser(User user);

}
