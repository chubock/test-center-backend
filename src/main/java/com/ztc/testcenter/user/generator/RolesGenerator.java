package com.ztc.testcenter.user.generator;

import com.ztc.testcenter.user.domain.Authority;
import com.ztc.testcenter.user.domain.Role;
import com.ztc.testcenter.user.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yubar on 4/3/17.
 */

@Service
public class RolesGenerator {

    private final RoleRepository roleRepository;

    public RolesGenerator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void createAuthorities() {

        Role admin = new Role("ADMIN");
        admin.getAuthorities().add(Authority.GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__GET_QUESTIONS);
        admin.getAuthorities().add(Authority.GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__SAVE);
        admin.getAuthorities().add(Authority.GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__DELETE);

        admin.getAuthorities().add(Authority.GRE_NUMERIC_QUESTION_REST_SERVICE__GET_QUESTIONS);
        admin.getAuthorities().add(Authority.GRE_NUMERIC_QUESTION_REST_SERVICE__SAVE);
        admin.getAuthorities().add(Authority.GRE_NUMERIC_QUESTION_REST_SERVICE__DELETE);

        admin.getAuthorities().add(Authority.GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__GET_QUESTIONS);
        admin.getAuthorities().add(Authority.GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__SAVE);
        admin.getAuthorities().add(Authority.GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__DELETE);

        admin.getAuthorities().add(Authority.GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__GET_QUESTIONS);
        admin.getAuthorities().add(Authority.GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__SAVE);
        admin.getAuthorities().add(Authority.GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__DELETE);

        admin.getAuthorities().add(Authority.GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__GET_QUESTIONS);
        admin.getAuthorities().add(Authority.GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__SAVE);
        admin.getAuthorities().add(Authority.GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__DELETE);

        admin.getAuthorities().add(Authority.GRE_READING_COMPREHENSION_QUESTION_REST_SERVICE__GET_QUESTIONS);
        admin.getAuthorities().add(Authority.GRE_READING_COMPREHENSION_QUESTION_REST_SERVICE__GET_SAVE);
        admin.getAuthorities().add(Authority.GRE_READING_COMPREHENSION_QUESTION_REST_SERVICE__GET_DELETE);

        admin.getAuthorities().add(Authority.GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__GET_QUESTIONS);
        admin.getAuthorities().add(Authority.GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__SAVE);
        admin.getAuthorities().add(Authority.GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__DELETE);

        admin.getAuthorities().add(Authority.GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__GET_QUESTIONS);
        admin.getAuthorities().add(Authority.GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__SAVE);
        admin.getAuthorities().add(Authority.GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__DELETE);

        admin.getAuthorities().add(Authority.GRE_WRITING_QUESTION_REST_SERVICE__GET_QUESTIONS);
        admin.getAuthorities().add(Authority.GRE_WRITING_QUESTION_REST_SERVICE__SAVE);
        admin.getAuthorities().add(Authority.GRE_WRITING_QUESTION_REST_SERVICE__DELETE);

        admin.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_TESTS_ALL);
        admin.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_TEST_ALL);
        admin.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_TEST_SECTION_ALL);

        admin.getAuthorities().add(Authority.PRODUCT_REST_SERVICE__GET_PRODUCTS);
        admin.getAuthorities().add(Authority.PRODUCT_REST_SERVICE__GET_PRODUCT);
        admin.getAuthorities().add(Authority.PRODUCT_REST_SERVICE__SAVE_PRODUCT);

        admin.getAuthorities().add(Authority.ORDER_REST_SERVICE__GET_ORDERS_ALL);
        admin.getAuthorities().add(Authority.ORDER_REST_SERVICE__GET_ORDER_ALL);
        admin.getAuthorities().add(Authority.ORDER_REST_SERVICE__GET_AVAILABLE_PRODUCTS);
        admin.getAuthorities().add(Authority.ORDER_REST_SERVICE__GET_PRODUCT);

        admin.getAuthorities().add(Authority.ANSWERED_QUESTION_REST_SERVICE__GET_ANSWERED_QUESTIONS_ALL);
        admin.getAuthorities().add(Authority.ANSWERED_QUESTION_REST_SERVICE__SCORE_ANSWERED_QUESTIONS);

        admin.getAuthorities().add(Authority.FILE_REST_SERVICE__UPLOAD_FILE);

        admin.getAuthorities().add(Authority.ROLE_REST_SERVICE__GET_ROLES);
        admin.getAuthorities().add(Authority.ROLE_REST_SERVICE__GET_ROLE);
        admin.getAuthorities().add(Authority.ROLE_REST_SERVICE__SAVE_ROLE);


        Role teacher = new Role("TEACHER");
        teacher.getAuthorities().add(Authority.ANSWERED_QUESTION_REST_SERVICE__GET_ANSWERED_QUESTIONS_ALL);
        teacher.getAuthorities().add(Authority.ANSWERED_QUESTION_REST_SERVICE__SCORE_ANSWERED_QUESTIONS);

        Role sale = new Role("SALE");
        sale.getAuthorities().add(Authority.PRODUCT_REST_SERVICE__GET_PRODUCTS);
        sale.getAuthorities().add(Authority.PRODUCT_REST_SERVICE__GET_PRODUCT);
        sale.getAuthorities().add(Authority.PRODUCT_REST_SERVICE__SAVE_PRODUCT);

        sale.getAuthorities().add(Authority.ORDER_REST_SERVICE__GET_ORDERS_ALL);
        sale.getAuthorities().add(Authority.ORDER_REST_SERVICE__GET_ORDER_ALL);
        sale.getAuthorities().add(Authority.ORDER_REST_SERVICE__GET_AVAILABLE_PRODUCTS);
        sale.getAuthorities().add(Authority.ORDER_REST_SERVICE__GET_PRODUCT);

        Role student = new Role("STUDENT");
        student.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_TESTS);
        student.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_TEST);
        student.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_CURRENT_TEST);
        student.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__CREATE_TEST);
        student.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_TEST_SECTION);
        student.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__CREATE_NEXT_SECTION);
        student.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__SEE_QUESTION);
        student.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__ANSWER_QUESTION);
        student.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__MARK_QUESTION);
        student.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__UN_MARK_QUESTION);
        student.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__FINISH_TEST);
        student.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__COMMENT_TEST);

        student.getAuthorities().add(Authority.ORDER_REST_SERVICE__GET_ORDERS);
        student.getAuthorities().add(Authority.ORDER_REST_SERVICE__GET_ORDER);
        student.getAuthorities().add(Authority.ORDER_REST_SERVICE__CREATE_ORDER);
        student.getAuthorities().add(Authority.ORDER_REST_SERVICE__GET_AVAILABLE_PRODUCTS);
        student.getAuthorities().add(Authority.ORDER_REST_SERVICE__GET_PRODUCT);

        student.getAuthorities().add(Authority.ANSWERED_QUESTION_REST_SERVICE__GET_ANSWERED_QUESTIONS);

        Role data = new Role("DATA");
        data.getAuthorities().add(Authority.GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__GET_QUESTIONS);
        data.getAuthorities().add(Authority.GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__SAVE);
        data.getAuthorities().add(Authority.GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__DELETE);

        data.getAuthorities().add(Authority.GRE_NUMERIC_QUESTION_REST_SERVICE__GET_QUESTIONS);
        data.getAuthorities().add(Authority.GRE_NUMERIC_QUESTION_REST_SERVICE__SAVE);
        data.getAuthorities().add(Authority.GRE_NUMERIC_QUESTION_REST_SERVICE__DELETE);

        data.getAuthorities().add(Authority.GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__GET_QUESTIONS);
        data.getAuthorities().add(Authority.GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__SAVE);
        data.getAuthorities().add(Authority.GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__DELETE);

        data.getAuthorities().add(Authority.GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__GET_QUESTIONS);
        data.getAuthorities().add(Authority.GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__SAVE);
        data.getAuthorities().add(Authority.GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__DELETE);

        data.getAuthorities().add(Authority.GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__GET_QUESTIONS);
        data.getAuthorities().add(Authority.GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__SAVE);
        data.getAuthorities().add(Authority.GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__DELETE);

        data.getAuthorities().add(Authority.GRE_READING_COMPREHENSION_QUESTION_REST_SERVICE__GET_QUESTIONS);
        data.getAuthorities().add(Authority.GRE_READING_COMPREHENSION_QUESTION_REST_SERVICE__GET_SAVE);
        data.getAuthorities().add(Authority.GRE_READING_COMPREHENSION_QUESTION_REST_SERVICE__GET_DELETE);

        data.getAuthorities().add(Authority.GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__GET_QUESTIONS);
        data.getAuthorities().add(Authority.GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__SAVE);
        data.getAuthorities().add(Authority.GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__DELETE);

        data.getAuthorities().add(Authority.GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__GET_QUESTIONS);
        data.getAuthorities().add(Authority.GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__SAVE);
        data.getAuthorities().add(Authority.GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__DELETE);

        data.getAuthorities().add(Authority.GRE_WRITING_QUESTION_REST_SERVICE__GET_QUESTIONS);
        data.getAuthorities().add(Authority.GRE_WRITING_QUESTION_REST_SERVICE__SAVE);
        data.getAuthorities().add(Authority.GRE_WRITING_QUESTION_REST_SERVICE__DELETE);

        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_TESTS);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_TESTS_ALL);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_TEST);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_TEST_ALL);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_CURRENT_TEST);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__CREATE_TEST);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_TEST_SECTION);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__GET_TEST_SECTION_ALL);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__CREATE_NEXT_SECTION);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__SEE_QUESTION);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__ANSWER_QUESTION);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__MARK_QUESTION);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__UN_MARK_QUESTION);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__FINISH_TEST);
        data.getAuthorities().add(Authority.GRE_TEST_REST_SERVICE__COMMENT_TEST);

        data.getAuthorities().add(Authority.FILE_REST_SERVICE__UPLOAD_FILE);

        roleRepository.save(admin);
        roleRepository.save(data);
        roleRepository.save(teacher);
        roleRepository.save(sale);
        roleRepository.save(student);

    }

}