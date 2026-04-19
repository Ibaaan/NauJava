package ru.ivan.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ivan.NauJava.model.ChatParticipant;
import ru.ivan.NauJava.model.User;

import java.util.List;

@RepositoryRestResource(path = "chat_participant")
public interface ChatParticipantRepository extends CrudRepository<ChatParticipant, Long> {
    List<ChatParticipant> findByUser(User user);

}
