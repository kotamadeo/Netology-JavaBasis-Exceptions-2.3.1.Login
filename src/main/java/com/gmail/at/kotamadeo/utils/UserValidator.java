package com.gmail.at.kotamadeo.utils;

import com.gmail.at.kotamadeo.User;
import com.gmail.at.kotamadeo.exceptions.AccessDeniedException;
import com.gmail.at.kotamadeo.exceptions.UserNotFoundException;

public class UserValidator {
    private static final int ACCESS_AGE = 18;

    public static void validate(User user, String login, String password) throws UserNotFoundException {
        var isValidPair = user.getLogin().equals(login) && user.getPassword().equals(password);
        if (!isValidPair) {
            throw new UserNotFoundException("Ошибка! " + user + " не найден! " + "Введен неверный логин или пароль!");
        }
        if (user.getAge() < ACCESS_AGE) {
            throw new AccessDeniedException("Доступ отклонен! " + user + " младше " + ACCESS_AGE + " лет!");
        }
        System.out.println(Utils.ANSI_GREEN + "Доступ предоставлен пользователю " + login + "!" + Utils.ANSI_RESET);
    }
}
