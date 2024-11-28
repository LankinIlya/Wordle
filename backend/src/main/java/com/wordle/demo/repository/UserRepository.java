package com.wordle.demo.repository;

import com.wordle.demo.repository.entity.GameEntity;
import com.wordle.demo.repository.entity.UserEntity;
import com.wordle.demo.service.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
//    @Query(
//            nativeQuery = true,
//            value = "SELECT * " +
//                    "FROM users " +
//                    "WHERE users.user_id = :user_id ")
//    Optional<UserEntity> findById(@Param("user_id") Long userId);

    //    @Query(
//            nativeQuery = true,
//            value = "SELECT * " +
//                    "FROM users " +
//                    "WHERE users.login = :login ")
    Optional<UserEntity> findByLogin(@Param("login") String login);

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM users " +
                    "WHERE users.id != 1 " +
                    "ORDER BY users.wins DESC " +
                    "LIMIT 10 ")
    ArrayList<UserEntity> getTopByWins();


        @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM users " +
                    "WHERE users.games > 0 " +
                    "AND users.id != 1 " +
                    "ORDER BY (users.wins * 1.0 / users.games ) DESC " +
                    "LIMIT 10")

    ArrayList<UserEntity> getTopByRatio();
//
//    @Query(
//            nativeQuery = true,
//            value = "SELECT * " +
//                    "FROM users " +
//                    "WHERE users.games > 0 " +
//                    "AND users.id != 1 " +
//                    "ORDER BY users.games DESC " +
//                    "LIMIT 10")
//    ArrayList<UserEntity> getTopByRatio();


    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "UPDATE users " +
                    "SET games = games + 1 " +
                    "WHERE id = :user_id ")
    void incGames(@Param("user_id") Long userId);


    @Modifying
    @Transactional
    @Query(
            nativeQuery = true,
            value = "UPDATE users " +
                    "SET wins = wins + 1 " +
                    "WHERE id = :user_id  ")
    void incWins(@Param("user_id") Long userId);
}



//    @Query(
//            nativeQuery = true,
//            value = "SELECT * " +
//                    "FROM users " +
//                    "WHERE users.games > 0 " +
//                    "AND users.id != 1 " +
//                    "ORDER BY (users.wins * 1.0 / users.games ) DESC " +
//                    "LIMIT 10")

//    ArrayList<UserEntity> getTopByRatio();


//select * from users
//        where users.games > 0
//ORDER BY users.games DESC;
//
//
//SELECT *
//        FROM users
//        WHERE users.games > 0
//        AND users.id != 1
//        ORDER BY users.games DESC
//        LIMIT 10