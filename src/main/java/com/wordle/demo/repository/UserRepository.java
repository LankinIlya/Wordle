package com.wordle.demo.repository;

import com.wordle.demo.repository.entity.UserEntity;
import com.wordle.demo.service.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<MyUser> findByLogin(String login);
}
