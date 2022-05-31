# **Задача № 1 Проверка доступа к ресурсу**

## **Цель**:
1. Создать программу для проверки доступа к ресурсу. Во время запуска программы нужно запросить логин или пароль пользователя. Если один из введеных параметров не совпадает (логин/пароль), то нужно выбросить checked исключение UserNotFoundException. Если возраст пользователя менее 18 лет, то нужно выбросить исключение AccessDeniedException, а если 18 и больше лет - вывести сообщение "Доступ предоставлен".


### *Пример*:

``` Пример 1
1.Создадим класс User, в котором будем хранить инфомрацию о логине, пароле и возрасте пользователя: class User, login, password, email, age;

2.Создадим класс исключение UserNotFoundException на основе базового класса Exception. Это исключение будем использовать, если пользователь ввел неверный логин или пароль;

3. Аналогичным образом создадим класс исключения AccessDeniedException;

4. Создадим класс Main, в котором создадим метод getUsers, этот метод должен возвращать список заранее созданных пользователей:
public static User[] getUsers() {
    User user1 = new User("jhon", "jhon@gmail.com", "pass", 24);
    ...
    return new User[]{user1, ...};
}

5. Создадим в классе Main метод getUserByLoginAndPassword(String login, String password), в этом методе нужно найти соответствие пары логина и пароля пользователя из массива, возвращаемого методом getUsers. Если пользователь не найден, выбрасываем исключение UserNotFoundException, если найден - возвращаем найденного пользователя:
public static User getUserByLoginAndPassword(String login, String password) throws UserNotFoundException {
    User[] users = getUsers();
    for (User user : users) {
        ...
    }
    throw new UserNotFoundException("User not found");    
} 

6.Создадим к классу Main еще один метод validateUser для проверки возрастра пользователя. Если возраст менее 18 лет, метод должен выбросить исключение AccessDeniedException:public static void validateUser(User user) throws AccessDeniedException;

7. Добавим последний метод в классе Main для запуска программы public static void main(String[] args) throws UserNotFoundException, AccessDeniedException В нем нужно запросить логин и пароль пользователя, проверить есть ли данная пара "логин и пароль" в массиве пользователей и последним шагом провалидировать возраст.
    public static void main(String[] args) throws UserNotFoundException, AccessDeniedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите логин");
        String login = scanner.nextLine();
        System.out.println("Введите пароль");
        String password = scanner.nextLine();

        //Проверить логин и пароль
        
        //Вызвать методы валидации пользователя
        
        System.out.println("Доступ предоставлен");
    }

Программа завершена. Отличная работа!
```

### **Моя реализация**:
1. Реализация осуществлена в парадигме ООП.
2. Создал структуру классов:

* **Program** - класс, отвечающий за запуск программы, путем инициирования метода *start()* с инициированием внутри себя
  вспомогательных ```void``` методов: 
  * *printMenu()* - выводит меню команд программы на экран; 
  * *demonstration()* - добавляет в ```ArrayList``` заготовленных пользователей;
  * *printUserList()* - выводит список пользователей на экран. 

#### Класс **Program**:
``` java
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
```

* **UserValidator** - класс, позволяющий валидировать пользователя по паре логин\пароль и возрасту. Содержит ```static void``` метод *validate()*, который валидирует пользователя.

#### Класс **UserValidator**:
``` java   
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
```

* **User** - класс, описывающий пользователя. Имеет метод *findUserByPairLoginPassword()*, возвращающий пользователя, если совпала пара логин\пароль. Также класс имеет переопределенный метод *toString()* и геттер-методы для доступа к полям: login, password, age;

#### Класс **User**:
```java
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
```

* **AccessDeniedException** - класс-исключение, проверяющий младше ли пользователь валидируемого возраста.

#### Класс **AccessDeniedException**:
```java
public class AccessDeniedException extends UserNotFoundException{
    public AccessDeniedException(String message) {
        super(message);
    }
}
```

* **UserNotFoundException** - класс-исключение, проверяющий есть ли пользователь в списке.

#### Класс **UserNotFoundException**:
```java
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
```

2. Использовал кодирование цвета текста (ANSI).

#### Класс **Utils**:
``` java
public class Utils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printDelim() {
        System.out.println(ANSI_GREEN + "*********************************************" + ANSI_RESET);
    }
}
```

3. Использовал ```try-catch```, чтобы избежать падение программы в исключения.

#### Метод *main()* в классе **Main**:
``` java
public class Main {
    public static void main(String[] args) throws UserNotFoundException {
        var program = new Program();
        program.start();
    }
}
```

## *Вывод в консоль*:

* меню:
``` 
Программа-валидатор предоставления доступа пользователю к ресурсу.
Возможные команды программы:
0 или выход: выход из программы.
1: ввести данные пользователя.
2: ввести логин и пароль, чтобы получить доступ к ресурсу.
3: демонстрация работоспособности метода.
```

* Демонстрация работы:
```
3
введите номер пользователя, его логин и пароль через пробел:
1. Пользователь: temny@gmail.com.
2. Пользователь: areak@mail.ru.
3. Пользователь: ivanov.i.i@list.ru.
4. Пользователь: kotamadeo@gmail.com.
Пользователь: temny@gmail.com не пройдет по возрасту. L: Temnitsky, P: Temnitsky1
Пользователь: areak@mail.ru достаточно ввести любой пароль, чтобы не пройти валидацию. L: Keara
Пользователь: ivanov.i.i@list.ru пройдет. L: Ivanov, P: ivanych123
Пользователь: kotamadeo@gmail.com пройдет. L: Kotamadeo, P: KoTAmadeo01
1 Temnitsky Temnitsky1
Exception in thread "main" com.gmail.at.kotamadeo.exceptions.AccessDeniedException: Доступ отклонен! Пользователь Temnitsky младше 18 лет!

3
введите номер пользователя, его логин и пароль через пробел:
1. Пользователь: temny@gmail.com.
2. Пользователь: areak@mail.ru.
3. Пользователь: ivanov.i.i@list.ru.
4. Пользователь: kotamadeo@gmail.com.
Пользователь: temny@gmail.com не пройдет по возрасту. L: Temnitsky, P: Temnitsky1
Пользователь: areak@mail.ru достаточно ввести любой пароль, чтобы не пройти валидацию. L: Keara
Пользователь: ivanov.i.i@list.ru пройдет. L: Ivanov, P: ivanych123
Пользователь: kotamadeo@gmail.com пройдет. L: Kotamadeo, P: KoTAmadeo01
2 Keara Keara
Exception in thread "main" com.gmail.at.kotamadeo.exceptions.UserNotFoundException: Ошибка! Пользователь: areak@mail.ru не найден! Введен неверный логин или пароль!

1
Введите данные пользователя (логин, пароль, e-mail и возраст) через пробел: 
kotamadeo kotamadeo1 kot@mail.ru 26

2
Введите логин и пароль через пробел:
kotamadeo kotamadeo1
Доступ предоставлен пользователю kotamadeo!
```