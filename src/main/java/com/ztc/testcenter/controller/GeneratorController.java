package com.ztc.testcenter.controller;

import com.ztc.testcenter.generator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yubar on 6/29/17.
 */

@Controller
@ResponseBody
@RequestMapping("/generators")
public class GeneratorController {

    private final ProductsGenerator productsGenerator;
    private final QuestionsGenerator questionsGenerator;
    private final QuestionTemplatesGenerator questionTemplatesGenerator;
    private final RolesGenerator rolesGenerator;
    private final SectionTemplatesGenerator sectionTemplatesGenerator;
    private final TestTemplatesGenerator testTemplatesGenerator;
    private final UsersGenerator usersGenerator;

    @Autowired
    public GeneratorController(ProductsGenerator productsGenerator, QuestionsGenerator questionsGenerator, QuestionTemplatesGenerator questionTemplatesGenerator, RolesGenerator rolesGenerator, SectionTemplatesGenerator sectionTemplatesGenerator, TestTemplatesGenerator testTemplatesGenerator, UsersGenerator usersGenerator) {
        this.productsGenerator = productsGenerator;
        this.questionsGenerator = questionsGenerator;
        this.questionTemplatesGenerator = questionTemplatesGenerator;
        this.rolesGenerator = rolesGenerator;
        this.sectionTemplatesGenerator = sectionTemplatesGenerator;
        this.testTemplatesGenerator = testTemplatesGenerator;
        this.usersGenerator = usersGenerator;
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public String generateRoles() {
        rolesGenerator.createAuthorities();
        return "Roles are generated";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String generateUsers() {
        usersGenerator.generateUsers();
        return "Users are generated";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String generateProducts() {
        productsGenerator.createProducts();
        return "Products are generated";
    }

    @RequestMapping(value = "/question-templates", method = RequestMethod.POST)
    public String generateQuestionTemplates() {
        questionTemplatesGenerator.createAll();
        return "Question templates are generated";
    }

    @RequestMapping(value = "/section-templates", method = RequestMethod.POST)
    public String generateSectionTemplates() {
        sectionTemplatesGenerator.createAll();
        return "Section templates are generated";
    }

    @RequestMapping(value = "/test-templates", method = RequestMethod.POST)
    public String generateTestTemplates() {
        testTemplatesGenerator.createAll();
        return "Test templates are generated";
    }

    @RequestMapping(value = "/questions", method = RequestMethod.POST)
    public String generateQuestions(@RequestParam(defaultValue = "3000") Integer notFreeCount, @RequestParam(defaultValue = "1000") Integer freeCount) {
        questionsGenerator.generateAll(notFreeCount, freeCount);
        return "Questions are generated";
    }
}
