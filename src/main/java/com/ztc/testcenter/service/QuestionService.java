package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.question.Question;
import com.ztc.testcenter.domain.question.QuestionType;
import com.ztc.testcenter.domain.test.AnsweredQuestion;
import com.ztc.testcenter.repository.question.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yubar on 5/29/17.
 */

@Service
@Transactional
public class QuestionService {

    private final DataInterpretationSetQuestionRepository dataInterpretationSetQuestionRepository;
    private final DataInterpretationSingleAnswerQuestionRepository dataInterpretationSingleAnswerQuestionRepository;
    private final DataInterpretationMultipleAnswerQuestionRepository dataInterpretationMultipleAnswerQuestionRepository;
    private final DataInterpretationNumericQuestionRepository dataInterpretationNumericQuestionRepository;
    private final NumericQuestionRepository numericQuestionRepository;
    private final QuantitativeComparisonQuestionRepository quantitativeComparisonQuestionRepository;
    private final QuantitativeSingleAnswerQuestionRepository quantitativeSingleAnswerQuestionRepository;
    private final QuantitativeMultipleAnswerQuestionRepository quantitativeMultipleAnswerQuestionRepository;
    private final ReadingComprehensionQuestionRepository readingComprehensionQuestionRepository;
    private final ReadingComprehensionSingleAnswerQuestionRepository readingComprehensionSingleAnswerQuestionRepository;
    private final ReadingComprehensionMultipleAnswerQuestionRepository readingComprehensionMultipleAnswerQuestionRepository;
    private final SelectInPassageQuestionRepository selectInPassageQuestionRepository;
    private final SentenceEquivalenceQuestionRepository sentenceEquivalenceQuestionRepository;
    private final TextCompletionQuestionRepository textCompletionQuestionRepository;
    private final WritingQuestionRepository writingQuestionRepository;

    @Autowired
    public QuestionService(DataInterpretationSetQuestionRepository dataInterpretationSetQuestionRepository, DataInterpretationSingleAnswerQuestionRepository dataInterpretationSingleAnswerQuestionRepository, DataInterpretationMultipleAnswerQuestionRepository dataInterpretationMultipleAnswerQuestionRepository, DataInterpretationNumericQuestionRepository dataInterpretationNumericQuestionRepository, NumericQuestionRepository numericQuestionRepository, QuantitativeComparisonQuestionRepository quantitativeComparisonQuestionRepository, QuantitativeSingleAnswerQuestionRepository quantitativeSingleAnswerQuestionRepository, QuantitativeMultipleAnswerQuestionRepository quantitativeMultipleAnswerQuestionRepository, ReadingComprehensionQuestionRepository readingComprehensionQuestionRepository, ReadingComprehensionSingleAnswerQuestionRepository readingComprehensionSingleAnswerQuestionRepository, ReadingComprehensionMultipleAnswerQuestionRepository readingComprehensionMultipleAnswerQuestionRepository, SelectInPassageQuestionRepository selectInPassageQuestionRepository, SentenceEquivalenceQuestionRepository sentenceEquivalenceQuestionRepository, TextCompletionQuestionRepository textCompletionQuestionRepository, WritingQuestionRepository writingQuestionRepository) {
        this.dataInterpretationSetQuestionRepository = dataInterpretationSetQuestionRepository;
        this.dataInterpretationSingleAnswerQuestionRepository = dataInterpretationSingleAnswerQuestionRepository;
        this.dataInterpretationMultipleAnswerQuestionRepository = dataInterpretationMultipleAnswerQuestionRepository;
        this.dataInterpretationNumericQuestionRepository = dataInterpretationNumericQuestionRepository;
        this.numericQuestionRepository = numericQuestionRepository;
        this.quantitativeComparisonQuestionRepository = quantitativeComparisonQuestionRepository;
        this.quantitativeSingleAnswerQuestionRepository = quantitativeSingleAnswerQuestionRepository;
        this.quantitativeMultipleAnswerQuestionRepository = quantitativeMultipleAnswerQuestionRepository;
        this.readingComprehensionQuestionRepository = readingComprehensionQuestionRepository;
        this.readingComprehensionSingleAnswerQuestionRepository = readingComprehensionSingleAnswerQuestionRepository;
        this.readingComprehensionMultipleAnswerQuestionRepository = readingComprehensionMultipleAnswerQuestionRepository;
        this.selectInPassageQuestionRepository = selectInPassageQuestionRepository;
        this.sentenceEquivalenceQuestionRepository = sentenceEquivalenceQuestionRepository;
        this.textCompletionQuestionRepository = textCompletionQuestionRepository;
        this.writingQuestionRepository = writingQuestionRepository;
    }

