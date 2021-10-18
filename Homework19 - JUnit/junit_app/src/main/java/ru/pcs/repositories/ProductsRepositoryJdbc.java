package ru.pcs.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pcs.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ProductsRepositoryJdbc implements ProductsRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductsRepositoryJdbc.class);

    //language=SQL
    //for deleteById and delete

    private static final String SQL_DELETE_BY_ID = "delete from product where id = ?;";

    //language=SQL
    // for findById

    private static final String SQL_SELECT_BY_ID = "select * from product where id = ?";

    //language=SQL
    // for save

    private static final String SQL_INSERT = "insert into product(category, name, price, stock, discount) values (?, ?, ?, ?, ?)";

    //language=SQL
    // for update

    private static final String SQL_UPDATE = "update product set category = ?, name = ?, price = ?, stock = ?, discount = ? where id = ?;";

    //language=SQL
    // for findAll

    private static final String SQL_SELECT_ALL = "select * from product order by id limit ? offset ?;";

    private final DataSource dataSource;

    private static final Function<ResultSet, Product> productMapper = resultSet -> {
        try {
            Long id = resultSet.getLong("id");
            String category = resultSet.getString("category");
            String name = resultSet.getString("name");
            Double price = resultSet.getDouble("price");
            Integer stock = resultSet.getInt("stock");
            Integer discount = resultSet.getInt("discount");
            return new Product(id, category, name, price, stock, discount);
        } catch (SQLException e) {
            logger.error("Caught SQLException in method productMapper");
            throw new IllegalArgumentException(e);
        }

    };

    public ProductsRepositoryJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Product> findById(Long productId) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setLong(1, productId);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    logger.debug("Object " + productId + " found in database");
                    return Optional.of(productMapper.apply(resultSet));
                }
                logger.warn("Object not found in database");
                return Optional.empty();
            }

        } catch (SQLException e) {
            logger.error("caught SQLException in method findById()");
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Product> findAll(int page, int size) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
            logger.info("created connection");
            statement.setInt(1, size);
            statement.setInt(2, size * page);
            logger.debug("select all with page = {}, size = {}", page, size);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(productMapper.apply(resultSet));
                }
                logger.info("finish query");
                return products;
            }

        } catch (SQLException e) {
            logger.error("Caught SQLException in method findAll()");
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void save(Product product) {
        try(Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getCategory());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getStock());
            statement.setInt(5, product.getDiscount());
            int affectedRow = statement.executeUpdate();

            if (affectedRow != 1) {
                logger.warn("Can`t insert product");
                throw new SQLException("Can`t insert product");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong("id"));
                logger.debug("For product " + product.getId() + " generated key in database");
            } else {
                logger.warn("Can`t get id");
                throw new SQLException("Can`t get id");
            }
            logger.debug("Product " + product.getId() + " " + " added in database");
        } catch (SQLException e) {
            logger.error("caught SQLException in method save()");
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(Product product) {
        try(Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, product.getCategory());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getStock());
            statement.setInt(5, product.getDiscount());
            statement.setLong(6, product.getId());
            int affectedRow = statement.executeUpdate();
            logger.debug("Product " + product.getId() + " update");

            if (affectedRow != 1) {
                logger.warn("Can`t update product");
                throw new SQLException("Can`t update product");
            }
        } catch (SQLException e) {
            logger.error("Caught SQLException in method update()");
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, product.getId());
            statement.executeUpdate();
            logger.debug("Product " + product.getId() + " delete");
        } catch (SQLException e) {
            logger.error("Caught SQLException in method delete(). Object not found in database");
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            logger.debug("Product " + id + " delete");
        } catch (SQLException e) {
            logger.error("Caught SQLException in method deleteByID. Object not found in database");
            throw new IllegalArgumentException(e);
        }
    }
}
