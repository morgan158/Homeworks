package ru.pcs.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pcs.repositories.ProductsRepository;
import ru.pcs.repositories.ProductsRepositoryJdbc;
import ru.pcs.config.Config;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Parameters(separators = "=")
public class Main18 {

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

        Main18 main18 = new Main18();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(main18)
                .build();

        jCommander.parse(args);
        int max = main18.hikariPoolSize;
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

    }
}
