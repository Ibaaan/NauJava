package ru.ivan.NauJava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.ivan.NauJava.model.Chat;
import ru.ivan.NauJava.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT cp.chat FROM ChatParticipant  cp where cp.user = :user")
    List<Chat> findByUser(@Param("user") User user);
}
