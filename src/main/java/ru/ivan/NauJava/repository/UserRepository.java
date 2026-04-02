package ru.ivan.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ivan.NauJava.model.Chat;
import ru.ivan.NauJava.model.User;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "user")
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT cp.chat FROM ChatParticipant cp WHERE cp.user.id = :userId")
    List<Chat> findByUserId(@Param("userId") Long userId);
}
