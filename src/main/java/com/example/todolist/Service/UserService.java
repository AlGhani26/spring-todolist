package com.example.todolist.Service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.todolist.Repository.UserRepository;
import com.example.todolist.Entity.User;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, @Lazy PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User register(String name, String email, String rawPassword) {
        if (repo.existsByEmail(email)) throw new IllegalArgumentException("Email already registered");
        User u = User.builder().name(name).email(email).password(encoder.encode(rawPassword)).build();
        return repo.save(u);
    }
}
