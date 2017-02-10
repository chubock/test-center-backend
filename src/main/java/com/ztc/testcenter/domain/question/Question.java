package com.ztc.testcenter.domain.question;

import com.ztc.testcenter.domain.File;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Yubar on 1/18/2017.
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class Question implements Serializable {

    private long id;
    private String text;
    private File image;
    private Difficulty difficulty = Difficulty.MEDIUM;
    private String answers;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    String getAnswers() {
        return answers;
    }

    void setAnswers(String answers) {
        this.answers = answers;
    }

    public void prepare() {

    }
}
