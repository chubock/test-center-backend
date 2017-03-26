package com.ztc.testcenter.domain.question;

import com.ztc.testcenter.domain.File;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Yubar on 1/18/2017.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(indexes = {
        @Index(columnList = "difficulty,difficultyLevel,questionType"),
        @Index(columnList = "difficulty,template_id"),
})
public abstract class Question implements Serializable {

    private Long id;
    private String text;
    private File image;
    private Difficulty difficulty = Difficulty.MEDIUM;
    private DifficultyLevel difficultyLevel = DifficultyLevel.LEVEL3;
    private String answers;
    private QuestionTemplate template;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @NotNull
    @Enumerated
    @Column(nullable = false)
    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    String getAnswers() {
        return answers;
    }

    void setAnswers(String answers) {
        this.answers = answers;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public QuestionTemplate getTemplate() {
        return template;
    }

    public void setTemplate(QuestionTemplate template) {
        this.template = template;
    }

    @Enumerated
    @Column(nullable = false)
    public abstract QuestionType getQuestionType();

    private void setQuestionType(QuestionType questionType) {}

    public void prepare() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        if (getId() != null ? !getId().equals(question.getId()) : question.getId() != null) return false;
        if (getId() != null && getId().equals(question.getId()))
            return true;
        if (getText() != null ? !getText().equals(question.getText()) : question.getText() != null) return false;
        if (getDifficulty() != question.getDifficulty()) return false;
        if (getDifficultyLevel() != question.getDifficultyLevel()) return false;
        return getAnswers() != null ? getAnswers().equals(question.getAnswers()) : question.getAnswers() == null;
    }

    @Override
    public int hashCode() {
        int result = getText() != null ? getText().hashCode() : 0;
        result = 31 * result + (getDifficulty() != null ? getDifficulty().hashCode() : 0);
        result = 31 * result + (getDifficultyLevel() != null ? getDifficultyLevel().hashCode() : 0);
        result = 31 * result + (getAnswers() != null ? getAnswers().hashCode() : 0);
        return result;
    }
}
