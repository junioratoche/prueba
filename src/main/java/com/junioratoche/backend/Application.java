package com.junioratoche.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.junioratoche.backend")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }    

}
