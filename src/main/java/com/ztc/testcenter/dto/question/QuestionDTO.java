package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.File;
import com.ztc.testcenter.domain.question.*;
import com.ztc.testcenter.domain.test.AnsweredQuestion;
import com.ztc.testcenter.dto.AbstractDTO;

/**
 * Created by Yubar on 1/19/2017.
 */
public abstract class QuestionDTO<T extends Question> extends AbstractDTO<T> {

    private Long id;
    private Integer number;
    private String text;
    private Long image;
    private Difficulty difficulty = Difficulty.MEDIUM;
    private DifficultyLevel difficultyLevel = DifficultyLevel.LEVEL3;
    private QuestionType questionType;
    private Boolean free = false;

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

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public T convert(T question) {
        question.setId(getId());
        question.setText(getText());
        question.setDifficulty(getDifficulty());
        question.setDifficultyLevel(getDifficultyLevel());
        question.setFree(getFree());
        return question;
    }

    void copy(T question) {
        setId(question.getId());
        setText(question.getText());
        setDifficulty(question.getDifficulty());
        setDifficultyLevel(question.getDifficultyLevel());
        setQuestionType(question.getQuestionType());
        setFree(question.getFree());
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

        /*
        switch (answeredQuestion.getQuestion().getQuestionType()) {
            case GRE_DATA_INTERPRETATION_SET:
                ret = DataInterpretationSetQuestionDTO.valueOf((DataInterpretationSetQuestion) answeredQuestion.getQuestion());
                break;
            case GRE_DATA_INTERPRETATION_SET_SINGLE_ANSWER:
                ret = DataInterpretationSingleAnswerQuestionDTO.valueOf((DataInterpretationSingleAnswerQuestion) answeredQuestion.getQuestion());
                ((DataInterpretationSingleAnswerQuestionDTO)ret).setParent(DataInterpretationSetQuestionDTO.valueOf(((DataInterpretationSingleAnswerQuestion) answeredQuestion.getQuestion()).getParent(), true));
                break;
            case GRE_DATA_INTERPRETATION_SET_MULTIPLE_ANSWER:
                ret = DataInterpretationMultipleAnswerQuestionDTO.valueOf((DataInterpretationMultipleAnswerQuestion) answeredQuestion.getQuestion());
                ((DataInterpretationMultipleAnswerQuestionDTO)ret).setParent(DataInterpretationSetQuestionDTO.valueOf(((DataInterpretationMultipleAnswerQuestion) answeredQuestion.getQuestion()).getParent(), true));
                break;
            case GRE_DATA_INTERPRETATION_SET_NUMERIC:
                ret = DataInterpretationNumericQuestionDTO.valueOf((DataInterpretationNumericQuestion) answeredQuestion.getQuestion());
                ((DataInterpretationNumericQuestionDTO)ret).setParent(DataInterpretationSetQuestionDTO.valueOf(((DataInterpretationNumericQuestion) answeredQuestion.getQuestion()).getParent(), true));
                break;
            case GRE_NUMERIC:
            case GRE_NUMERIC_FRACTION:
                ret = NumericQuestionDTO.valueOf((NumericQuestion) answeredQuestion.getQuestion());
                break;
            case GRE_QUANTITATIVE_COMPARISON:
                ret = QuantitativeComparisonQuestionDTO.valueOf((QuantitativeComparisonQuestion) answeredQuestion.getQuestion());
                break;
            case GRE_QUANTITATIVE_SINGLE_ANSWER:
                ret = QuantitativeSingleAnswerQuestionDTO.valueOf((QuantitativeSingleAnswerQuestion) answeredQuestion.getQuestion());
                break;
            case GRE_QUANTITATIVE_MULTIPLE_ANSWER:
                ret = QuantitativeMultipleAnswerQuestionDTO.valueOf((QuantitativeMultipleAnswerQuestion) answeredQuestion.getQuestion());
                break;
            case GRE_READING_COMPREHENSION_SHORT:
            case GRE_READING_COMPREHENSION_MEDIUM:
            case GRE_READING_COMPREHENSION_LONG:
            case GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT:
                ret = ReadingComprehensionQuestionDTO.valueOf((ReadingComprehensionQuestion) answeredQuestion.getQuestion());
                break;
            case GRE_READING_COMPREHENSION_SINGLE_ANSWER:
                ret = ReadingComprehensionSingleAnswerQuestionDTO.valueOf((ReadingComprehensionSingleAnswerQuestion) answeredQuestion.getQuestion());
                ((ReadingComprehensionSingleAnswerQuestionDTO)ret).setParent(ReadingComprehensionQuestionDTO.valueOf(((ReadingComprehensionSingleAnswerQuestion) answeredQuestion.getQuestion()).getParent(), true));
                break;
            case GRE_READING_COMPREHENSION_MULTIPLE_ANSWER:
                ret = ReadingComprehensionMultipleAnswerQuestionDTO.valueOf((ReadingComprehensionMultipleAnswerQuestion) answeredQuestion.getQuestion());
                ((ReadingComprehensionMultipleAnswerQuestionDTO)ret).setParent(ReadingComprehensionQuestionDTO.valueOf(((ReadingComprehensionMultipleAnswerQuestion) answeredQuestion.getQuestion()).getParent(), true));
                break;
            case GRE_READING_COMPREHENSION_SELECT_IN_PASSAGE:
                ret = SelectInPassageQuestionDTO.valueOf((SelectInPassageQuestion) answeredQuestion.getQuestion());
                ((SelectInPassageQuestionDTO)ret).setParent(ReadingComprehensionQuestionDTO.valueOf(((SelectInPassageQuestion) answeredQuestion.getQuestion()).getParent(), true));
                break;
            case GRE_SENTENCE_EQUIVALENCE:
                ret = SentenceEquivalenceQuestionDTO.valueOf((SentenceEquivalenceQuestion) answeredQuestion.getQuestion());
                break;
            case GRE_TEXT_COMPLETION_ONE_BLANK:
            case GRE_TEXT_COMPLETION_TWO_BLANK:
            case GRE_TEXT_COMPLETION_THREE_BLANK:
                ret = TextCompletionQuestionDTO.valueOf((TextCompletionQuestion) answeredQuestion.getQuestion());
                break;
            case GRE_WRITING_ISSUE:
            case GRE_WRITING_ARGUMENT:
                ret = WritingQuestionDTO.valueOf((WritingQuestion) answeredQuestion.getQuestion());
                break;
            default:
                throw new IllegalArgumentException("Can't find Static Factory for type of Question " + answeredQuestion.getQuestion().getQuestionType());
        }
        */

        ret.setId(answeredQuestion.getId());
        ret.setNumber(answeredQuestion.getNumber());
        ret.setUserAnswer(answeredQuestion.getUserAnswer());
        return ret;
    }
}
