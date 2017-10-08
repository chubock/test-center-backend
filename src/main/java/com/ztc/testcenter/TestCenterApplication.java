package com.ztc.testcenter;

import com.ztc.testcenter.gre.generator.QuestionTemplatesGenerator;
import com.ztc.testcenter.gre.generator.QuestionsGenerator;
import com.ztc.testcenter.gre.generator.SectionTemplatesGenerator;
import com.ztc.testcenter.gre.generator.TestTemplatesGenerator;
import com.ztc.testcenter.shop.generator.ProductsGenerator;
import com.ztc.testcenter.user.generator.UsersGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Created by Yubar on 1/15/2017.
 */

@EnableAsync
@EnableCaching
@EnableResourceServer
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class TestCenterApplication implements CommandLineRunner {

    private static final Boolean GENERATE_PRODUCTS = false;
    private static final Boolean GENERATE_QUESTIONS = false;
    private static final Boolean GENERATE_USERS = false;
    private static final Boolean GENERATE_QUESTION_TEMPLATES = false;
    private static final Boolean GENERATE_SECTION_TEMPLATES = false;
    private static final Boolean GENERATE_TEST_TEMPLATES = false;

    private final ProductsGenerator productsGenerator;
    private final QuestionsGenerator questionsGenerator;
    private final UsersGenerator usersGenerator;
    private final QuestionTemplatesGenerator questionTemplatesGenerator;
    private final SectionTemplatesGenerator sectionTemplatesGenerator;
    private final TestTemplatesGenerator testTemplatesGenerator;

    @Autowired
    public TestCenterApplication(ProductsGenerator productsGenerator, QuestionsGenerator questionsGenerator, UsersGenerator usersGenerator, QuestionTemplatesGenerator questionTemplatesGenerator, SectionTemplatesGenerator sectionTemplatesGenerator, TestTemplatesGenerator testTemplatesGenerator) {
        this.productsGenerator = productsGenerator;
        this.questionsGenerator = questionsGenerator;
        this.usersGenerator = usersGenerator;
        this.questionTemplatesGenerator = questionTemplatesGenerator;
        this.sectionTemplatesGenerator = sectionTemplatesGenerator;
        this.testTemplatesGenerator = testTemplatesGenerator;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestCenterApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        if (GENERATE_PRODUCTS)
            productsGenerator.createProducts();
        if (GENERATE_QUESTION_TEMPLATES)
            questionTemplatesGenerator.createAll();
        if (GENERATE_USERS)
            usersGenerator.generateUsers();
        if (GENERATE_SECTION_TEMPLATES)
            sectionTemplatesGenerator.createAll();
        if (GENERATE_TEST_TEMPLATES)
            testTemplatesGenerator.createAll();
        if (GENERATE_QUESTIONS)
            questionsGenerator.generateAll(3000, 1000);
    }
}
