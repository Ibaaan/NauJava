package ru.ivan.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ivan.NauJava.model.Message;

@RepositoryRestResource(path = "message")
public interface MessageRepository extends CrudRepository<Message, Long> {
}
