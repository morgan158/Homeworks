package java1.ru.pcs.app;

import java1.ru.pcs.app.repositories.ProductsRepository;
import java1.ru.pcs.app.repositories.ProductsRepositoryJdbc;
import java1.ru.pcs.config.Config;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Parameters(separators = "=")
public class Main16 {

    @Parameter(names = {"--threadsCount"})
    private int threadsCount;

    public static void main(String[] args) {
        Config con = new Config();
        HikariConfig config = new HikariConfig();

        config.setUsername(con.getUser());
        config.setPassword(con.getPassword());
        config.setDriverClassName(con.getDriver());
        config.setJdbcUrl(con.getUrl());

        Main16 main16 = new Main16();

        JCommander.newBuilder()
                .addObject(main16)
                .build()
                .parse(args);

        int max = main16.threadsCount;
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
