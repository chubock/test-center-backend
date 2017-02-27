package com.ztc.testcenter.domain.test;

/**
 * Created by Yubar on 2/23/2017.
 */
public enum SectionType {

    GRE_VERBAL_REASONING_1(30, 20),
    GRE_VERBAL_REASONING_2(30, 20),
    GRE_QUANTITATIVE_REASONING_1(35, 20),
    GRE_QUANTITATIVE_REASONING_2(35, 20),
    GRE_ANALYTICAL_WRITING_ISSUE(1, 30),
    GRE_ANALYTICAL_WRITING_ARGUMENT(1, 30),
    GRE_VERBAL_UNSCORE(30, 20),
    GRE_QUANTITATIVE_UNSCORE(35, 20);

    Integer time;
    Integer questionsCount;

    SectionType(Integer time, Integer questionCount) {
        this.time = time;
        this.questionsCount = questionCount;
    }
}