package ru.ivan.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ivan.NauJava.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
