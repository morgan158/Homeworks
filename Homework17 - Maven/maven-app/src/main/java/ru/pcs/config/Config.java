package ru.pcs.config;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
     private String driver;
     private String user;
     private String password;
     private String url;

    public Config() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("resources\\threads.properties"));
            this.driver = properties.getProperty("executor.threads.driver");
            this.user = properties.getProperty("executor.threads.user");
            this.password = properties.getProperty("executor.threads.password");
            this.url = properties.getProperty("executor.threads.url");
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String getDriver() {
        return driver;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }
}
