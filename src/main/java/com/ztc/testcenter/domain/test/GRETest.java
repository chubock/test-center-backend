package com.ztc.testcenter.domain.test;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Yubar on 2/23/2017.
 */

@Entity
@Table(name = "GRE_TESTS")
public class GRETest extends Test {

    private SectionTemplate verbalTemplate1;
    private SectionTemplate verbalTemplate2;
    private SectionTemplate quantitativeTemplate1;
    private SectionTemplate quantitativeTemplate2;
    private SectionTemplate writingTemplate1;
    private SectionTemplate writingTemplate2;

    @ManyToOne(fetch = FetchType.LAZY)
    public SectionTemplate getVerbalTemplate1() {
        return verbalTemplate1;
    }

    public void setVerbalTemplate1(SectionTemplate verbalTemplate1) {
        this.verbalTemplate1 = verbalTemplate1;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public SectionTemplate getVerbalTemplate2() {
        return verbalTemplate2;
    }

    public void setVerbalTemplate2(SectionTemplate verbalTemplate2) {
        this.verbalTemplate2 = verbalTemplate2;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public SectionTemplate getQuantitativeTemplate1() {
        return quantitativeTemplate1;
    }

    public void setQuantitativeTemplate1(SectionTemplate quantitativeTemplate1) {
        this.quantitativeTemplate1 = quantitativeTemplate1;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public SectionTemplate getQuantitativeTemplate2() {
        return quantitativeTemplate2;
    }

    public void setQuantitativeTemplate2(SectionTemplate quantitativeTemplate2) {
        this.quantitativeTemplate2 = quantitativeTemplate2;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public SectionTemplate getWritingTemplate1() {
        return writingTemplate1;
    }

    public void setWritingTemplate1(SectionTemplate writingTemplate1) {
        this.writingTemplate1 = writingTemplate1;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public SectionTemplate getWritingTemplate2() {
        return writingTemplate2;
    }

    public void setWritingTemplate2(SectionTemplate writingTemplate2) {
        this.writingTemplate2 = writingTemplate2;
    }
}
