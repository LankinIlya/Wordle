package com.wordle.demo.repository.entity;

import com.wordle.demo.service.model.MyUser;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;
    private Integer games;
    private Integer wins;

    public UserEntity() {}
    public UserEntity(String login, String password, Integer games, Integer wins) {
        this.login = login;
        this.password = password;
        this.games = games;
        this.wins = wins;

    }
    public UserEntity(MyUser user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
