package com.ztc.testcenter.domain.question;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Yubar on 2/23/2017.
 */

@Entity
@Table(name = "QUESTION_TEMPLATES")
public class QuestionTemplate implements Serializable {

    private Long id;
    private QuestionType questionType;
    private Difficulty difficulty;
    private List<QuestionTemplateItem> questionTemplateItems = new ArrayList<>();
    private String label;

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
    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
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

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "QUESTION_TEMPLATE")
    public List<QuestionTemplateItem> getQuestionTemplateItems() {
        return questionTemplateItems;
    }

    public void setQuestionTemplateItems(List<QuestionTemplateItem> questionTemplateItems) {
        this.questionTemplateItems = questionTemplateItems;
    }

    @Column(nullable = false)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void prepare() {
        StringBuilder builder = new StringBuilder();
        builder.append(getQuestionType().name());
        builder.append('-');
        builder.append(getDifficulty().name());
        builder.append('-');
        Iterator<QuestionTemplateItem> itemIterator = getQuestionTemplateItems().iterator();
        while(itemIterator.hasNext()) {
            QuestionTemplateItem item = itemIterator.next();
            builder.append(item.getCount());
            builder.append("L" + (item.getDifficultyLevel().ordinal() + 1));
            if (itemIterator.hasNext())
                builder.append('-');
        }
        setLabel(builder.toString());
    }
}
