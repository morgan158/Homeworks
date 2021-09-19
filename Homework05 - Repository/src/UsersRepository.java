import java.util.List;
import java.util.Optional;


public interface UsersRepository {
    void update(User user);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    void delete(User user);
    void save(User user);
    int count();

    boolean existsByEmail(String email);


}
