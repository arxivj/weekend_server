package com.arxivj.weekend_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WeekendServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeekendServerApplication.class, args);
    }

}
