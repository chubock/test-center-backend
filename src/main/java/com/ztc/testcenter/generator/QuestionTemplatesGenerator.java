package com.ztc.testcenter.generator;

import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.question.DifficultyLevel;
import com.ztc.testcenter.domain.question.QuestionType;
import com.ztc.testcenter.domain.question.QuestionTemplate;
import com.ztc.testcenter.domain.question.QuestionTemplateItem;
import com.ztc.testcenter.repository.test.QuestionTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yubar on 2/28/17.
 */

@Service
public class QuestionTemplatesGenerator {

    @Autowired
    QuestionTemplateRepository repository;

    @Transactional
    public void createMediumDataInterpretationTemplate1() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_DATA_INTERPRETATION_SET);
        template.setDifficulty(Difficulty.MEDIUM);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL2, 2));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL3, 1));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL4, 1));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createEasyDataInterpretationTemplate1() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_DATA_INTERPRETATION_SET);
        template.setDifficulty(Difficulty.EASY);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL1, 1));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL2, 2));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL4, 1));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createHardDataInterpretationTemplate1() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_DATA_INTERPRETATION_SET);
        template.setDifficulty(Difficulty.EASY);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL3, 1));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL4, 1));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL5, 2));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createMediumReadingComprehensionTemplate1() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_READING_COMPREHENSION);
        template.setDifficulty(Difficulty.MEDIUM);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL2, 1));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL3, 1));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL4, 1));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL5, 1));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createMediumReadingComprehensionTemplate2() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_READING_COMPREHENSION);
        template.setDifficulty(Difficulty.MEDIUM);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL3, 1));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createMediumReadingComprehensionTemplate3() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_READING_COMPREHENSION);
        template.setDifficulty(Difficulty.MEDIUM);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL3, 1));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL5, 1));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createEasyReadingComprehensionTemplate1() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_READING_COMPREHENSION);
        template.setDifficulty(Difficulty.EASY);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL2, 2));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL3, 1));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL4, 1));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createEasyReadingComprehensionTemplate2() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_READING_COMPREHENSION);
        template.setDifficulty(Difficulty.EASY);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL3, 1));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createEasyReadingComprehensionTemplate3() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_READING_COMPREHENSION);
        template.setDifficulty(Difficulty.EASY);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL2, 1));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL3, 1));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createEasyReadingComprehensionTemplate4() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_READING_COMPREHENSION);
        template.setDifficulty(Difficulty.EASY);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL3, 1));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL5, 1));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createEasyReadingComprehensionTemplate5() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_READING_COMPREHENSION);
        template.setDifficulty(Difficulty.EASY);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL2, 1));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createHardReadingComprehensionTemplate1() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_READING_COMPREHENSION);
        template.setDifficulty(Difficulty.HARD);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL2, 1));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL4, 2));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL5, 1));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createHardReadingComprehensionTemplate2() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_READING_COMPREHENSION);
        template.setDifficulty(Difficulty.HARD);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL5, 1));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createHardReadingComprehensionTemplate3() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_READING_COMPREHENSION);
        template.setDifficulty(Difficulty.HARD);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL4, 1));
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL5, 1));
        template.prepare();
        repository.save(template);
    }

    @Transactional
    public void createHardReadingComprehensionTemplate4() {
        QuestionTemplate template = new QuestionTemplate();
        template.setQuestionType(QuestionType.GRE_READING_COMPREHENSION);
        template.setDifficulty(Difficulty.HARD);
        template.getQuestionTemplateItems().add(new QuestionTemplateItem(DifficultyLevel.LEVEL5, 2));
        template.prepare();
        repository.save(template);
    }

    public void createAll() {
        createMediumDataInterpretationTemplate1();
        createEasyDataInterpretationTemplate1();
        createHardDataInterpretationTemplate1();
        createMediumReadingComprehensionTemplate1();
        createMediumReadingComprehensionTemplate2();
        createMediumReadingComprehensionTemplate3();
        createEasyReadingComprehensionTemplate1();
        createEasyReadingComprehensionTemplate2();
        createEasyReadingComprehensionTemplate3();
        createEasyReadingComprehensionTemplate4();
        createEasyReadingComprehensionTemplate5();
        createHardReadingComprehensionTemplate1();
        createHardReadingComprehensionTemplate2();
        createHardReadingComprehensionTemplate3();
        createHardReadingComprehensionTemplate4();
    }
}
