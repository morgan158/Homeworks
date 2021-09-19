import java.util.Scanner;

public class Main5 {

    public static void main(String[] args) {
        IdGenerator idGenerator = new IdGeneratorFileImpl("users_id.txt");
        UsersRepository usersRepository = new UsersRepositoryFileImpl("users.txt", idGenerator);
        UsersService usersService = new UsersService(usersRepository);

        Scanner scanner = new Scanner(System.in);


        usersService.signUp("ss", "ss");
        usersService.signUp("sdsd", "sdsd");
        usersService.signUp("ee", "ee");
        


        System.out.println(usersRepository.count());
        System.out.println(usersRepository.findAll().toString());
        System.out.println(usersRepository.existsByEmail("ee"));
        System.out.println(usersRepository.findByEmail("ss"));
        usersRepository.update(new User("sdsd", "dfdf111"));
        usersRepository.delete(new User("ss", "ss"));
    }
}
