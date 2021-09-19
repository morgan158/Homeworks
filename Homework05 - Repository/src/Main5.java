import java.util.Scanner;

public class Main5 {

    public static void main(String[] args) {
        IdGenerator idGenerator = new IdGeneratorFileImpl("users_id.txt");
        UsersRepository usersRepository = new UsersRepositoryFileImpl("users.txt", idGenerator);
        UsersService usersService = new UsersService(usersRepository);

        Scanner scanner = new Scanner(System.in);

        int c = 0;

//        while (c < 0) {
//            String email = scanner.nextLine();
//            String password = scanner.nextLine();
//
//            usersService.signUp(email, password);
//            c++;
//        }

        System.out.println(usersRepository.count());
        System.out.println(usersRepository.findAll().toString());
        System.out.println(usersRepository.existsByEmail("dfdf"));
        System.out.println(usersRepository.findByEmail(""));
        usersRepository.update(new User("", "dfdf111"));
    }
}
