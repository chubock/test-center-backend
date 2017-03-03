package com.ztc.testcenter;

import com.ztc.testcenter.generator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Yubar on 1/15/2017.
 */

@SpringBootApplication
public class TestCenterApplication implements CommandLineRunner {

    private static final Boolean GENERATE_QUESTIONS = false;
    private static final Boolean GENERATE_USERS = false;
    private static final Boolean GENERATE_QUESTION_TEMPLATES = false;
    private static final Boolean GENERATE_SECTION_TEMPLATES = false;
    private static final Boolean GENERATE_TEST_TEMPLATES = false;

    private final QuestionsGenerator questionsGenerator;
    private final UsersGenerator usersGenerator;
    private final QuestionTemplatesGenerator questionTemplatesGenerator;
    private final SectionTemplatesGenerator sectionTemplatesGenerator;
    private final TestTemplatesGenerator testTemplatesGenerator;

    @Autowired
    public TestCenterApplication(QuestionsGenerator questionsGenerator, UsersGenerator usersGenerator, QuestionTemplatesGenerator questionTemplatesGenerator, SectionTemplatesGenerator sectionTemplatesGenerator, TestTemplatesGenerator testTemplatesGenerator) {
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
        if (GENERATE_QUESTION_TEMPLATES)
            questionTemplatesGenerator.createAll();
        if (GENERATE_QUESTIONS)
            questionsGenerator.generateAll(1000);
        if (GENERATE_USERS)
            usersGenerator.generateUsers(1000);
        if (GENERATE_SECTION_TEMPLATES)
            sectionTemplatesGenerator.createAll();
        if (GENERATE_TEST_TEMPLATES)
            testTemplatesGenerator.createAll();
    }
}
