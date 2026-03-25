package ru.ivan.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ivan.NauJava.model.MessageMedia;

public interface MessageMediaRepository extends CrudRepository<MessageMedia, Long> {
}
