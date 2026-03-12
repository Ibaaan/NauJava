package ru.ivan.NauJava.service;


import ru.ivan.NauJava.model.Message;

public interface MessageService{

    void createMessage(Long chatId, Long senderId, String text);
    Message findMessage(Long id);
    void deleteMessage(Long id);
    void updateMessage(Long id, String newText);
}
