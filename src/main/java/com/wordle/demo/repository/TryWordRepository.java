package com.wordle.demo.repository;

import com.wordle.demo.repository.entity.TryWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TryWordRepository extends JpaRepository<TryWordEntity, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM trywords " +
                    "WHERE trywords.game_id = :game_id " +
                    "ORDER BY trywords.try_number"
    )
    List<TryWordEntity> findByGameId(@Param("game_id") Long gameId);

    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(*) " +
                    "FROM trywords " +
                    "WHERE trywords.game_id = :game_id "
    )
    Long getCountByGameId(@Param("game_id") Long gameId);
}
