package com.ztc.testcenter;

import com.ztc.testcenter.questionsgenerator.QuestionsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Yubar on 1/15/2017.
 */

@SpringBootApplication
public class TestCenterApplication implements CommandLineRunner {

    private static final boolean GENERATE_QUESTIONS = true;

    @Autowired
    QuestionsGenerator questionsGenerator;

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
    }
}
