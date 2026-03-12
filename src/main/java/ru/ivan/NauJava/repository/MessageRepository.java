package ru.ivan.NauJava.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivan.NauJava.model.Message;

import java.util.List;

@Component
public class MessageRepository implements CrudRepository<Message, Long> {
    private final List<Message> messageContainer;

    @Autowired
    public MessageRepository(List<Message> messageContainer) {
        this.messageContainer = messageContainer;
    }


    @Override
    public void create(Message entity) {
        messageContainer.add(entity);
    }

    @Override
    public Message read(Long id) {
        for (Message m : messageContainer) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public void update(Message entity) {
        for(int i = 0; i < messageContainer.size(); i++) {
            Message m = messageContainer.get(i);
            if (m.getId().equals(entity.getId())){
                messageContainer.set(i, entity);
                return;
            }
        }
    }

    @Override
    public void delete(Long id) {
        for(int i = 0; i < messageContainer.size(); i++) {
            Message m = messageContainer.get(i);
            if (m.getId().equals(id)) {
                messageContainer.remove(i);
                return;
            }
        }
    }
}
