package com.wordle.demo.service.model;

import com.wordle.demo.repository.entity.UserEntity;

import java.util.Objects;

public final class MyUser {
    public static final Long ANONYMOUS_ID = 1L;
    private Long id;
    private String login;
    private String password;
    private Integer games;
    private Integer wins;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public Integer getGames() {
        return games;
    }

    public Integer getWins() {
        return wins;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public MyUser(String login, String password, Integer games, Integer wins) {
        this.login = login;
        this.password = password;
        this.games = games;
        this.wins = wins;
    }

    public MyUser(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.login = userEntity.getLogin();
        this.password = userEntity.getPassword();
        this.games = userEntity.getGames();
        this.wins = userEntity.getWins();

    }

    public String login() {
        return login;
    }

    public String password() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (MyUser) obj;
        return Objects.equals(this.login, that.login) &&
                Objects.equals(this.password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "MyUser[" +
                "login=" + login + ", " +
                "password=" + password + ']';
    }

}
