package com.ztc.testcenter;

import com.ztc.testcenter.generator.QuestionTemplatesGenerator;
import com.ztc.testcenter.generator.QuestionsGenerator;
import com.ztc.testcenter.generator.SectionTemplatesGenerator;
import com.ztc.testcenter.generator.UsersGenerator;
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

@SpringBootApplication
public class TestCenterApplication implements CommandLineRunner {

    private static final Boolean GENERATE_QUESTIONS = false;
    private static final Boolean GENERATE_USERS = false;
    private static final Boolean GENERATE_QUESTION_TEMPLATES = false;
    private static final Boolean GENERATE_SECTION_TEMPLATES = false;

    @Autowired
    QuestionsGenerator questionsGenerator;

    @Autowired
    UsersGenerator usersGenerator;

    @Autowired
    QuestionTemplatesGenerator questionTemplatesGenerator;

    @Autowired
    SectionTemplatesGenerator sectionTemplatesGenerator;

    public static void main(String[] args) {
        SpringApplication.run(TestCenterApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        if (GENERATE_QUESTION_TEMPLATES)
            questionTemplatesGenerator.createAll();
        if (GENERATE_QUESTIONS)
            questionsGenerator.generateAll(1000);
        if (GENERATE_USERS)
            usersGenerator.generateUsers(1000);
        if (GENERATE_SECTION_TEMPLATES)
            sectionTemplatesGenerator.createAll();
    }
}
