package com.ztc.testcenter;

import com.ztc.testcenter.questionsgenerator.QuestionsGenerator;
import com.ztc.testcenter.questionsgenerator.UsersGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Yubar on 1/15/2017.
 */

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TestCenterApplication implements CommandLineRunner {

    private static final Boolean GENERATE_QUESTIONS = false;
    private static final Boolean GENERATE_USERS = true;

    @Autowired
    QuestionsGenerator questionsGenerator;

    @Autowired
    UsersGenerator usersGenerator;

    public static void main(String[] args) {
        SpringApplication.run(TestCenterApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }

    @Override
    public void run(String... strings) throws Exception {
        if (GENERATE_QUESTIONS)
            questionsGenerator.generateAll(1000);
        if (GENERATE_USERS)
            usersGenerator.generateUsers(1000);
    }
}
