package com.ztc.testcenter.user.domain;

/**
 * Created by Yubar on 2/23/2017.
 */

public enum Authority {

    GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__GET_QUESTIONS,
    GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__SAVE,
    GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__DELETE,

    GRE_NUMERIC_QUESTION_REST_SERVICE__GET_QUESTIONS,
    GRE_NUMERIC_QUESTION_REST_SERVICE__SAVE,
    GRE_NUMERIC_QUESTION_REST_SERVICE__DELETE,

    GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__GET_QUESTIONS,
    GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__SAVE,
    GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__DELETE,

    GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__GET_QUESTIONS,
    GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__SAVE,
    GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__DELETE,

    GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__GET_QUESTIONS,
    GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__SAVE,
    GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__DELETE,

    GRE_READING_COMPREHENSION_QUESTION_REST_SERVICE__GET_QUESTIONS,
    GRE_READING_COMPREHENSION_QUESTION_REST_SERVICE__GET_SAVE,
    GRE_READING_COMPREHENSION_QUESTION_REST_SERVICE__GET_DELETE,

    GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__GET_QUESTIONS,
    GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__SAVE,
    GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__DELETE,

    GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__GET_QUESTIONS,
    GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__SAVE,
    GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__DELETE,

    GRE_WRITING_QUESTION_REST_SERVICE__GET_QUESTIONS,
    GRE_WRITING_QUESTION_REST_SERVICE__SAVE,
    GRE_WRITING_QUESTION_REST_SERVICE__DELETE,

    GRE_TEST_REST_SERVICE__GET_TESTS,
    GRE_TEST_REST_SERVICE__GET_TESTS_ALL,
    GRE_TEST_REST_SERVICE__GET_TEST,
    GRE_TEST_REST_SERVICE__GET_TEST_ALL,
    GRE_TEST_REST_SERVICE__GET_CURRENT_TEST,
    GRE_TEST_REST_SERVICE__CREATE_TEST,
    GRE_TEST_REST_SERVICE__GET_TEST_SECTION,
    GRE_TEST_REST_SERVICE__GET_TEST_SECTION_ALL,
    GRE_TEST_REST_SERVICE__CREATE_NEXT_SECTION,
    GRE_TEST_REST_SERVICE__SEE_QUESTION,
    GRE_TEST_REST_SERVICE__ANSWER_QUESTION,
    GRE_TEST_REST_SERVICE__MARK_QUESTION,
    GRE_TEST_REST_SERVICE__UN_MARK_QUESTION,
    GRE_TEST_REST_SERVICE__FINISH_TEST,
    GRE_TEST_REST_SERVICE__COMMENT_TEST,

    PRODUCT_REST_SERVICE__GET_PRODUCTS,
    PRODUCT_REST_SERVICE__GET_PRODUCT,
    PRODUCT_REST_SERVICE__SAVE_PRODUCT,

    ORDER_REST_SERVICE__GET_ORDERS,
    ORDER_REST_SERVICE__GET_ORDERS_ALL,
    ORDER_REST_SERVICE__GET_ORDER,
    ORDER_REST_SERVICE__GET_ORDER_ALL,
    ORDER_REST_SERVICE__CREATE_ORDER,
    ORDER_REST_SERVICE__GET_AVAILABLE_PRODUCTS,
    ORDER_REST_SERVICE__GET_PRODUCT,

    ANSWERED_QUESTION_REST_SERVICE__GET_ANSWERED_QUESTIONS,
    ANSWERED_QUESTION_REST_SERVICE__GET_ANSWERED_QUESTIONS_ALL,
    ANSWERED_QUESTION_REST_SERVICE__SCORE_ANSWERED_QUESTIONS,

    FILE_REST_SERVICE__UPLOAD_FILE,

    ROLE_REST_SERVICE__GET_ROLES,
    ROLE_REST_SERVICE__GET_ROLE,
    ROLE_REST_SERVICE__SAVE_ROLE,
}