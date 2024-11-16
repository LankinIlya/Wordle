package com.wordle.demo.repository;

import com.wordle.demo.repository.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Long> {
    Optional<WordEntity> findByText(String text);
}
