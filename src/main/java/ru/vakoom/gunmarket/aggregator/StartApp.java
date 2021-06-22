package ru.vakoom.gunmarket.aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan("ru.vakoom.gunmarket.commondatalayer.model")
@EnableJpaRepositories({
        "ru.vakoom.gunmarket.commondatalayer.repo",
        "ru.vakoom.querybuilder"
})
@SpringBootApplication(scanBasePackages = {
        "ru.vakoom.gunmarket.aggregator",
        "ru.vakoom.querybuilder"
})
public class StartApp {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class);
    }

}
