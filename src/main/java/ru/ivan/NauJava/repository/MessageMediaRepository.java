package ru.ivan.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ivan.NauJava.model.MessageMedia;

@RepositoryRestResource(path = "message_media")
public interface MessageMediaRepository extends CrudRepository<MessageMedia, Long> {
}
