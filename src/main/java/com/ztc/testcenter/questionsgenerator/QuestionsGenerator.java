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

    private void createSingleAnswerChoices(List<Choice> choices, int number) {
        for (int i=0; i< number; i++)
            choices.add(new Choice("Choice " + (i + 1)));
        choices.get(random.nextInt(number)).setAnswer(true);
    }

    private int createMultipleAnswerChoices(List<Choice> choices, int number, int maxAnswer, int minAnswer) {
        if (maxAnswer > number)
            throw new IllegalArgumentException();
        if (minAnswer > maxAnswer)
            throw new IllegalArgumentException();
        int answersCount = 0;
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

    private void createNumericQuestion(int number, AbstractNumericQuestion question) {
        question.setText("This is a sample Numeric question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        question.setNominatorAnswer(random.nextInt(99) + 1);
        question.setFraction(random.nextBoolean());
        if (question.isFraction())
            question.setDenominatorAnswer(random.nextInt(99) + 1);
    }

    private void createQuantitativeMultipleAnswerQuestion(int number, AbstractQuantitativeMultipleAnswerQuestion question) {
        question.setText("This is a sample Quantitative Multiple Answer question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        int answersCount = createMultipleAnswerChoices(question.getChoices(), 3, 3, 1);
        if (answersCount > 1 && answersCount <3 && random.nextBoolean())
            question.setMaxAnswerCount(answersCount);
    }

    private void createQuantitativeSingleAnswerQuestion(int number, AbstractQuantitativeSingleAnswerQuestion question) {
        question.setText("This is a sample Quantitative Single Answer question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        createSingleAnswerChoices(question.getChoices(), 5);
    }

    @Transactional
    public void createNumericQuestion(int number) {
        NumericQuestion question = new NumericQuestion();
        createNumericQuestion(number, question);
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    public void createQuantitativeComparisonQuestion(int number) {
        QuantitativeComparisonQuestion question = new QuantitativeComparisonQuestion();
        question.setText("Sample Quantitative A in question " + number);
        question.setQuantityB("Sample Quantitative B in question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        question.setAnswer(random.nextInt(4));
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    public void createQuantitativeMultipleAnswerQuestion(int number) {
        QuantitativeMultipleAnswerQuestion question = new QuantitativeMultipleAnswerQuestion();
        createQuantitativeMultipleAnswerQuestion(number, question);
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    public void createQuantitativeSingleAnswerQuestion(int number) {
        QuantitativeSingleAnswerQuestion question = new QuantitativeSingleAnswerQuestion();
        createQuantitativeSingleAnswerQuestion(number, question);
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    public void createSentenceEquivalenceQuestion(int number) {
        SentenceEquivalenceQuestion question = new SentenceEquivalenceQuestion();
        question.setText("This is a sample Sentence Equivalence question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        createMultipleAnswerChoices(question.getChoices(), 6, 2, 2);
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    public void createTextCompletionQuestion(int number) {
        TextCompletionQuestion question = new TextCompletionQuestion();
        question.setText("This is a sample Text Completion question " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        int questionItemsCount = random.nextInt(3) + 1;
        if (questionItemsCount == 1) {
            TextCompletionQuestionItem item = new TextCompletionQuestionItem();
            for (int i=0; i< 5; i++)
                item.getChoices().add(new Choice("Choice " + (i + 1)));
            item.getChoices().get(random.nextInt(5)).setAnswer(true);
            question.getItems().add(item);
        } else {
            for (int i=0; i< questionItemsCount; i++) {
                TextCompletionQuestionItem item = new TextCompletionQuestionItem();
                for (int j=0; j< 3; j++)
                    item.getChoices().add(new Choice("Choice " + (j + 1)));
                item.getChoices().get(random.nextInt(3)).setAnswer(true);
                question.getItems().add(item);
            }
        }
        question.prepare();
        questionRepository.save(question);
    }

    @Transactional
    public void createWritingQuestion(int number) {
        WritingQuestion question = new WritingQuestion();
        question.setText("This is a sample Writing question " + number);
        question.setTaskType(WritingQuestion.TaskType.values()[random.nextInt(2)]);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        question.prepare();
        questionRepository.save(question);
    }

    @SuppressWarnings("Duplicates")
    @Transactional
    public void createDataInterpretationSetQuestion(int number) {
        DataInterpretationSetQuestion question = new DataInterpretationSetQuestion();
        question.setText("This is a sample Data Interpretation Set question " + number);
        int count = random.nextInt(3);
        int difficultyWeight = 0;
        int n = 1;
        for (int i=0; i< count; i++) {
            DataInterpretationNumericQuestion dataInterpretationNumericQuestion = new DataInterpretationNumericQuestion();
            createNumericQuestion(i + 1, dataInterpretationNumericQuestion);
            dataInterpretationNumericQuestion.setNumber(n++);
            question.getNumericQuestions().add(dataInterpretationNumericQuestion);
            switch (dataInterpretationNumericQuestion.getDifficulty()) {
                case EASY:
                    difficultyWeight--;
                    break;
                case HARD:
                    difficultyWeight++;
            }
        }
        count = random.nextInt(3);
        for (int i=0; i< count; i++) {
            DataInterpretationMultipleAnswerQuestion dataInterpretationMultipleAnswerQuestion = new DataInterpretationMultipleAnswerQuestion();
            createQuantitativeMultipleAnswerQuestion(i + 1, dataInterpretationMultipleAnswerQuestion);
            dataInterpretationMultipleAnswerQuestion.setNumber(n++);
            question.getMultipleAnswerQuestions().add(dataInterpretationMultipleAnswerQuestion);
            switch (dataInterpretationMultipleAnswerQuestion.getDifficulty()) {
                case EASY:
                    difficultyWeight--;
                    break;
                case HARD:
                    difficultyWeight++;
            }
        }
        count = random.nextInt(3);
        for (int i=0; i< count; i++) {
            DataInterpretationMultipleAnswerQuestion dataInterpretationMultipleAnswerQuestion = new DataInterpretationMultipleAnswerQuestion();
            createQuantitativeMultipleAnswerQuestion(i + 1, dataInterpretationMultipleAnswerQuestion);
            dataInterpretationMultipleAnswerQuestion.setNumber(n++);
            question.getMultipleAnswerQuestions().add(dataInterpretationMultipleAnswerQuestion);
            switch (dataInterpretationMultipleAnswerQuestion.getDifficulty()) {
                case EASY:
                    difficultyWeight--;
                    break;
                case HARD:
                    difficultyWeight++;
            }
        }
        if (difficultyWeight < 0)
            question.setDifficulty(Difficulty.EASY);
        else if (difficultyWeight > 0)
            question.setDifficulty(Difficulty.HARD);
        else
            question.setDifficulty(Difficulty.MEDIUM);
        question.prepare();
        questionRepository.save(question);
    }

    @SuppressWarnings("Duplicates")
    @Transactional
    public void createReadingComprehensionQuestion(int number) {
        ReadingComprehensionQuestion question = new ReadingComprehensionQuestion();
        question.setText("This is a sample Reading Comprehension question " + number);
        int count = random.nextInt(3);
        int difficultyWeight = 0;
        int n = 1;
        for (int i=0; i< count; i++) {
            ReadingComprehensionSingleAnswerQuestion readingComprehensionSingleAnswerQuestion = new ReadingComprehensionSingleAnswerQuestion();
            readingComprehensionSingleAnswerQuestion.setText("This is a sample Reading Comprehension Single Answer question " + (i + 1));
            readingComprehensionSingleAnswerQuestion.setDifficulty(Difficulty.values()[random.nextInt(3)]);
            createSingleAnswerChoices(readingComprehensionSingleAnswerQuestion.getChoices(), 5);
            readingComprehensionSingleAnswerQuestion.setNumber(n++);
            question.getSingleAnswerQuestions().add(readingComprehensionSingleAnswerQuestion);
            switch (readingComprehensionSingleAnswerQuestion.getDifficulty()) {
                case EASY:
                    difficultyWeight--;
                    break;
                case HARD:
                    difficultyWeight++;
            }
        }
        count = random.nextInt(3);
        for (int i=0; i< count; i++) {
            ReadingComprehensionMultipleAnswerQuestion readingComprehensionMultipleAnswerQuestion = new ReadingComprehensionMultipleAnswerQuestion();
            readingComprehensionMultipleAnswerQuestion.setText("This is a sample Reading Comprehension Multiple Answer question " + (i + 1));
            readingComprehensionMultipleAnswerQuestion.setDifficulty(Difficulty.values()[random.nextInt(3)]);
            createMultipleAnswerChoices(readingComprehensionMultipleAnswerQuestion.getChoices(), 3, 3, 1);
            readingComprehensionMultipleAnswerQuestion.setNumber(n++);
            question.getMultipleAnswerQuestions().add(readingComprehensionMultipleAnswerQuestion);
            switch (readingComprehensionMultipleAnswerQuestion.getDifficulty()) {
                case EASY:
                    difficultyWeight--;
                    break;
                case HARD:
                    difficultyWeight++;
            }
        }
        count = random.nextInt(2);
        for (int i=0; i< count; i++) {
            SelectInPassageQuestion selectInPassageQuestion = new SelectInPassageQuestion();
            selectInPassageQuestion.setText("<p><u>This</u> is a <u>sample</u> <u>Select</u> In <u>Passage Question</u>.</p>");
            selectInPassageQuestion.setNumber(n++);
            selectInPassageQuestion.setAnswer(random.nextInt(4));
            question.getSelectInPassageQuestions().add(selectInPassageQuestion);
            switch (selectInPassageQuestion.getDifficulty()) {
                case EASY:
                    difficultyWeight--;
                    break;
                case HARD:
                    difficultyWeight++;
            }
        }
        if (difficultyWeight < 0)
            question.setDifficulty(Difficulty.EASY);
        else if (difficultyWeight > 0)
            question.setDifficulty(Difficulty.HARD);
        else
            question.setDifficulty(Difficulty.MEDIUM);
        question.prepare();
        questionRepository.save(question);
    }

    public void generateAll(int count) {
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
