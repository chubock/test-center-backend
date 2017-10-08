package com.ztc.testcenter.gre.domain.test;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Yubar on 3/2/2017.
 */

@Entity
@Table(name = "TEST_TEMPLATE_ITEMS")
public class TestTemplateItem implements Serializable {

    private Long id;
    private Integer number;
    private SectionType sectionType;

    protected TestTemplateItem() {
    }

    public TestTemplateItem(Integer number, SectionType sectionType) {
        this.number = number;
        this.sectionType = sectionType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(nullable = false)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @NotNull
    @Enumerated
    @Column(nullable = false)
    public SectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }
}
