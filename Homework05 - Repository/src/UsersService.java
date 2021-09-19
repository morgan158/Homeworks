
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void signUp(String email, String password) {
        if (!usersRepository.existsByEmail(email)) {
            User user = new User(email, password);
            usersRepository.save(user);
        } else throw new IllegalArgumentException("Email already exists");
    }
}
