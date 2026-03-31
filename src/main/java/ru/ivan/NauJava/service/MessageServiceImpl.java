package ru.ivan.NauJava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;
import org.springframework.util.JdkIdGenerator;
import ru.ivan.NauJava.model.Message;
import ru.ivan.NauJava.repository.CrudRepository;
import ru.ivan.NauJava.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {
    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);
    private final CrudRepository<Message, Long> messageRepository;
    private final IdGenerator idGenerator = new JdkIdGenerator();

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void createMessage(Long chatId, Long senderId, String text) {
        Message m = new Message(
                generateId(),
                chatId,
                senderId,
                text);

        messageRepository.create(m);
        log.info("created: {}", m);
    }

    @Override
    public Message findMessage(Long id) {
        return messageRepository.read(id);
    }

    @Override
    public void deleteMessage(Long id) {
        Message m = messageRepository.read(id);
        messageRepository.delete(id);
        log.info("deleted: {}", m);
    }


    @Override
    public void updateMessage(Long id, String newText) {
        Message m = messageRepository.read(id);
        String oldText = m.getText();
        m.setText(newText);
        messageRepository.update(m);
        log.info("updated: {}\nfrom text: {}", m, oldText);
    }

    private Long generateId() {
        return Math.abs(idGenerator.generateId().getMostSignificantBits());
    }
}
