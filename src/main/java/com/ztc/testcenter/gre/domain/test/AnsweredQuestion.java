package com.ztc.testcenter.gre.domain.test;

import com.ztc.testcenter.gre.domain.question.*;
import com.ztc.testcenter.user.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Yubar on 2/23/2017.
 */

@Entity
public class AnsweredQuestion implements Serializable {

    private Long id;
    private Integer number;
    private TestSection testSection;
    private SectionType.Group testSectionGroup;
    private Test test;
    private String username;
    private Question question;
    private Question questionParent;
    private Difficulty difficulty;
    private DifficultyLevel difficultyLevel;
    private QuestionType questionType;
    private QuestionTemplate questionTemplate;
    private String userAnswer;
    private Boolean free;
    private Boolean marked = false;
    private Status status = Status.NOT_SEEN;
    private Double score;
    private String comment;

    protected AnsweredQuestion() {
    }

    public AnsweredQuestion(TestSection testSection, Question question) {
        this.testSection = testSection;
        this.test = testSection.getTest();
        this.testSectionGroup = testSection.getSectionType().group;
        this.question = question;
        this.difficulty = question.getDifficulty();
        this.difficultyLevel = question.getDifficultyLevel();
        this.questionType = question.getQuestionType();
        this.free = question.getFree();
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

    @Enumerated
    public SectionType.Group getTestSectionGroup() {
        return testSectionGroup;
    }

    private void setTestSectionGroup(SectionType.Group testSectionGroup) {
        this.testSectionGroup = testSectionGroup;
    }

    @NotNull
    @ManyToOne(optional = false)
    public Test getTest() {
        return test;
    }

    private void setTest(Test test) {
        this.test = test;
    }

    @NotNull
    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
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

    @NotNull
    @Column(nullable = false)
    public Boolean getFree() {
        return free;
    }

    void setFree(Boolean free) {
        if (free == null)
            throw new NullPointerException();
        this.free = free;
    }

    public void setSeen() {
        if (status == Status.NOT_SEEN)
            status = Status.NOT_ANSWERED;
    }

    @NotNull
    @Column(nullable = false)
    public Boolean getMarked() {
        return marked;
    }

    public void setMarked(Boolean marked) {
        this.marked = marked;
    }

    @Column(length = 3000)
    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @NotNull
    @Enumerated
    @Column(nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public enum  Status {
        NOT_SEEN,
        NOT_ANSWERED,
        CORRECT,
        INCORRECT
    }
}
