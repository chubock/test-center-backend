package com.ztc.testcenter.gre.service;

import com.ztc.testcenter.gre.domain.question.*;
import com.ztc.testcenter.gre.repository.question.*;
import com.ztc.testcenter.gre.specification.QuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by yubar on 5/29/17.
 */

@Service
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
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
    private final QuestionTemplateRepository questionTemplateRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, DataInterpretationSetQuestionRepository dataInterpretationSetQuestionRepository, DataInterpretationSingleAnswerQuestionRepository dataInterpretationSingleAnswerQuestionRepository, DataInterpretationMultipleAnswerQuestionRepository dataInterpretationMultipleAnswerQuestionRepository, DataInterpretationNumericQuestionRepository dataInterpretationNumericQuestionRepository, NumericQuestionRepository numericQuestionRepository, QuantitativeComparisonQuestionRepository quantitativeComparisonQuestionRepository, QuantitativeSingleAnswerQuestionRepository quantitativeSingleAnswerQuestionRepository, QuantitativeMultipleAnswerQuestionRepository quantitativeMultipleAnswerQuestionRepository, ReadingComprehensionQuestionRepository readingComprehensionQuestionRepository, ReadingComprehensionSingleAnswerQuestionRepository readingComprehensionSingleAnswerQuestionRepository, ReadingComprehensionMultipleAnswerQuestionRepository readingComprehensionMultipleAnswerQuestionRepository, SelectInPassageQuestionRepository selectInPassageQuestionRepository, SentenceEquivalenceQuestionRepository sentenceEquivalenceQuestionRepository, TextCompletionQuestionRepository textCompletionQuestionRepository, WritingQuestionRepository writingQuestionRepository, QuestionTemplateRepository questionTemplateRepository) {
        this.questionRepository = questionRepository;
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
        this.questionTemplateRepository = questionTemplateRepository;
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

    public <T extends Question> Page<T> findAllQuestions(QuestionSpecification<T> specification, Pageable pageable, Class<T> type) {
        return findSpecificationExecutor(specification, type).findAll(specification, pageable);
    }

    public Question save(Question question) {
        if (question instanceof QuestionsContainer)
            saveTemplate((QuestionsContainer) question);
        question.prepare();
        return questionRepository.save(question);
    }

    private void saveTemplate(QuestionsContainer questionsContainer) {
        Question question = (Question) questionsContainer;
        QuestionTemplate template = QuestionTemplate.templateOf(questionsContainer);
        QuestionTemplate newTemplate = questionTemplateRepository.findByLabel(template.getLabel());
        QuestionTemplate currentTemplate = question.getId() == null || question.getId() == 0 ? null : question.getTemplate();
        if (newTemplate == null)
            question.setTemplate(newTemplate = questionTemplateRepository.save(template));
        else
            question.setTemplate(newTemplate);
        if (currentTemplate == null || !Objects.equals(newTemplate.getId(), currentTemplate.getId())) {
            newTemplate.setCount(newTemplate.getCount() + 1);
            if (currentTemplate != null)
                currentTemplate.setCount(currentTemplate.getCount() - 1);
        }
    }

    public void delete(Long id) {
        questionRepository.delete(questionRepository.getOne(id));
    }

    public List<? extends Question> findQuestions(QuestionType questionType, List<Long> ids) {
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

    @SuppressWarnings("unchecked")
    private <T extends Question> JpaSpecificationExecutor<T> findSpecificationExecutor(QuestionSpecification<T> specification, Class<T> type) {
        if (type.equals(DataInterpretationSetQuestion.class))
            return (JpaSpecificationExecutor<T>) dataInterpretationSetQuestionRepository;
        else if (type.equals(NumericQuestion.class))
            return (JpaSpecificationExecutor<T>) numericQuestionRepository;
        else if (type.equals(QuantitativeComparisonQuestion.class))
            return (JpaSpecificationExecutor<T>) quantitativeComparisonQuestionRepository;
        else if (type.equals(QuantitativeMultipleAnswerQuestion.class))
            return (JpaSpecificationExecutor<T>) quantitativeMultipleAnswerQuestionRepository;
        else if (type.equals(QuantitativeSingleAnswerQuestion.class))
            return (JpaSpecificationExecutor<T>) quantitativeSingleAnswerQuestionRepository;
        else if (type.equals(ReadingComprehensionQuestion.class))
            return (JpaSpecificationExecutor<T>) readingComprehensionQuestionRepository;
        else if (type.equals(SentenceEquivalenceQuestion.class))
            return (JpaSpecificationExecutor<T>) sentenceEquivalenceQuestionRepository;
        else if (type.equals(TextCompletionQuestion.class))
            return (JpaSpecificationExecutor<T>) textCompletionQuestionRepository;
        else if (type.equals(WritingQuestion.class))
            return (JpaSpecificationExecutor<T>) writingQuestionRepository;
        return null;
    }
}
