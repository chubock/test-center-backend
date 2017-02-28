package com.ztc.testcenter.generator;

import com.ztc.testcenter.domain.question.*;
import com.ztc.testcenter.domain.test.QuestionTemplate;
import com.ztc.testcenter.domain.test.QuestionTemplateItem;
import com.ztc.testcenter.repository.question.QuestionRepository;
import com.ztc.testcenter.repository.test.QuestionTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by Yubar on 2/24/2017.
 */

@Service
public class QuestionsGenerator {

    private final QuestionRepository questionRepository;
    private final Random random = new Random(1);
    private final QuestionTemplateRepository questionTemplateRepository;

    private List<QuestionTemplate> dataInterpretationTemplates;
    private List<QuestionTemplate> readingComprehensionTemplates;

    @Autowired
    public QuestionsGenerator(QuestionRepository questionRepository, QuestionTemplateRepository questionTemplateRepository) {
        this.questionRepository = questionRepository;
        this.questionTemplateRepository = questionTemplateRepository;
    }

    @PostConstruct
    private void init() {
        dataInterpretationTemplates = questionTemplateRepository.findByQuestionType(QuestionType.GRE_DATA_INTERPRETATION_SET);
        readingComprehensionTemplates = questionTemplateRepository.findByQuestionType(QuestionType.GRE_READING_COMPREHENSION);
    }

    private void createSingleAnswerChoices(List<Choice> choices, Integer number) {
        for (int i=0; i< number; i++)
            choices.add(new Choice("Choice " + (i + 1)));
        choices.get(random.nextInt(number)).setAnswer(true);
    }

    private Integer createMultipleAnswerChoices(List<Choice> choices, Integer number, Integer maxAnswer, Integer minAnswer) {
        if (maxAnswer > number)
            throw new IllegalArgumentException();
        if (minAnswer > maxAnswer)
            throw new IllegalArgumentException();
        Integer answersCount = 0;
        for (int i=0; i< number; i++) {
            Choice choice = new Choice("Choice " + (i + 1));
            if (answersCount < maxAnswer)
            choice.setAnswer(random.nextBoolean());
            if (choice.isAnswer())
                answersCount++;
            choices.add(choice);
        }
        while (answersCount < minAnswer) {
            Choice choice = choices.get(random.nextInt(number));
            if (! choice.isAnswer()) {
                choice.setAnswer(true);
                answersCount++;
            }
        }
        return answersCount;
    }

