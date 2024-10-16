package com.wordle.demo.service.model;

import com.wordle.demo.repository.entity.UserEntity;

import java.util.Objects;

public final class MyUser {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public MyUser(String login, String password) {
        this.login = login;
        this.password = password;
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
