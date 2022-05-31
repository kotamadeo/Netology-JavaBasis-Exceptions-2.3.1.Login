package com.gmail.at.kotamadeo.program;

import com.gmail.at.kotamadeo.User;
import com.gmail.at.kotamadeo.exceptions.UserNotFoundException;
import com.gmail.at.kotamadeo.utils.UserValidator;
import com.gmail.at.kotamadeo.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    private final Scanner scanner = new Scanner(System.in);
    private final User user = new User();
    private final List<User> users = new ArrayList<>();

    public void start() throws UserNotFoundException {
        String input;
        String[] allInput;
        while (true) {
            try {
                printMenu();
                input = scanner.nextLine();
                if ("выход".equalsIgnoreCase(input) || "0".equals(input)) {
                    scanner.close();
                    break;
                } else {
                    var operationNumber = Integer.parseInt(input);
                    switch (operationNumber) {
                        case 1:
                            System.out.println(Utils.ANSI_BLUE + "Введите данные пользователя " +
                                    "(логин, пароль, e-mail и возраст) через пробел: " + Utils.ANSI_RESET);
                            allInput = scanner.nextLine().split(" ");
                            users.add(new User(allInput[0], allInput[1], allInput[2], Integer.parseInt(allInput[3])));
                            break;
                        case 2:
                            System.out.println(Utils.ANSI_BLUE + "Введите логин и пароль через пробел:" +
                                    Utils.ANSI_RESET);
                            allInput = scanner.nextLine().split(" ");
                            User currentUser = user.findUserByPairLoginPassword(users, allInput[0], allInput[1]);
                            UserValidator.validate(currentUser, allInput[0], allInput[1]);
                            break;
                        case 3:
                            System.out.println(Utils.ANSI_BLUE + "введите номер пользователя, его логин и пароль " +
                                    "через пробел:" + Utils.ANSI_RESET);
                            demonstration();
                            allInput = scanner.nextLine().split(" ");
                            UserValidator.validate(users.get(Integer.parseInt(allInput[0]) - 1), allInput[1],
                                    allInput[2]);
                            break;
                        default:
                            System.out.println(Utils.ANSI_RED + "Вы ввели неверный номер операции!" + Utils.ANSI_RESET);
                    }
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println(Utils.ANSI_RED + "Ошибка ввода!" + Utils.ANSI_RESET);
            }
        }
    }

    private static void printMenu() {
        System.out.println(Utils.ANSI_YELLOW + "Программа-валидатор предоставления доступа пользователю к ресурсу." +
                Utils.ANSI_RESET);
        System.out.println(Utils.ANSI_PURPLE + "Возможные команды программы:" + Utils.ANSI_RESET);
        System.out.println("0 или выход: выход из программы.");
        System.out.println("1: ввести данные пользователя.");
        System.out.println("2: ввести логин и пароль, чтобы получить доступ к ресурсу.");
        System.out.println("3: демонстрация работоспособности метода.");
    }

    private void demonstration() {
        users.add(new User("Temnitsky", "Temnitsky1", "temny@gmail.com", 17));
        users.add(new User("Keara", "", "areak@mail.ru", 15));
        users.add(new User("Ivanov", "ivanych123", "ivanov.i.i@list.ru", 18));
        users.add(new User("Kotamadeo", "KoTAmadeo01", "kotamadeo@gmail.com", 26));
        printUsersList(users);
        System.out.println(users.get(0) + " не пройдет по возрасту. L: Temnitsky, P: Temnitsky1");
        System.out.println(users.get(1) + " достаточно ввести любой пароль, чтобы не пройти валидацию. L: Keara");
        System.out.println(users.get(2) + " пройдет. L: Ivanov, P: ivanych123");
        System.out.println(users.get(3) + " пройдет. L: Kotamadeo, P: KoTAmadeo01");
    }

    private static void printUsersList(List<User> users) {
        if (!users.isEmpty()) {
            for (var i = 0; i < users.size(); i++) {
                System.out.printf("%s%s. %s.%s%n", Utils.ANSI_PURPLE, (i + 1), users.get(i), Utils.ANSI_RESET);
            }
        } else {
            System.out.println(Utils.ANSI_RED + "Список пользователей пуст!" + Utils.ANSI_RESET);
        }
    }
}
