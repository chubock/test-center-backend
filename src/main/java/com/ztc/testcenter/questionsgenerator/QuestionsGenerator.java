package com.ztc.testcenter.questionsgenerator;

import com.ztc.testcenter.domain.question.*;
import com.ztc.testcenter.repository.question.QuestionRepository;
import com.ztc.testcenter.repository.question.WritingQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * Created by Yubar on 2/24/2017.
 */

@Service
public class QuestionsGenerator {

    private final QuestionRepository questionRepository;
    private final Random random = new Random(1);

    @Autowired
    public QuestionsGenerator(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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

    @SuppressWarnings("Duplicates")
    @Transactional
    public void createDataInterpretationSetQuestion(Integer number) {
        DataInterpretationSetQuestion question = new DataInterpretationSetQuestion();
        question.setText("This is a sample Data Interpretation Set question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        Integer count = random.nextInt(3);
        Integer difficultyWeight = 0;
        Integer n = 1;
        for (int i=0; i< count; i++) {
            DataInterpretationNumericQuestion dataInterpretationNumericQuestion = new DataInterpretationNumericQuestion();
            createNumericQuestion(i + 1, dataInterpretationNumericQuestion);
            dataInterpretationNumericQuestion.setDifficulty(question.getDifficulty());
            int difficultyLevel = random.nextInt(5);
            dataInterpretationNumericQuestion.setDifficultyLevel(DifficultyLevel.values()[difficultyLevel]);
            difficultyWeight += difficultyLevel;
            dataInterpretationNumericQuestion.setNumber(n++);
            question.getNumericQuestions().add(dataInterpretationNumericQuestion);
        }
        count = random.nextInt(3);
        for (int i=0; i< count; i++) {
            DataInterpretationMultipleAnswerQuestion dataInterpretationMultipleAnswerQuestion = new DataInterpretationMultipleAnswerQuestion();
            createQuantitativeMultipleAnswerQuestion(i + 1, dataInterpretationMultipleAnswerQuestion);
            dataInterpretationMultipleAnswerQuestion.setDifficulty(question.getDifficulty());
            int difficultyLevel = random.nextInt(5);
            dataInterpretationMultipleAnswerQuestion.setDifficultyLevel(DifficultyLevel.values()[difficultyLevel]);
            dataInterpretationMultipleAnswerQuestion.setNumber(n++);
            difficultyWeight += difficultyLevel;
            question.getMultipleAnswerQuestions().add(dataInterpretationMultipleAnswerQuestion);
        }
        count = random.nextInt(3);
        for (int i=0; i< count; i++) {
            DataInterpretationMultipleAnswerQuestion dataInterpretationMultipleAnswerQuestion = new DataInterpretationMultipleAnswerQuestion();
            createQuantitativeMultipleAnswerQuestion(i + 1, dataInterpretationMultipleAnswerQuestion);
            dataInterpretationMultipleAnswerQuestion.setDifficulty(question.getDifficulty());
            int difficultyLevel = random.nextInt(5);
            dataInterpretationMultipleAnswerQuestion.setDifficultyLevel(DifficultyLevel.values()[difficultyLevel]);
            difficultyWeight += difficultyLevel;
            dataInterpretationMultipleAnswerQuestion.setNumber(n++);
            question.getMultipleAnswerQuestions().add(dataInterpretationMultipleAnswerQuestion);
        }
        question.setDifficultyLevel(DifficultyLevel.values()[difficultyWeight / (question.getMultipleAnswerQuestions().size() + question.getSingleAnswerQuestions().size() + question.getNumericQuestions().size())]);
        question.prepare();
        questionRepository.save(question);
    }

    @SuppressWarnings("Duplicates")
    @Transactional
    public void createReadingComprehensionQuestion(Integer number) {
        ReadingComprehensionQuestion question = new ReadingComprehensionQuestion();
        question.setText("This is a sample Reading Comprehension question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        Integer count = random.nextInt(3);
        Integer difficultyWeight = 0;
        Integer n = 1;
        for (int i=0; i< count; i++) {
            ReadingComprehensionSingleAnswerQuestion readingComprehensionSingleAnswerQuestion = new ReadingComprehensionSingleAnswerQuestion();
            readingComprehensionSingleAnswerQuestion.setText("This is a sample Reading Comprehension Single Answer question " + (i + 1));
            readingComprehensionSingleAnswerQuestion.setDifficulty(question.getDifficulty());
            int difficultyLevel = random.nextInt(5);
            readingComprehensionSingleAnswerQuestion.setDifficultyLevel(DifficultyLevel.values()[difficultyLevel]);
            difficultyWeight += difficultyLevel;
            createSingleAnswerChoices(readingComprehensionSingleAnswerQuestion.getChoices(), 5);
            readingComprehensionSingleAnswerQuestion.setNumber(n++);
            question.getSingleAnswerQuestions().add(readingComprehensionSingleAnswerQuestion);
        }
        count = random.nextInt(3);
        for (int i=0; i< count; i++) {
            ReadingComprehensionMultipleAnswerQuestion readingComprehensionMultipleAnswerQuestion = new ReadingComprehensionMultipleAnswerQuestion();
            readingComprehensionMultipleAnswerQuestion.setText("This is a sample Reading Comprehension Multiple Answer question " + (i + 1));
            readingComprehensionMultipleAnswerQuestion.setDifficulty(question.getDifficulty());
            int difficultyLevel = random.nextInt(5);
            readingComprehensionMultipleAnswerQuestion.setDifficultyLevel(DifficultyLevel.values()[difficultyLevel]);
            difficultyWeight += difficultyLevel;
            createMultipleAnswerChoices(readingComprehensionMultipleAnswerQuestion.getChoices(), 3, 3, 1);
            readingComprehensionMultipleAnswerQuestion.setNumber(n++);
            question.getMultipleAnswerQuestions().add(readingComprehensionMultipleAnswerQuestion);
        }
        count = random.nextInt(2);
        for (int i=0; i< count; i++) {
            SelectInPassageQuestion selectInPassageQuestion = new SelectInPassageQuestion();
            selectInPassageQuestion.setText("<p><u>This</u> is a <u>sample</u> <u>Select</u> In <u>Passage Question</u>.</p>");
            selectInPassageQuestion.setNumber(n++);
            selectInPassageQuestion.setAnswer(random.nextInt(4));
            selectInPassageQuestion.setDifficulty(question.getDifficulty());
            int difficultyLevel = random.nextInt(5);
            selectInPassageQuestion.setDifficultyLevel(DifficultyLevel.values()[difficultyLevel]);
            difficultyWeight += difficultyLevel;
            question.getSelectInPassageQuestions().add(selectInPassageQuestion);
        }
        question.setDifficultyLevel(DifficultyLevel.values()[difficultyWeight / (question.getMultipleAnswerQuestions().size() + question.getSingleAnswerQuestions().size() + question.getSelectInPassageQuestions().size())]);
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
