package com.example.bst;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
@EntityScan(basePackages = "com.example.bst.model")
public class BstApplication {

    public static void main(String[] args) {
        SpringApplication.run(BstApplication.class, args);
    }

    @Bean
    public CommandLineRunner testDatabase(DataSource dataSource) {
        return args -> {
            System.out.println("Testing database connection...");
            System.out.println("DataSource: " + dataSource.getConnection().getMetaData().getURL());
        };
    }
}
