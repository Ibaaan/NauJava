package ru.ivan.NauJava.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ivan.NauJava.model.Chat;
import ru.ivan.NauJava.model.ChatParticipant;
import ru.ivan.NauJava.model.MessageMedia;
import ru.ivan.NauJava.model.User;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepositoryCustom {
    private final EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate usernamePredicate = criteriaBuilder.equal(
                userRoot.get("username"), username);

        criteriaQuery.select(userRoot).where(usernamePredicate);
        User result = entityManager.createQuery(criteriaQuery).getSingleResult();
        return Optional.ofNullable(result);
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
