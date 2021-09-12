import java.util.ArrayList;

public class UserAuthorization implements UsersService {
    ArrayList<User> users = new ArrayList<User>();

    @Override
    public void signUp(String email, String password) {
        if (email.contains("@") && password.length() > 7) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            users.add(user);
            System.out.println("Пользователь успешно зарегистрирован");
        }
    }

    @Override
    public void signIn(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Пользователь успешно авторизован");
                break;
            } else {
                System.out.println("Пользователя нет в базе данных");
            }
        }
    }
}
