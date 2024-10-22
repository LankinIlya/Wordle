package com.wordle.demo.service;

import com.wordle.demo.repository.UserRepository;
import com.wordle.demo.repository.entity.UserEntity;
import com.wordle.demo.service.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public MyUser getUserByUsername(String login)
            throws UsernameNotFoundException {
        Optional<UserEntity> user = repository.findByLogin(login);

        if(user.isPresent())
            return new MyUser(user.get());
        else throw new UsernameNotFoundException(login + " not found");
    }
}
