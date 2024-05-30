package ru.netology.hibernate;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(HibernateApplication.class);
        application.run(args);
    }

    @Override
    @Transactional
    public void run(String... args) {
    }
}
