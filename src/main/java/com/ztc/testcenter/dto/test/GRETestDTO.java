package com.ztc.testcenter.dto.test;

import com.ztc.testcenter.domain.test.GRETest;
import com.ztc.testcenter.domain.test.Test;

/**
 * Created by Yubar on 3/4/2017.
 */
public class GRETestDTO extends TestDTO {

    private SectionTemplateDTO verbalTemplate1;
    private SectionTemplateDTO verbalTemplate2;
    private SectionTemplateDTO quantitativeTemplate1;
    private SectionTemplateDTO quantitativeTemplate2;
    private SectionTemplateDTO writingTemplate1;
    private SectionTemplateDTO writingTemplate2;

    public SectionTemplateDTO getVerbalTemplate1() {
        return verbalTemplate1;
    }

    public void setVerbalTemplate1(SectionTemplateDTO verbalTemplate1) {
        this.verbalTemplate1 = verbalTemplate1;
    }

    public SectionTemplateDTO getVerbalTemplate2() {
        return verbalTemplate2;
    }

    public void setVerbalTemplate2(SectionTemplateDTO verbalTemplate2) {
        this.verbalTemplate2 = verbalTemplate2;
    }

    public SectionTemplateDTO getQuantitativeTemplate1() {
        return quantitativeTemplate1;
    }

    public void setQuantitativeTemplate1(SectionTemplateDTO quantitativeTemplate1) {
        this.quantitativeTemplate1 = quantitativeTemplate1;
    }

    public SectionTemplateDTO getQuantitativeTemplate2() {
        return quantitativeTemplate2;
    }

    public void setQuantitativeTemplate2(SectionTemplateDTO quantitativeTemplate2) {
        this.quantitativeTemplate2 = quantitativeTemplate2;
    }

    public SectionTemplateDTO getWritingTemplate1() {
        return writingTemplate1;
    }

    public void setWritingTemplate1(SectionTemplateDTO writingTemplate1) {
        this.writingTemplate1 = writingTemplate1;
    }

    public SectionTemplateDTO getWritingTemplate2() {
        return writingTemplate2;
    }

    public void setWritingTemplate2(SectionTemplateDTO writingTemplate2) {
        this.writingTemplate2 = writingTemplate2;
    }

    void convert(GRETest test) {
        super.convert(test);
        if (getWritingTemplate1() != null)
            test.setWritingTemplate1(getWritingTemplate1().convert());
        if (getWritingTemplate2() != null)
            test.setWritingTemplate2(getWritingTemplate2().convert());
        if (getVerbalTemplate1() != null)
            test.setVerbalTemplate1(getVerbalTemplate1().convert());
        if (getVerbalTemplate2() != null)
            test.setVerbalTemplate2(getVerbalTemplate2().convert());
        if (getQuantitativeTemplate1() != null)
            test.setQuantitativeTemplate1(getQuantitativeTemplate1().convert());
        if (getQuantitativeTemplate2() != null)
            test.setQuantitativeTemplate2(getQuantitativeTemplate2().convert());
    }

    @Override
    public Test convert() {
        GRETest test = new GRETest();
        convert(test);
        return test;
    }

    public static GRETestDTO valueOf(GRETest test) {
        if (test == null)
            return null;
        GRETestDTO testDTO = new GRETestDTO();
        testDTO.copy(test);
        return testDTO;
    }
}
