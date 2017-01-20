package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.question.Question;
import com.ztc.testcenter.dto.AbstractDTO;
import com.ztc.testcenter.dto.FileDTO;

/**
 * Created by Yubar on 1/19/2017.
 */
public abstract class QuestionDTO extends AbstractDTO<Question> {

    private long id;
    private String text;
    private FileDTO image;
    private Difficulty difficulty = Difficulty.MEDIUM;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public FileDTO getImage() {
        return image;
    }

    public void setImage(FileDTO image) {
        this.image = image;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    void convert(Question question) {
        question.setId(getId());
        question.setText(getText());
        question.setDifficulty(getDifficulty());
        if (getImage() != null)
            question.setImage(getImage().convert());
    }

    void copy(Question question) {
        setId(question.getId());
        setText(question.getText());
        setDifficulty(question.getDifficulty());
        if (question.getImage() != null) {
            setImage(new FileDTO());
            getImage().setId(question.getImage().getId());
        }
    }
}
