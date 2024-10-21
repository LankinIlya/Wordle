package com.wordle.demo.repository;

import com.wordle.demo.repository.entity.GameEntity;
import com.wordle.demo.repository.entity.TryWordEntity;
import com.wordle.demo.repository.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<WordEntity, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM games " +
                    "WHERE games.user_d = :user_id AND games.is_active")
    Optional<GameEntity> findByUserId(@Param("user_id") Long userId);
}
