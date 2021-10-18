package ru.pcs.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class ProductsRepositoryJdbcTest {
    private ProductsRepositoryJdbc productsRepositoryJdbc;

    @BeforeEach
    public void setUp() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        this.productsRepositoryJdbc = new ProductsRepositoryJdbc(dataSource);
    }

    @Test
    public void testDatabase(){
        assertTrue(productsRepositoryJdbc.findAll(0, 5).size()>0);
    }
    @Test
    public void checkName(){
        assertTrue(productsRepositoryJdbc.findAll(0, 5).get(0).getName().length()<255);
    }
}