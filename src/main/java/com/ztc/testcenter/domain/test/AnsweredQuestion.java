package com.ztc.testcenter.domain.test;

import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.domain.question.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Yubar on 2/23/2017.
 */

@Entity
@Table(name = "ANSWERED_QUESTIONS", indexes = {
        @Index(columnList = "user_id,difficulty,difficultyLevel,questionType"),
        @Index(columnList = "user_id,difficulty,question_template_id"),
})
public class AnsweredQuestion implements Serializable {

    private Long id;
    private Integer number;
    private TestSection testSection;
    private User user;
    private Question question;
    private Question questionParent;
    private Difficulty difficulty;
    private DifficultyLevel difficultyLevel;
    private QuestionType questionType;
    private QuestionTemplate questionTemplate;
    private String userAnswer;

    protected AnsweredQuestion() {
    }

    public AnsweredQuestion(TestSection testSection, Question question) {
        this.testSection = testSection;
        this.question = question;
        this.difficulty = question.getDifficulty();
        this.difficultyLevel = question.getDifficultyLevel();
        this.questionType = question.getQuestionType();
        if (question instanceof InnerQuestion){
            this.questionParent = ((InnerQuestion)question).getParent();
            this.questionTemplate = this.questionParent.getTemplate();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public TestSection getTestSection() {
        return testSection;
    }

    public void setTestSection(TestSection testSection) {
        this.testSection = testSection;
    }

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @NotNull
    @Enumerated
    @Column(nullable = false)
    public Difficulty getDifficulty() {
        return difficulty;
    }

    void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @NotNull
    @Enumerated
    @Column(nullable = false)
    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @NotNull
    @Enumerated
    @Column(nullable = false)
    public QuestionType getQuestionType() {
        return questionType;
    }

    void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Question getQuestionParent() {
        return questionParent;
    }

    void setQuestionParent(Question questionParent) {
        this.questionParent = questionParent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public QuestionTemplate getQuestionTemplate() {
        return questionTemplate;
    }

    void setQuestionTemplate(QuestionTemplate questionTemplate) {
        this.questionTemplate = questionTemplate;
    }

    @Column(length = 3000)
    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
