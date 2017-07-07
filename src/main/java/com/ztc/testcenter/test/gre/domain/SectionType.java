package com.ztc.testcenter.test.gre.domain;

/**
 * Created by Yubar on 2/23/2017.
 */
public enum SectionType {

    GRE_VERBAL_REASONING_1(Group.GRE_VERBAL, 30, 20, 1),
    GRE_VERBAL_REASONING_2(Group.GRE_VERBAL, 30, 20, 1),
    GRE_QUANTITATIVE_REASONING_1(Group.GRE_QUANTITATIVE, 35, 20, 1),
    GRE_QUANTITATIVE_REASONING_2(Group.GRE_QUANTITATIVE, 35, 20, 1),
    GRE_ANALYTICAL_WRITING_ISSUE(Group.GRE_WRITING, 30, 1, 10),
    GRE_ANALYTICAL_WRITING_ARGUMENT(Group.GRE_WRITING, 30, 1, 10),
    GRE_VERBAL_UNSCORE(Group.GRE_VERBAL, 30, 20, 1),
    GRE_QUANTITATIVE_UNSCORE(Group.GRE_QUANTITATIVE, 35, 20, 1);

    public Integer time;
    public Integer questionsCount;
    public Integer breakTime;
    public Group group;

    SectionType(Group group, Integer time, Integer questionCount, Integer breakTime) {
        this.group = group;
        this.time = time;
        this.questionsCount = questionCount;
        this.breakTime = breakTime;
    }

    public enum Group {
        GRE_VERBAL,
        GRE_QUANTITATIVE,
        GRE_WRITING
    }
}