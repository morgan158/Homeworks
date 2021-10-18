package ru.pcs.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pcs.config.Config;
import ru.pcs.repositories.ProductsRepository;
import ru.pcs.repositories.ProductsRepositoryJdbc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Parameters(separators = "=")
public class Main19 {

    @Parameter(names = {"--hikari-pool-size"})
    private int hikariPoolSize;
    private static final Logger logger = LoggerFactory.getLogger(ProductsRepositoryJdbc.class);

    public static void main(String[] args) {
        Config con = new Config();
        HikariConfig config = new HikariConfig();

        config.setUsername(con.getUser());
        config.setPassword(con.getPassword());
        config.setDriverClassName(con.getDriver());
        config.setJdbcUrl(con.getUrl());

        Main19 main19 = new Main19();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(main19)
                .build();

        jCommander.parse(args);
        int max = main19.hikariPoolSize;
        logger.debug("Arguments received, pool size is " + max);

        config.setMaximumPoolSize(max);
        logger.info("Configuration built");

        HikariDataSource dataSource = new HikariDataSource(config);
        logger.info("Connection with database");

        ProductsRepository productsRepository = new ProductsRepositoryJdbc(dataSource);
        logger.info("Repository created");

        ExecutorService service = Executors.newFixedThreadPool(max);

        for (int clientNumber = 0; clientNumber < max; clientNumber++) {
            logger.debug("Client " + clientNumber + " submitted");
            service.submit(() -> {
                for (int i = 0; i < 5; i++) {
                    try {
                        logger.debug(" " + (i*20) + " products found");
                        System.out.println(productsRepository.findAll(i, 20));
                    } catch (Exception e) {
                        logger.error("Search`s error");
                        e.printStackTrace();
                    }
                }
            });
        }
        service.shutdown();

        /*
        Закомментированы вызовы методов для тестирования
         */
        // Добавление нового товара

//        Product product = new Product("Кустарники", "Черная смородина", 3000.0, 4, 10);
//        productsRepository.save(product);
//        System.out.println(product);
//
//        // Найти все (пагинация)
//
//        System.out.println(productsRepository.findAll(1, 5));
//
//        // Поиск по ID
//
//        System.out.println(productsRepository.findById(5L));
//
//        // Обновление данных о товаре
//        Optional<Product> productOptional = productsRepository.findById(2L);
//        productOptional.ifPresent(product1 -> {
//            product1.setCategory("Хвойные");
//            product1.setDiscount(0);
//            product1.setStock(1);
//            product1.setPrice(4000.0);
//            product1.setName("Елка");
//            productsRepository.update(product1);
//            System.out.println(product1);
//        });
//
//
//
//        System.out.println("Товар " + productsRepository.findById(37L).get().getId() + " будет удален");
//
//        // Удаление товара по ID
//        productsRepository.deleteById(37L);
//
//        System.out.println("Товар " + product.getId() + " будет удален");
//
//        // Удаление товара по объекту (объект объявлен выше)
//        productsRepository.delete(product);

    }
}
