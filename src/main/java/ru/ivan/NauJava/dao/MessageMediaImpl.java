package ru.ivan.NauJava.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ivan.NauJava.model.Message;
import ru.ivan.NauJava.model.MessageMedia;

import java.util.List;

public class MessageMediaImpl implements MessageMediaCustom {
    private final EntityManager entityManager;

    @Autowired
    public MessageMediaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MessageMedia> findByMessageAndMediaType(Message message, String mediaType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MessageMedia> criteriaQuery = criteriaBuilder.createQuery(MessageMedia.class);

        Root<MessageMedia> messageMediaRoot = criteriaQuery.from(MessageMedia.class);
        Predicate messagePredicate = criteriaBuilder.equal(
                messageMediaRoot.get("message"), message);
        Predicate mediaTypePredicate = criteriaBuilder.equal(
                messageMediaRoot.get("mediaType"), mediaType);
        Predicate combined = criteriaBuilder.and(messagePredicate, mediaTypePredicate);

        criteriaQuery.select(messageMediaRoot).where(combined);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
