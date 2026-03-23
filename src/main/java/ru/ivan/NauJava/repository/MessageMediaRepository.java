package ru.ivan.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ivan.NauJava.model.Message;
import ru.ivan.NauJava.model.MessageMedia;

import java.util.List;

public interface MessageMediaRepository extends CrudRepository<MessageMedia, Long> {
    List<MessageMedia> findByMessageAndMediaType(Message message, String mediaType);
}
