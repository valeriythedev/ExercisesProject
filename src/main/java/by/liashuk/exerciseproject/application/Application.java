package by.liashuk.exerciseproject.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("by.liashuk.exerciseproject.**")
@EnableJpaRepositories("by.liashuk.exerciseproject.repository")
@EntityScan("by.liashuk.exerciseProject.model")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
