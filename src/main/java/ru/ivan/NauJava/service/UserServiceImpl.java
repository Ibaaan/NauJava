package ru.ivan.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.ivan.NauJava.model.ChatParticipant;
import ru.ivan.NauJava.model.User;
import ru.ivan.NauJava.repository.ChatParticipantRepository;
import ru.ivan.NauJava.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final PlatformTransactionManager transactionManager;
    private final UserRepository userRepository;
    private final ChatParticipantRepository chatParticipantRepository;

    @Autowired
    public UserServiceImpl(PlatformTransactionManager transactionManager,
                           UserRepository userRepository,
                           ChatParticipantRepository chatParticipantRepository) {
        this.transactionManager = transactionManager;
        this.userRepository = userRepository;
        this.chatParticipantRepository = chatParticipantRepository;
    }

    @Override
    public void deleteUser(String username) {
        TransactionStatus status = transactionManager.getTransaction(new
                DefaultTransactionDefinition());
        try {
            Optional<User> user = userRepository.findByUsername(username);

            if (user.isPresent()){
                List<ChatParticipant> participantList =
                        chatParticipantRepository.findByUser(user.get());
                for (ChatParticipant cp : participantList){
                    chatParticipantRepository.deleteById(cp.getId());
                }
            }
            user.ifPresent(value -> userRepository.deleteById(value.getId()));
            transactionManager.commit(status);
        }catch (DataAccessException ex){
            transactionManager.rollback(status);
            throw ex;
        }
    }
}
