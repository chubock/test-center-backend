package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.File;
import com.ztc.testcenter.domain.question.*;
import com.ztc.testcenter.domain.test.AnsweredQuestion;
import com.ztc.testcenter.dto.AbstractDTO;

/**
 * Created by Yubar on 1/19/2017.
 */
public abstract class QuestionDTO extends AbstractDTO<Question> {

    private Long id;
    private Integer number;
    private String text;
    private Long image;
    private Difficulty difficulty = Difficulty.MEDIUM;
    private DifficultyLevel difficultyLevel = DifficultyLevel.LEVEL3;
    private QuestionType questionType;

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

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
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
        setQuestionType(question.getQuestionType());
        if (question.getImage() != null)
            setImage(question.getImage().getId());
    }

    public abstract void setUserAnswer(String answer);

    public static QuestionDTO valueOf(AnsweredQuestion answeredQuestion) {
        QuestionDTO ret;
        if (answeredQuestion.getQuestion() instanceof DataInterpretationSetQuestion)
            ret = DataInterpretationSetQuestionDTO.valueOf((DataInterpretationSetQuestion) answeredQuestion.getQuestion());
        else if (answeredQuestion.getQuestion() instanceof DataInterpretationSingleAnswerQuestion){
            ret = DataInterpretationSingleAnswerQuestionDTO.valueOf((DataInterpretationSingleAnswerQuestion) answeredQuestion.getQuestion());
            ((DataInterpretationSingleAnswerQuestionDTO)ret).setParent(DataInterpretationSetQuestionDTO.valueOf(((DataInterpretationSingleAnswerQuestion) answeredQuestion.getQuestion()).getParent(), true));
        }
        else if (answeredQuestion.getQuestion() instanceof DataInterpretationMultipleAnswerQuestion){
            ret = DataInterpretationMultipleAnswerQuestionDTO.valueOf((DataInterpretationMultipleAnswerQuestion) answeredQuestion.getQuestion());
            ((DataInterpretationMultipleAnswerQuestionDTO)ret).setParent(DataInterpretationSetQuestionDTO.valueOf(((DataInterpretationMultipleAnswerQuestion) answeredQuestion.getQuestion()).getParent(), true));
        }
        else if (answeredQuestion.getQuestion() instanceof DataInterpretationNumericQuestion){
            ret = DataInterpretationNumericQuestionDTO.valueOf((DataInterpretationNumericQuestion) answeredQuestion.getQuestion());
            ((DataInterpretationNumericQuestionDTO)ret).setParent(DataInterpretationSetQuestionDTO.valueOf(((DataInterpretationNumericQuestion) answeredQuestion.getQuestion()).getParent(), true));
        }
        else if (answeredQuestion.getQuestion() instanceof NumericQuestion)
            ret = NumericQuestionDTO.valueOf((NumericQuestion) answeredQuestion.getQuestion());
        else if (answeredQuestion.getQuestion() instanceof QuantitativeComparisonQuestion)
            ret = QuantitativeComparisonQuestionDTO.valueOf((QuantitativeComparisonQuestion) answeredQuestion.getQuestion());
        else if (answeredQuestion.getQuestion() instanceof QuantitativeSingleAnswerQuestion)
            ret = QuantitativeSingleAnswerQuestionDTO.valueOf((QuantitativeSingleAnswerQuestion) answeredQuestion.getQuestion());
        else if (answeredQuestion.getQuestion() instanceof QuantitativeMultipleAnswerQuestion)
            ret = QuantitativeMultipleAnswerQuestionDTO.valueOf((QuantitativeMultipleAnswerQuestion) answeredQuestion.getQuestion());
        else if (answeredQuestion.getQuestion() instanceof ReadingComprehensionQuestion)
            ret = ReadingComprehensionQuestionDTO.valueOf((ReadingComprehensionQuestion) answeredQuestion.getQuestion());
        else if (answeredQuestion.getQuestion() instanceof ReadingComprehensionSingleAnswerQuestion){
            ret = ReadingComprehensionSingleAnswerQuestionDTO.valueOf((ReadingComprehensionSingleAnswerQuestion) answeredQuestion.getQuestion());
            ((ReadingComprehensionSingleAnswerQuestionDTO)ret).setParent(ReadingComprehensionQuestionDTO.valueOf(((ReadingComprehensionSingleAnswerQuestion) answeredQuestion.getQuestion()).getParent(), true));
        }
        else if (answeredQuestion.getQuestion() instanceof ReadingComprehensionMultipleAnswerQuestion){
            ret = ReadingComprehensionMultipleAnswerQuestionDTO.valueOf((ReadingComprehensionMultipleAnswerQuestion) answeredQuestion.getQuestion());
            ((ReadingComprehensionMultipleAnswerQuestionDTO)ret).setParent(ReadingComprehensionQuestionDTO.valueOf(((ReadingComprehensionMultipleAnswerQuestion) answeredQuestion.getQuestion()).getParent(), true));
        }
        else if (answeredQuestion.getQuestion() instanceof SelectInPassageQuestion){
            ret = SelectInPassageQuestionDTO.valueOf((SelectInPassageQuestion) answeredQuestion.getQuestion());
            ((SelectInPassageQuestionDTO)ret).setParent(ReadingComprehensionQuestionDTO.valueOf(((SelectInPassageQuestion) answeredQuestion.getQuestion()).getParent(), true));
        }
        else if (answeredQuestion.getQuestion() instanceof SentenceEquivalenceQuestion)
            ret = SentenceEquivalenceQuestionDTO.valueOf((SentenceEquivalenceQuestion) answeredQuestion.getQuestion());
        else if (answeredQuestion.getQuestion() instanceof TextCompletionQuestion)
            ret = TextCompletionQuestionDTO.valueOf((TextCompletionQuestion) answeredQuestion.getQuestion());
        else if (answeredQuestion.getQuestion() instanceof WritingQuestion)
            ret = WritingQuestionDTO.valueOf((WritingQuestion) answeredQuestion.getQuestion());
        else
            throw new IllegalArgumentException("Can't find Static Factory for type of Question");
        ret.setId(answeredQuestion.getId());
        ret.setNumber(answeredQuestion.getNumber());
        ret.setUserAnswer(answeredQuestion.getUserAnswer());
        return ret;
    }
}
