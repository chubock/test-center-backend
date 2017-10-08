package com.ztc.testcenter.gre.domain.test;

import com.ztc.testcenter.gre.domain.question.Difficulty;

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

    private Long id;
    private SectionType sectionType;
    private Difficulty difficulty;
    private Boolean free = false;
    private List<SectionTemplateItem> items = new ArrayList<>();

    protected SectionTemplate() {
    }

    public SectionTemplate(SectionType sectionType, Difficulty difficulty) {
        this.sectionType = sectionType;
        this.difficulty = difficulty;
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

    @NotNull
    @Column(nullable = false)
    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        if (free == null)
            throw new NullPointerException();
        this.free = free;
    }

    @OrderBy("number")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "SECTION_TEMPLATE")
    public List<SectionTemplateItem> getItems() {
        return items;
    }

    public void setItems(List<SectionTemplateItem> items) {
        this.items = items;
    }
}
