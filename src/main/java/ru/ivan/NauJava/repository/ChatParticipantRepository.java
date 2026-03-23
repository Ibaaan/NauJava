package ru.ivan.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ivan.NauJava.model.Chat;

public interface ChatParticipantRepository extends CrudRepository<Chat, Long> {

}
