package ru.ivan.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ivan.NauJava.model.Chat;

@RepositoryRestResource(path = "chat")
public interface ChatRepository extends CrudRepository<Chat, Long> {
}
