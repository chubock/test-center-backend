package com.ztc.testcenter;

import com.ztc.testcenter.generator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.UUID;

/**
 * Created by Yubar on 1/15/2017.
 */

@EnableCaching
@SpringBootApplication
public class TestCenterApplication implements CommandLineRunner {

    private static final Boolean GENERATE_PRODUCTS = false;
    private static final Boolean GENERATE_ADMIN_ROLE = false;
    private static final Boolean GENERATE_USER_ROLE = false;
    private static final Boolean GENERATE_AUTHORITIES = false;
    private static final Boolean GENERATE_QUESTIONS = false;
    private static final Boolean GENERATE_USERS = false;
    private static final Boolean GENERATE_QUESTION_TEMPLATES = false;
    private static final Boolean GENERATE_SECTION_TEMPLATES = false;
    private static final Boolean GENERATE_TEST_TEMPLATES = false;

    private final ProductsGenerator productsGenerator;
    private final RolesGenerator rolesGenerator;
    private final AuthoritiesGenerator authoritiesGenerator;
    private final QuestionsGenerator questionsGenerator;
    private final UsersGenerator usersGenerator;
    private final QuestionTemplatesGenerator questionTemplatesGenerator;
    private final SectionTemplatesGenerator sectionTemplatesGenerator;
    private final TestTemplatesGenerator testTemplatesGenerator;

    @Autowired
    public TestCenterApplication(ProductsGenerator productsGenerator, RolesGenerator rolesGenerator, AuthoritiesGenerator authoritiesGenerator, QuestionsGenerator questionsGenerator, UsersGenerator usersGenerator, QuestionTemplatesGenerator questionTemplatesGenerator, SectionTemplatesGenerator sectionTemplatesGenerator, TestTemplatesGenerator testTemplatesGenerator) {
        this.productsGenerator = productsGenerator;
        this.rolesGenerator = rolesGenerator;
        this.authoritiesGenerator = authoritiesGenerator;
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
        if (GENERATE_AUTHORITIES)
            authoritiesGenerator.createAuthorities(30);
        if (GENERATE_ADMIN_ROLE)
            rolesGenerator.createAdminRole();
        if (GENERATE_USER_ROLE)
            rolesGenerator.createUserRole();
        if (GENERATE_QUESTION_TEMPLATES)
            questionTemplatesGenerator.createAll();
        if (GENERATE_QUESTIONS)
            questionsGenerator.generateAll(5000);
        if (GENERATE_USERS)
            usersGenerator.generateUsers(1000);
        if (GENERATE_SECTION_TEMPLATES)
            sectionTemplatesGenerator.createAll();
        if (GENERATE_TEST_TEMPLATES)
            testTemplatesGenerator.createAll();
    }
}
