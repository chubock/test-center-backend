package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.File;
import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.question.DifficultyLevel;
import com.ztc.testcenter.domain.question.Question;
import com.ztc.testcenter.dto.AbstractDTO;
import com.ztc.testcenter.dto.FileDTO;

/**
 * Created by Yubar on 1/19/2017.
 */
public abstract class QuestionDTO extends AbstractDTO<Question> {

    private Long id;
    private String text;
    private Long image;
    private Difficulty difficulty = Difficulty.MEDIUM;
    private DifficultyLevel difficultyLevel = DifficultyLevel.LEVEL3;
    private String answers;

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

    public Long getImage() {
        return image;
    }

    public void setImage(Long image) {
        this.image = image;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    void convert(Question question) {
        question.setId(getId());
        question.setText(getText());
        question.setDifficulty(getDifficulty());
        question.setDifficultyLevel(getDifficultyLevel());
        if (getImage() != null && getImage() != 0) {
            File file = new File();
            file.setId(getImage());
            question.setImage(file);
        }
    }

    void copy(Question question) {
        setId(question.getId());
        setText(question.getText());
        setDifficulty(question.getDifficulty());
        setDifficultyLevel(question.getDifficultyLevel());
        if (question.getImage() != null)
            setImage(question.getImage().getId());
    }
}
