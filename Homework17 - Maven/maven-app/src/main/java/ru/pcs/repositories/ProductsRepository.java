package ru.pcs.repositories;

import ru.pcs.models.Product;

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
