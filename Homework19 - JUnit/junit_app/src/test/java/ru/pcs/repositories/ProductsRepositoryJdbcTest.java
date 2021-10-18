package ru.pcs.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class ProductsRepositoryJdbcTest {
    private ProductsRepositoryJdbc productsRepositoryJdbc;

    private DataSource dataSource;
    @BeforeEach
    public void setUp() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .build();
        this.productsRepositoryJdbc = new ProductsRepositoryJdbc(dataSource);
    }

    @Test
    public void test(){
        System.out.println(productsRepositoryJdbc.findAll(2, 5));
    }
}