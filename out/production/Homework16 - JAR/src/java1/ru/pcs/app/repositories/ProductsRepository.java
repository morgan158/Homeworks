package java1.ru.pcs.app.repositories;

import java1.ru.pcs.app.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {
    Optional<Product> findById(Long id);
    List<Product> findAll(int page, int size);

    void save(Product product);
    void update(Product product);

    void delete(Product product);
    void deleteById(Long id);
}
