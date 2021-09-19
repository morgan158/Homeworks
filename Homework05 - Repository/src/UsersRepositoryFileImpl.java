import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class UsersRepositoryFileImpl implements UsersRepository {

    private final String fileName;
    private final IdGenerator idGenerator;

    public UsersRepositoryFileImpl(String fileName, IdGenerator idGenerator) {
        this.fileName = fileName;
        this.idGenerator = idGenerator;
    }

    @Override
    public void update(User user) {
        /**
         * Если в файле уже есть такой пользователь, у него нужно обновить данные
         */
        List<User> users = this.findAll();   // создаем список всех наших объектов
        idGenerator.clear();                // сбрасываем счетчик в users_id.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) { //очищаем файл user.txt
            writer.write("");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        for (User userOfList : users) {  // заново заполняем всеми объектами, кроме указанного в параметре метода
            if (!user.getEmail().equals(userOfList.getEmail())) { // проверяем эквивалентность id
                this.save(userOfList);                  // если не совпадает id, сохраняем в файл как есть
            } else {
                this.save(user);                       // если совпадает, сохраняем новую версию
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNext()){
                String stringUser = scanner.nextLine();
                User user = new User(stringUser.substring(stringUser.indexOf('|') + 1, stringUser.lastIndexOf('|')),
                        stringUser.substring(stringUser.lastIndexOf('|') + 1));
                if (user.getEmail().equals(email)) {
                    user.setId(Integer.parseInt(stringUser.substring(0, stringUser.indexOf('|'))));
                    return Optional.of(user);
                }
            }
            return Optional.empty();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public List<User> findAll() {
        List<User> listUsers = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNext()){
                String stringUser = scanner.nextLine();
                User user = new User(stringUser.substring(stringUser.indexOf('|') + 1, stringUser.lastIndexOf('|')),
                                    stringUser.substring(stringUser.lastIndexOf('|')+1));

                listUsers.add(user);
            }
            return listUsers;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(User user) {
        List<User> users = this.findAll();   // создаем список всех наших объектов
        idGenerator.clear();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) { //очищаем файл user.txt
            writer.write("");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        for (User userOfList : users) {  // заново заполняем всеми объектами, кроме указанного в параметре метода
            if (!(user.getEmail().equals(userOfList.getEmail()) &&
                    user.getPassword().equals(userOfList.getPassword()))) { // проверяем эквивалентность id
                this.save(userOfList);
            }
        }
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            user.setId(idGenerator.next());
            String userAsLine = user.getId() + "|" + user.getEmail() + "|" + user.getPassword();
            writer.write(userAsLine);
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public int count() {
        int count = 0;
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNext()){
                scanner.nextLine();
                count++;
            }
            return count;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {
            while (scanner.hasNext()){
                String currentUser = scanner.nextLine();
                String currentEmail = currentUser.substring(currentUser.indexOf('|')+1, currentUser.lastIndexOf('|'));
                if (email.equals(currentEmail)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
