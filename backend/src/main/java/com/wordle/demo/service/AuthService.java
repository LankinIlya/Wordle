package com.wordle.demo.service;

import com.wordle.demo.repository.UserRepository;
import com.wordle.demo.repository.entity.UserEntity;
import com.wordle.demo.service.model.MyUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(MyUser user) {
        repository.save(new UserEntity(user.getLogin(),
                passwordEncoder.encode(user.password()), 0, 0));
    }
}
