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
public abstract class Question implements Serializable {

    private Long id;
    private String text;
    private File image;
    private Difficulty difficulty = Difficulty.MEDIUM;
    private DifficultyLevel difficultyLevel = DifficultyLevel.LEVEL3;
    private String answers;

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

    @ManyToOne
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

    @Enumerated
    @Column(nullable = false)
    abstract QuestionType getQuestionType();

    private void setQuestionType(QuestionType questionType) {}

    public void prepare() {

    }
}
