package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.File;
import com.ztc.testcenter.domain.question.*;
import com.ztc.testcenter.domain.test.AnsweredQuestion;
import com.ztc.testcenter.dto.AbstractDTO;
import com.ztc.testcenter.dto.FileDTO;

import java.util.function.Function;

/**
 * Created by Yubar on 1/19/2017.
 */
public abstract class QuestionDTO extends AbstractDTO<Question> {

    private Long id;
    private String text;
    private Long image;
    private Difficulty difficulty = Difficulty.MEDIUM;
    private DifficultyLevel difficultyLevel = DifficultyLevel.LEVEL3;

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

    abstract void setAnswer(String answer);

    public static QuestionDTO valueOf(AnsweredQuestion answeredQuestion) {
        switch (answeredQuestion.getQuestionType()) {
            case GRE_DATA_INTERPRETATION_SET:
                return DataInterpretationSetQuestionDTO.valueOf(answeredQuestion);
            case GRE_DATA_INTERPRETATION_SET_SINGLE_ANSWER:
                return DataInterpretationSingleAnswerQuestionDTO.valueOf(answeredQuestion);
            case GRE_DATA_INTERPRETATION_SET_MULTIPLE_ANSWER:
                return DataInterpretationMultipleAnswerQuestionDTO.valueOf(answeredQuestion);
            case GRE_DATA_INTERPRETATION_SET_NUMERIC:
                return DataInterpretationNumericQuestionDTO.valueOf(answeredQuestion);
            case GRE_NUMERIC:
            case GRE_NUMERIC_FRACTION:
                return NumericQuestionDTO.valueOf(answeredQuestion);
            case GRE_QUANTITATIVE_COMPARISON:
                return QuantitativeComparisonQuestionDTO.valueOf(answeredQuestion);
            case GRE_QUANTITATIVE_SINGLE_ANSWER:
                return QuantitativeSingleAnswerQuestionDTO.valueOf(answeredQuestion);
            case GRE_QUANTITATIVE_MULTIPLE_ANSWER:
                return QuantitativeMultipleAnswerQuestionDTO.valueOf(answeredQuestion);
            case GRE_READING_COMPREHENSION_SHORT:
            case GRE_READING_COMPREHENSION_MEDIUM:
            case GRE_READING_COMPREHENSION_LONG:
            case GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT:
                return NumericQuestionDTO.valueOf(answeredQuestion);
            case GRE_READING_COMPREHENSION_SINGLE_ANSWER:
                return ReadingComprehensionSingleAnswerQuestionDTO.valueOf(answeredQuestion);
            case GRE_READING_COMPREHENSION_MULTIPLE_ANSWER:
                return ReadingComprehensionMultipleAnswerQuestionDTO.valueOf(answeredQuestion);
            case GRE_READING_COMPREHENSION_SELECT_IN_PASSAGE:
                return SelectInPassageQuestionDTO.valueOf(answeredQuestion);
            case GRE_SENTENCE_EQUIVALENCE:
                return SentenceEquivalenceQuestionDTO.valueOf(answeredQuestion);
            case GRE_TEXT_COMPLETION_ONE_BLANK:
            case GRE_TEXT_COMPLETION_TWO_BLANK:
            case GRE_TEXT_COMPLETION_THREE_BLANK:
                return TextCompletionQuestionDTO.valueOf(answeredQuestion);
            case GRE_WRITING_ARGUMENT:
            case GRE_WRITING_ISSUE:
                return SelectInPassageQuestionDTO.valueOf(answeredQuestion);
        }
        throw new IllegalArgumentException("Can't find Static Factory for type of Question");
    }
}
