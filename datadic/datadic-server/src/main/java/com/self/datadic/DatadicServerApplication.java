package com.self.datadic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.self.*")
public class DatadicServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatadicServerApplication.class, args);
    }

}