    private void createNumericQuestion(Integer number, AbstractNumericQuestion question) {
        question.setText("This is a sample Numeric question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        question.setDifficultyLevel(DifficultyLevel.values()[random.nextInt(5)]);
        question.setNominatorAnswer((long) (random.nextInt(99) + 1));
        question.setFraction(random.nextBoolean());
        if (question.isFraction())
            question.setDenominatorAnswer((long) (random.nextInt(99) + 1));
    }

    private void createQuantitativeMultipleAnswerQuestion(Integer number, AbstractQuantitativeMultipleAnswerQuestion question) {
        question.setText("This is a sample Quantitative Multiple Answer question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        question.setDifficultyLevel(DifficultyLevel.values()[random.nextInt(5)]);
        Integer answersCount = createMultipleAnswerChoices(question.getChoices(), 3, 3, 1);
        if (answersCount > 1 && answersCount <3 && random.nextBoolean())
            question.setMaxAnswerCount(answersCount);
    }

    private void createQuantitativeSingleAnswerQuestion(Integer number, AbstractQuantitativeSingleAnswerQuestion question) {
        question.setText("This is a sample Quantitative Single Answer question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        question.setDifficultyLevel(DifficultyLevel.values()[random.nextInt(5)]);
        createSingleAnswerChoices(question.getChoices(), 5);
    }

    @Transactional
    public void createNumericQuestion(Integer number) {
        NumericQuestion question = new NumericQuestion();
        createNumericQuestion(number, question);
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    public void createQuantitativeComparisonQuestion(Integer number) {
        QuantitativeComparisonQuestion question = new QuantitativeComparisonQuestion();
        question.setText("Sample Quantitative A in question " + number);
        question.setQuantityB("Sample Quantitative B in question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        question.setDifficultyLevel(DifficultyLevel.values()[random.nextInt(5)]);
        question.setAnswer(random.nextInt(4));
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    public void createQuantitativeMultipleAnswerQuestion(Integer number) {
        QuantitativeMultipleAnswerQuestion question = new QuantitativeMultipleAnswerQuestion();
        createQuantitativeMultipleAnswerQuestion(number, question);
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    public void createQuantitativeSingleAnswerQuestion(Integer number) {
        QuantitativeSingleAnswerQuestion question = new QuantitativeSingleAnswerQuestion();
        createQuantitativeSingleAnswerQuestion(number, question);
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    public void createSentenceEquivalenceQuestion(Integer number) {
        SentenceEquivalenceQuestion question = new SentenceEquivalenceQuestion();
        question.setText("This is a sample Sentence Equivalence question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        question.setDifficultyLevel(DifficultyLevel.values()[random.nextInt(5)]);
        createMultipleAnswerChoices(question.getChoices(), 6, 2, 2);
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    public void createTextCompletionQuestion(Integer number) {
        TextCompletionQuestion question = new TextCompletionQuestion();
        question.setText("This is a sample Text Completion question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        question.setDifficultyLevel(DifficultyLevel.values()[random.nextInt(5)]);
        Integer questionItemsCount = random.nextInt(3) + 1;
        if (questionItemsCount == 1) {
            TextCompletionQuestionItem item = new TextCompletionQuestionItem();
            for (int i=0; i< 5; i++)
                item.getChoices().add(new Choice("Choice " + (i + 1)));
            item.getChoices().get(random.nextInt(5)).setAnswer(true);
            question.getItems().add(item);
        } else {
            for (int i=0; i< questionItemsCount; i++) {
                TextCompletionQuestionItem item = new TextCompletionQuestionItem();
                for (Integer j=0; j< 3; j++)
                    item.getChoices().add(new Choice("Choice " + (j + 1)));
                item.getChoices().get(random.nextInt(3)).setAnswer(true);
                question.getItems().add(item);
            }
        }
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    public void createWritingQuestion(Integer number) {
        WritingQuestion question = new WritingQuestion();
        question.setText("This is a sample Writing question " + number);
        question.setTaskType(WritingQuestion.TaskType.values()[random.nextInt(2)]);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        question.setDifficultyLevel(DifficultyLevel.values()[random.nextInt(5)]);
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    public void createDataInterpretationSetQuestion(int number) {
        QuestionTemplate template = dataInterpretationTemplates.get(random.nextInt(dataInterpretationTemplates.size()));
        DataInterpretationSetQuestion question = new DataInterpretationSetQuestion();
        question.setText("This is a sample Data Interpretation Set question " + number);
        question.setDifficulty(template.getDifficulty());
        int n = 1;
        int difficultyWeight = 0;
        for (QuestionTemplateItem item : template.getQuestionTemplateItems()) {
            for (int i=0; i< item.getCount(); i++) {
                switch (random.nextInt(3)) {
                    case 0:
                        DataInterpretationNumericQuestion dataInterpretationNumericQuestion = new DataInterpretationNumericQuestion();
                        createNumericQuestion(i + 1, dataInterpretationNumericQuestion);
                        dataInterpretationNumericQuestion.setDifficulty(template.getDifficulty());
                        dataInterpretationNumericQuestion.setDifficultyLevel(item.getDifficultyLevel());
                        difficultyWeight += item.getDifficultyLevel().ordinal();
                        dataInterpretationNumericQuestion.setNumber(n++);
                        question.getNumericQuestions().add(dataInterpretationNumericQuestion);
                        break;
                    case 1:
                        DataInterpretationMultipleAnswerQuestion dataInterpretationMultipleAnswerQuestion = new DataInterpretationMultipleAnswerQuestion();
                        createQuantitativeMultipleAnswerQuestion(i + 1, dataInterpretationMultipleAnswerQuestion);
                        dataInterpretationMultipleAnswerQuestion.setDifficulty(template.getDifficulty());
                        dataInterpretationMultipleAnswerQuestion.setDifficultyLevel(item.getDifficultyLevel());
                        difficultyWeight += item.getDifficultyLevel().ordinal();
                        dataInterpretationMultipleAnswerQuestion.setNumber(n++);
                        question.getMultipleAnswerQuestions().add(dataInterpretationMultipleAnswerQuestion);
                        break;
                    case 2:
                        DataInterpretationSingleAnswerQuestion dataInterpretationSingleAnswerQuestion = new DataInterpretationSingleAnswerQuestion();
                        createQuantitativeSingleAnswerQuestion(i + 1, dataInterpretationSingleAnswerQuestion);
                        dataInterpretationSingleAnswerQuestion.setDifficulty(template.getDifficulty());
                        dataInterpretationSingleAnswerQuestion.setDifficultyLevel(item.getDifficultyLevel());
                        difficultyWeight += item.getDifficultyLevel().ordinal();
                        dataInterpretationSingleAnswerQuestion.setNumber(n++);
                        question.getSingleAnswerQuestions().add(dataInterpretationSingleAnswerQuestion);
                }
            }
        }
        int questionsCount = question.getMultipleAnswerQuestions().size() + question.getSingleAnswerQuestions().size() + question.getNumericQuestions().size();
        if (questionsCount == 0)
            return ;
        question.setDifficultyLevel(DifficultyLevel.values()[Math.round((float)difficultyWeight / questionsCount)]);
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    private void createReadingComprehensionQuestion(int number) {
        QuestionTemplate template = dataInterpretationTemplates.get(random.nextInt(dataInterpretationTemplates.size()));
        ReadingComprehensionQuestion question = new ReadingComprehensionQuestion();
        question.setText("This is a sample Reading Comprehension question " + number);
        question.setDifficulty(template.getDifficulty());
        int n = 1;
        int difficultyWeight = 0;
        for (QuestionTemplateItem item : template.getQuestionTemplateItems()) {
            for (int i=0; i< item.getCount(); i++) {
                switch (random.nextInt(3)) {
                    case 0:
                        ReadingComprehensionSingleAnswerQuestion readingComprehensionSingleAnswerQuestion = new ReadingComprehensionSingleAnswerQuestion();
                        readingComprehensionSingleAnswerQuestion.setText("This is a sample Reading Comprehension Single Answer question " + (i + 1));
                        readingComprehensionSingleAnswerQuestion.setDifficulty(template.getDifficulty());
                        readingComprehensionSingleAnswerQuestion.setDifficultyLevel(item.getDifficultyLevel());
                        difficultyWeight += item.getDifficultyLevel().ordinal();
                        createSingleAnswerChoices(readingComprehensionSingleAnswerQuestion.getChoices(), 5);
                        readingComprehensionSingleAnswerQuestion.setNumber(n++);
                        question.getSingleAnswerQuestions().add(readingComprehensionSingleAnswerQuestion);
                        break;
                    case 1:
                        ReadingComprehensionMultipleAnswerQuestion readingComprehensionMultipleAnswerQuestion = new ReadingComprehensionMultipleAnswerQuestion();
                        readingComprehensionMultipleAnswerQuestion.setText("This is a sample Reading Comprehension Multiple Answer question " + (i + 1));
                        readingComprehensionMultipleAnswerQuestion.setDifficulty(template.getDifficulty());
                        readingComprehensionMultipleAnswerQuestion.setDifficultyLevel(item.getDifficultyLevel());
                        difficultyWeight += item.getDifficultyLevel().ordinal();
                        createMultipleAnswerChoices(readingComprehensionMultipleAnswerQuestion.getChoices(), 3, 3, 1);
                        readingComprehensionMultipleAnswerQuestion.setNumber(n++);
                        question.getMultipleAnswerQuestions().add(readingComprehensionMultipleAnswerQuestion);
                        break;
                    case 2:
                        SelectInPassageQuestion selectInPassageQuestion = new SelectInPassageQuestion();
                        selectInPassageQuestion.setText("<p><u>This</u> is a <u>sample</u> <u>Select</u> In <u>Passage Question</u>.</p>");
                        selectInPassageQuestion.setNumber(n++);
                        selectInPassageQuestion.setAnswer(random.nextInt(4));
                        selectInPassageQuestion.setDifficulty(template.getDifficulty());
                        selectInPassageQuestion.setDifficultyLevel(item.getDifficultyLevel());
                        difficultyWeight += item.getDifficultyLevel().ordinal();
                        question.getSelectInPassageQuestions().add(selectInPassageQuestion);
                }
            }
        }
        int questionsCount = question.getMultipleAnswerQuestions().size() + question.getSingleAnswerQuestions().size() + question.getSelectInPassageQuestions().size();
        if (questionsCount == 0)
            return ;
        question.setDifficultyLevel(DifficultyLevel.values()[Math.round((float) difficultyWeight / questionsCount)]);
        question.prepare();
        questionRepository.save(question);
    }

    public void generateAll(Integer count) {
        for (int i=0; i< count; i++) {
            createDataInterpretationSetQuestion(i + 1);
            createNumericQuestion(i + 1);
            createQuantitativeComparisonQuestion(i + 1);
            createQuantitativeMultipleAnswerQuestion(i + 1);
            createQuantitativeSingleAnswerQuestion(i + 1);
            createReadingComprehensionQuestion(i + 1);
            createSentenceEquivalenceQuestion(i + 1);
            createTextCompletionQuestion(i + 1);
            createWritingQuestion(i + 1);
        }
    }

}
