package ru.ivan.NauJava.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ivan.NauJava.model.Chat;
import ru.ivan.NauJava.model.ChatParticipant;
import ru.ivan.NauJava.model.User;

import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Chat> findByUser(User user) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chat> query = cb.createQuery(Chat.class);
        Root<ChatParticipant> root = query.from(ChatParticipant.class);

        query.select(root.get("chat"));

        query.where(cb.equal(root.get("user"), user));

        return entityManager.createQuery(query).getResultList();
    }
}
