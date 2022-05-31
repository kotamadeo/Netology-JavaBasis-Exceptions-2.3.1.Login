package com.gmail.at.kotamadeo;

import com.gmail.at.kotamadeo.exceptions.UserNotFoundException;

import java.util.List;

public class User {
    private String login;
    private String password;
    private String email;
    private int age;

    public User() {
    }

    public User(String login, String password, String email, int age) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public User findUserByPairLoginPassword(List<User> users, String login, String password) throws UserNotFoundException {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new UserNotFoundException("Ошибка! " + login + " не найден! " + "Введен неверный логин или пароль!");
    }

    @Override
    public String toString() {
        return "Пользователь: " + login;
    }
}
