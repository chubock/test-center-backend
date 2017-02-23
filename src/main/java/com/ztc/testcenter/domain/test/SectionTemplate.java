package com.ztc.testcenter.domain.test;

import com.ztc.testcenter.domain.question.Difficulty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 2/23/2017.
 */

@Entity
@Table(name = "SECTION_TEMPLATES")
public class SectionTemplate implements Serializable {

    private long id;
    private String name;
    private SectionType sectionType;
    private Difficulty difficulty;
    private List<SectionTemplateItem> sectionTemplateItems = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @NotNull
    @Enumerated
    @Column(nullable = false)
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @OrderBy("number")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "SECTION_TEMPLATE")
    public List<SectionTemplateItem> getSectionTemplateItems() {
        return sectionTemplateItems;
    }

    public void setSectionTemplateItems(List<SectionTemplateItem> sectionTemplateItems) {
        this.sectionTemplateItems = sectionTemplateItems;
    }
}