    public Question findQuestion(Long id, QuestionType questionType) {
        switch (questionType) {
            case GRE_DATA_INTERPRETATION_SET:
                return dataInterpretationSetQuestionRepository.findOne(id);
            case GRE_DATA_INTERPRETATION_SET_SINGLE_ANSWER:
                return dataInterpretationSingleAnswerQuestionRepository.findOne(id);
            case GRE_DATA_INTERPRETATION_SET_MULTIPLE_ANSWER:
                return dataInterpretationMultipleAnswerQuestionRepository.findOne(id);
            case GRE_DATA_INTERPRETATION_SET_NUMERIC:
                return dataInterpretationNumericQuestionRepository.findOne(id);
            case GRE_NUMERIC:
            case GRE_NUMERIC_FRACTION:
                return numericQuestionRepository.findOne(id);
            case GRE_QUANTITATIVE_COMPARISON:
                return quantitativeComparisonQuestionRepository.findOne(id);
            case GRE_QUANTITATIVE_SINGLE_ANSWER:
                return quantitativeSingleAnswerQuestionRepository.findOne(id);
            case GRE_QUANTITATIVE_MULTIPLE_ANSWER:
                return quantitativeMultipleAnswerQuestionRepository.findOne(id);
            case GRE_READING_COMPREHENSION_SHORT:
            case GRE_READING_COMPREHENSION_MEDIUM:
            case GRE_READING_COMPREHENSION_LONG:
            case GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT:
                return readingComprehensionQuestionRepository.findOne(id);
            case GRE_READING_COMPREHENSION_SINGLE_ANSWER:
                return readingComprehensionSingleAnswerQuestionRepository.findOne(id);
            case GRE_READING_COMPREHENSION_MULTIPLE_ANSWER:
                return readingComprehensionMultipleAnswerQuestionRepository.findOne(id);
            case GRE_READING_COMPREHENSION_SELECT_IN_PASSAGE:
                return selectInPassageQuestionRepository.findOne(id);
            case GRE_SENTENCE_EQUIVALENCE:
                return sentenceEquivalenceQuestionRepository.findOne(id);
            case GRE_TEXT_COMPLETION_ONE_BLANK:
            case GRE_TEXT_COMPLETION_TWO_BLANK:
            case GRE_TEXT_COMPLETION_THREE_BLANK:
                return textCompletionQuestionRepository.findOne(id);
            case GRE_WRITING_ISSUE:
            case GRE_WRITING_ARGUMENT:
                return writingQuestionRepository.findOne(id);
            default:
                throw new IllegalArgumentException();
        }
    }

    public List<AnsweredQuestion> findQuestions(List<AnsweredQuestion> answeredQuestions) {
        Map<QuestionType, List<Long>> questionTypeIds = new HashMap<>();
        answeredQuestions.forEach(answeredQuestion -> {
            if (!questionTypeIds.containsKey(answeredQuestion.getQuestionType()))
                questionTypeIds.put(answeredQuestion.getQuestionType(), new ArrayList<>());
            questionTypeIds.get(answeredQuestion.getQuestionType()).add(answeredQuestion.getQuestion().getId());
        });
        questionTypeIds.forEach((questionType, ids) -> {
            List<? extends Question> questions = findQuestions(questionType, ids);
            questions.forEach(question -> {
                for (AnsweredQuestion answeredQuestion : answeredQuestions) {
                    if (answeredQuestion.getQuestion().getId().equals(question.getId())){
                        answeredQuestion.setQuestion(question);
                        break;
                    }
                }
            });
        });
        return answeredQuestions;
    }

    private List<? extends Question> findQuestions(QuestionType questionType, List<Long> ids) {
        List<Question> ret = null;
        switch (questionType) {
            case GRE_DATA_INTERPRETATION_SET:
                return dataInterpretationSetQuestionRepository.findAll(ids);
            case GRE_DATA_INTERPRETATION_SET_SINGLE_ANSWER:
                return dataInterpretationSingleAnswerQuestionRepository.findAll(ids);
            case GRE_DATA_INTERPRETATION_SET_MULTIPLE_ANSWER:
                return dataInterpretationMultipleAnswerQuestionRepository.findAll(ids);
            case GRE_DATA_INTERPRETATION_SET_NUMERIC:
                return dataInterpretationNumericQuestionRepository.findAll(ids);
            case GRE_NUMERIC:
            case GRE_NUMERIC_FRACTION:
                return numericQuestionRepository.findAll(ids);
            case GRE_QUANTITATIVE_COMPARISON:
                return quantitativeComparisonQuestionRepository.findAll(ids);
            case GRE_QUANTITATIVE_SINGLE_ANSWER:
                return quantitativeSingleAnswerQuestionRepository.findAll(ids);
            case GRE_QUANTITATIVE_MULTIPLE_ANSWER:
                return quantitativeMultipleAnswerQuestionRepository.findAll(ids);
            case GRE_READING_COMPREHENSION_SHORT:
            case GRE_READING_COMPREHENSION_MEDIUM:
            case GRE_READING_COMPREHENSION_LONG:
            case GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT:
                return readingComprehensionQuestionRepository.findAll(ids);
            case GRE_READING_COMPREHENSION_SINGLE_ANSWER:
                return readingComprehensionSingleAnswerQuestionRepository.findAll(ids);
            case GRE_READING_COMPREHENSION_MULTIPLE_ANSWER:
                return readingComprehensionMultipleAnswerQuestionRepository.findAll(ids);
            case GRE_READING_COMPREHENSION_SELECT_IN_PASSAGE:
                return selectInPassageQuestionRepository.findAll(ids);
            case GRE_SENTENCE_EQUIVALENCE:
                return sentenceEquivalenceQuestionRepository.findAll(ids);
            case GRE_TEXT_COMPLETION_ONE_BLANK:
            case GRE_TEXT_COMPLETION_TWO_BLANK:
            case GRE_TEXT_COMPLETION_THREE_BLANK:
                return textCompletionQuestionRepository.findAll(ids);
            case GRE_WRITING_ISSUE:
            case GRE_WRITING_ARGUMENT:
                return writingQuestionRepository.findAll(ids);
            default:
                throw new IllegalArgumentException();
        }
    }
}
