package com.ztc.testcenter.question.gre.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

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
    private Integer count = 0;

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

    @NotNull
    @Column(nullable = false)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public static QuestionTemplate templateOf(QuestionsContainer questionsContainer) {
        Question question = (Question) questionsContainer;
        QuestionTemplate questionTemplate = new QuestionTemplate();
        questionTemplate.setDifficulty(question.getDifficulty());
        questionTemplate.setQuestionType(question.getQuestionType());
        Map<DifficultyLevel, Integer> difficultyLevelsCount = new HashMap<>();
        for (DifficultyLevel difficultyLevel: DifficultyLevel.values())
            difficultyLevelsCount.put(difficultyLevel, 0);
        questionsContainer.innerQuestions().forEach(q -> difficultyLevelsCount.put(q.getDifficultyLevel(), difficultyLevelsCount.get(q.getDifficultyLevel()) + 1));
        for (DifficultyLevel difficultyLevel: DifficultyLevel.values()) {
            int count = difficultyLevelsCount.get(difficultyLevel);
            if (count != 0)
                questionTemplate.getQuestionTemplateItems().add(new QuestionTemplateItem(difficultyLevel, count));
        }
        questionTemplate.prepare();
        return questionTemplate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionTemplate)) return false;

        QuestionTemplate that = (QuestionTemplate) o;

        return getLabel() != null ? getLabel().equals(that.getLabel()) : that.getLabel() == null;
    }

    @Override
    public int hashCode() {
        return getLabel() != null ? getLabel().hashCode() : 0;
    }
}
