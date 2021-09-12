import java.util.ArrayList;
import java.util.regex.Pattern;

public class UserAuthorization implements UsersService {

    ArrayList<User> users = new ArrayList<User>();

    /*
     Метод signUp, наверное, лучше бы реализовать в отдельном классе для регистрации пользователей,
     но я решила не усложнять.
     */

    @Override
    public void signUp(String email, String password) {
        User user = new User();
        String patternEmail = "[a-zA-Z0-9]@[a-z].[a-z]";
        String patternPassword = "[a-zA-Z0-9]{8,}";

        /*
        Сначала проверяем, правильный ли адрес почты. Если нет, выбрасываем исключение.
         */
        if (Pattern.matches(patternEmail, email)) {
            user.setEmail(email);
        } else {
            throw new BadEmailException("Несуществующий адрес почты");
        }

        /*
        Тут проверяем, валидный ли пароль. Если да, устанавливаем пароль и добавляем пользователя в список,
        выводим сообщение о регистрации. Если нет - исключение.
         */

        if (Pattern.matches(patternPassword, password)) {
            user.setPassword(password);
            users.add(user);
            System.out.println("Пользователь успешно зарегистрирован");
        } else {
            throw new BadPasswordException("Пароль должен состоять из букв и цифр и быть больше 7 символов");
        }
    }

    /*
     */

    @Override
    public void signIn(String email, String password) {

        int temp = 0;

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                temp++;
                System.out.println("Пользователь успешно авторизован");
                break;
            }
        }

        /*
        Если такого пользователя нет в списке, выбрасываем исключение (использовала костыль temp,
        надеюсь, не злоупотребила).
         */

        if (temp == 0) {
            throw new UserNotFoundException("Нет такого пользователя");
        }
    }
}
