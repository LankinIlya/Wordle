package com.wordle.demo.service;

import com.wordle.demo.config.MyUserDetails;
import com.wordle.demo.repository.UserRepository;
import com.wordle.demo.service.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String login) 
            throws UsernameNotFoundException {
        Optional<MyUser> user = repository.findByLogin(login);
        return user.map(MyUserDetails::new)
                .orElseThrow(() ->
                        new UsernameNotFoundException(login + " not found"));
    }
}
