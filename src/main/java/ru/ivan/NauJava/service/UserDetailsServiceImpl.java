package ru.ivan.NauJava.service;

import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.ivan.NauJava.model.User;
import ru.ivan.NauJava.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @NullMarked
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> appUser = userRepository.findByUsername(username);
        if (appUser.isPresent()) {
            User user = appUser.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(),
                    userRoles(user));
        } else {
            throw new UsernameNotFoundException("user not found");
        }
    }

    private Collection<GrantedAuthority> userRoles(User appUser)
    {
        List<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new SimpleGrantedAuthority(appUser.getRole().getRoleName()));
        return collect;
    }
}
