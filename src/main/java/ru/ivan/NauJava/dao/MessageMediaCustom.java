package ru.ivan.NauJava.dao;

import ru.ivan.NauJava.model.Message;
import ru.ivan.NauJava.model.MessageMedia;

import java.util.List;

public interface MessageMediaCustom {
    List<MessageMedia> findByMessageAndMediaType(Message message, String mediaType);
}
