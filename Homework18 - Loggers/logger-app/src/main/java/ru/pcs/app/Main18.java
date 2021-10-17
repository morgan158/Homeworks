package ru.pcs.app;

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
    private String hikariPoolSize;

    public static void main(String[] args) {
        Config con = new Config();
        HikariConfig config = new HikariConfig();

        config.setUsername(con.getUser());
        config.setPassword(con.getPassword());
        config.setDriverClassName(con.getDriver());
        config.setJdbcUrl(con.getUrl());

        Main18 main18 = new Main18();

        JCommander.newBuilder()
                .addObject(main18)
                .build()
                .parse(args);

        int max = Integer.parseInt(main18.hikariPoolSize);
        config.setMaximumPoolSize(max);
        HikariDataSource dataSource = new HikariDataSource(config);

        ProductsRepository productsRepository = new ProductsRepositoryJdbc(dataSource);

        ExecutorService service = Executors.newFixedThreadPool(max);

        for (int clientNumber = 0; clientNumber < max; clientNumber++) {
            service.submit(() -> {
                for (int i = 0; i < 20; i++) {
                    try {
                        System.out.println(productsRepository.findAll(i, 100));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        service.shutdown();

    }
}
