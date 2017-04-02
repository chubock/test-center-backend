package com.ztc.testcenter.domain.test;

/**
 * Created by Yubar on 2/23/2017.
 */
public enum SectionType {

    GRE_VERBAL_REASONING_1(30, 20, 1),
    GRE_VERBAL_REASONING_2(30, 20, 1),
    GRE_QUANTITATIVE_REASONING_1(35, 20, 1),
    GRE_QUANTITATIVE_REASONING_2(35, 20, 1),
    GRE_ANALYTICAL_WRITING_ISSUE(1, 30, 10),
    GRE_ANALYTICAL_WRITING_ARGUMENT(1, 30, 10),
    GRE_VERBAL_UNSCORE(30, 20, 1),
    GRE_QUANTITATIVE_UNSCORE(35, 20, 1);

    public Integer time;
    public Integer questionsCount;
    public Integer breakTime;

    SectionType(Integer time, Integer questionCount, Integer breakTime) {
        this.time = time;
        this.questionsCount = questionCount;
        this.breakTime = breakTime;
    }
}