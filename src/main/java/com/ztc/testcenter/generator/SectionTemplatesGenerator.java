package com.ztc.testcenter.generator;

import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.question.DifficultyLevel;
import com.ztc.testcenter.domain.question.QuestionType;
import com.ztc.testcenter.domain.question.QuestionTemplate;
import com.ztc.testcenter.domain.test.SectionTemplate;
import com.ztc.testcenter.domain.test.SectionTemplateItem;
import com.ztc.testcenter.domain.test.SectionType;
import com.ztc.testcenter.repository.question.QuestionTemplateRepository;
import com.ztc.testcenter.repository.test.SectionTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yubar on 2/28/17.
 */

@Service
public class SectionTemplatesGenerator {

    private final SectionTemplateRepository repository;
    private final QuestionTemplateRepository questionTemplateRepository;

    @Autowired
    public SectionTemplatesGenerator(SectionTemplateRepository repository, QuestionTemplateRepository questionTemplateRepository) {
        this.repository = repository;
        this.questionTemplateRepository = questionTemplateRepository;
    }

    private void fillSectionTemplateItemsForMediumVerbal(SectionTemplate sectionTemplate) {
        sectionTemplate.getItems().add(new SectionTemplateItem(1, QuestionType.GRE_TEXT_COMPLETION_ONE_BLANK, DifficultyLevel.LEVEL1));
        sectionTemplate.getItems().add(new SectionTemplateItem(2, QuestionType.GRE_TEXT_COMPLETION_ONE_BLANK, DifficultyLevel.LEVEL2));
        sectionTemplate.getItems().add(new SectionTemplateItem(3, QuestionType.GRE_TEXT_COMPLETION_TWO_BLANK, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(4, QuestionType.GRE_TEXT_COMPLETION_TWO_BLANK, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(5, QuestionType.GRE_TEXT_COMPLETION_THREE_BLANK, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(6, QuestionType.GRE_TEXT_COMPLETION_THREE_BLANK, DifficultyLevel.LEVEL4));
        QuestionTemplate questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_MEDIUM-MEDIUM-1L2-1L3-1L4-1L5");
        sectionTemplate.getItems().add(new SectionTemplateItem(7, QuestionType.GRE_READING_COMPREHENSION_MEDIUM, DifficultyLevel.LEVEL3, questionTemplate));
        questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT-MEDIUM-1L3");
        sectionTemplate.getItems().add(new SectionTemplateItem(11, QuestionType.GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT, DifficultyLevel.LEVEL3, questionTemplate));
        questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_SHORT-MEDIUM-1L3-1L5");
        sectionTemplate.getItems().add(new SectionTemplateItem(12, QuestionType.GRE_READING_COMPREHENSION_SHORT, DifficultyLevel.LEVEL4, questionTemplate));
        sectionTemplate.getItems().add(new SectionTemplateItem(14, QuestionType.GRE_SENTENCE_EQUIVALENCE, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(15, QuestionType.GRE_SENTENCE_EQUIVALENCE, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(16, QuestionType.GRE_SENTENCE_EQUIVALENCE, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(17, QuestionType.GRE_SENTENCE_EQUIVALENCE, DifficultyLevel.LEVEL5));
        questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_SHORT-MEDIUM-1L3-1L5");
        sectionTemplate.getItems().add(new SectionTemplateItem(18, QuestionType.GRE_READING_COMPREHENSION_SHORT, DifficultyLevel.LEVEL4, questionTemplate));
        questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT-MEDIUM-1L3");
        sectionTemplate.getItems().add(new SectionTemplateItem(20, QuestionType.GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT, DifficultyLevel.LEVEL3, questionTemplate));
    }

    private void fillSectionTemplateItemsForMediumQuantitative(SectionTemplate sectionTemplate) {
        sectionTemplate.getItems().add(new SectionTemplateItem(1, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL1));
        sectionTemplate.getItems().add(new SectionTemplateItem(2, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL2));
        sectionTemplate.getItems().add(new SectionTemplateItem(3, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(4, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(5, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(6, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(7, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL5));
        sectionTemplate.getItems().add(new SectionTemplateItem(8, QuestionType.GRE_NUMERIC, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(9, QuestionType.GRE_QUANTITATIVE_SINGLE_ANSWER, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(10, QuestionType.GRE_QUANTITATIVE_SINGLE_ANSWER, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(11, QuestionType.GRE_QUANTITATIVE_MULTIPLE_ANSWER, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(12, QuestionType.GRE_QUANTITATIVE_MULTIPLE_ANSWER, DifficultyLevel.LEVEL3));
        QuestionTemplate questionTemplate = questionTemplateRepository.findByLabel("GRE_DATA_INTERPRETATION_SET-MEDIUM-2L2-1L3-1L4");
        sectionTemplate.getItems().add(new SectionTemplateItem(13, QuestionType.GRE_DATA_INTERPRETATION_SET, DifficultyLevel.LEVEL3, questionTemplate));
        sectionTemplate.getItems().add(new SectionTemplateItem(17, QuestionType.GRE_NUMERIC_FRACTION, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(18, QuestionType.GRE_QUANTITATIVE_SINGLE_ANSWER, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(19, QuestionType.GRE_QUANTITATIVE_SINGLE_ANSWER, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(20, QuestionType.GRE_QUANTITATIVE_MULTIPLE_ANSWER, DifficultyLevel.LEVEL3));
    }

    private void fillSectionTemplateItemsForEasyVerbal(SectionTemplate sectionTemplate) {
        sectionTemplate.getItems().add(new SectionTemplateItem(1, QuestionType.GRE_TEXT_COMPLETION_ONE_BLANK, DifficultyLevel.LEVEL1));
        sectionTemplate.getItems().add(new SectionTemplateItem(2, QuestionType.GRE_TEXT_COMPLETION_ONE_BLANK, DifficultyLevel.LEVEL2));
        sectionTemplate.getItems().add(new SectionTemplateItem(3, QuestionType.GRE_TEXT_COMPLETION_TWO_BLANK, DifficultyLevel.LEVEL2));
        sectionTemplate.getItems().add(new SectionTemplateItem(4, QuestionType.GRE_TEXT_COMPLETION_TWO_BLANK, DifficultyLevel.LEVEL2));
        sectionTemplate.getItems().add(new SectionTemplateItem(5, QuestionType.GRE_TEXT_COMPLETION_THREE_BLANK, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(6, QuestionType.GRE_TEXT_COMPLETION_THREE_BLANK, DifficultyLevel.LEVEL3));
        QuestionTemplate questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_MEDIUM-EASY-2L2-1L3-1L4");
        sectionTemplate.getItems().add(new SectionTemplateItem(7, QuestionType.GRE_READING_COMPREHENSION_MEDIUM, DifficultyLevel.LEVEL3, questionTemplate));
        questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT-EASY-1L3");
        sectionTemplate.getItems().add(new SectionTemplateItem(11, QuestionType.GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT, DifficultyLevel.LEVEL3, questionTemplate));
        questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_LONG-EASY-1L2-1L3");
        sectionTemplate.getItems().add(new SectionTemplateItem(12, QuestionType.GRE_READING_COMPREHENSION_LONG, DifficultyLevel.LEVEL2, questionTemplate));
        sectionTemplate.getItems().add(new SectionTemplateItem(14, QuestionType.GRE_SENTENCE_EQUIVALENCE, DifficultyLevel.LEVEL1));
        sectionTemplate.getItems().add(new SectionTemplateItem(15, QuestionType.GRE_SENTENCE_EQUIVALENCE, DifficultyLevel.LEVEL2));
        sectionTemplate.getItems().add(new SectionTemplateItem(16, QuestionType.GRE_SENTENCE_EQUIVALENCE, DifficultyLevel.LEVEL2));
        sectionTemplate.getItems().add(new SectionTemplateItem(17, QuestionType.GRE_SENTENCE_EQUIVALENCE, DifficultyLevel.LEVEL3));
        questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_SHORT-EASY-1L3-1L5");
        sectionTemplate.getItems().add(new SectionTemplateItem(18, QuestionType.GRE_READING_COMPREHENSION_SHORT, DifficultyLevel.LEVEL4, questionTemplate));
        questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT-EASY-1L2");
        sectionTemplate.getItems().add(new SectionTemplateItem(20, QuestionType.GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT, DifficultyLevel.LEVEL2, questionTemplate));
    }

    private void fillSectionTemplateItemsForEasyQuantitative(SectionTemplate sectionTemplate) {
        sectionTemplate.getItems().add(new SectionTemplateItem(1, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL1));
        sectionTemplate.getItems().add(new SectionTemplateItem(2, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL2));
        sectionTemplate.getItems().add(new SectionTemplateItem(3, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL2));
        sectionTemplate.getItems().add(new SectionTemplateItem(4, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL2));
        sectionTemplate.getItems().add(new SectionTemplateItem(5, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(6, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(7, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(8, QuestionType.GRE_NUMERIC, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(9, QuestionType.GRE_QUANTITATIVE_SINGLE_ANSWER, DifficultyLevel.LEVEL2));
        sectionTemplate.getItems().add(new SectionTemplateItem(10, QuestionType.GRE_QUANTITATIVE_SINGLE_ANSWER, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(11, QuestionType.GRE_QUANTITATIVE_MULTIPLE_ANSWER, DifficultyLevel.LEVEL1));
        sectionTemplate.getItems().add(new SectionTemplateItem(12, QuestionType.GRE_QUANTITATIVE_MULTIPLE_ANSWER, DifficultyLevel.LEVEL4));
        QuestionTemplate questionTemplate = questionTemplateRepository.findByLabel("GRE_DATA_INTERPRETATION_SET-EASY-1L1-2L2-1L4");
        sectionTemplate.getItems().add(new SectionTemplateItem(13, QuestionType.GRE_DATA_INTERPRETATION_SET, DifficultyLevel.LEVEL2, questionTemplate));
        sectionTemplate.getItems().add(new SectionTemplateItem(17, QuestionType.GRE_NUMERIC_FRACTION, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(18, QuestionType.GRE_QUANTITATIVE_SINGLE_ANSWER, DifficultyLevel.LEVEL1));
        sectionTemplate.getItems().add(new SectionTemplateItem(19, QuestionType.GRE_QUANTITATIVE_SINGLE_ANSWER, DifficultyLevel.LEVEL2));
        sectionTemplate.getItems().add(new SectionTemplateItem(20, QuestionType.GRE_QUANTITATIVE_MULTIPLE_ANSWER, DifficultyLevel.LEVEL3));
    }

    private void fillSectionTemplateItemsForHardVerbal(SectionTemplate sectionTemplate) {
        sectionTemplate.getItems().add(new SectionTemplateItem(1, QuestionType.GRE_TEXT_COMPLETION_ONE_BLANK, DifficultyLevel.LEVEL1));
        sectionTemplate.getItems().add(new SectionTemplateItem(2, QuestionType.GRE_TEXT_COMPLETION_ONE_BLANK, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(3, QuestionType.GRE_TEXT_COMPLETION_TWO_BLANK, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(4, QuestionType.GRE_TEXT_COMPLETION_TWO_BLANK, DifficultyLevel.LEVEL5));
        sectionTemplate.getItems().add(new SectionTemplateItem(5, QuestionType.GRE_TEXT_COMPLETION_THREE_BLANK, DifficultyLevel.LEVEL5));
        sectionTemplate.getItems().add(new SectionTemplateItem(6, QuestionType.GRE_TEXT_COMPLETION_THREE_BLANK, DifficultyLevel.LEVEL5));
        QuestionTemplate questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_MEDIUM-HARD-1L2-2L4-1L5");
        sectionTemplate.getItems().add(new SectionTemplateItem(7, QuestionType.GRE_READING_COMPREHENSION_MEDIUM, DifficultyLevel.LEVEL4, questionTemplate));
        questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT-HARD-1L5");
        sectionTemplate.getItems().add(new SectionTemplateItem(11, QuestionType.GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT, DifficultyLevel.LEVEL5, questionTemplate));
        questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_SHORT-HARD-1L4-1L5");
        sectionTemplate.getItems().add(new SectionTemplateItem(12, QuestionType.GRE_READING_COMPREHENSION_SHORT, DifficultyLevel.LEVEL4, questionTemplate));
        sectionTemplate.getItems().add(new SectionTemplateItem(14, QuestionType.GRE_SENTENCE_EQUIVALENCE, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(15, QuestionType.GRE_SENTENCE_EQUIVALENCE, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(16, QuestionType.GRE_SENTENCE_EQUIVALENCE, DifficultyLevel.LEVEL5));
        sectionTemplate.getItems().add(new SectionTemplateItem(17, QuestionType.GRE_SENTENCE_EQUIVALENCE, DifficultyLevel.LEVEL5));
        questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_SHORT-HARD-2L5");
        sectionTemplate.getItems().add(new SectionTemplateItem(18, QuestionType.GRE_READING_COMPREHENSION_SHORT, DifficultyLevel.LEVEL5, questionTemplate));
        questionTemplate = questionTemplateRepository.findByLabel("GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT-HARD-1L5");
        sectionTemplate.getItems().add(new SectionTemplateItem(20, QuestionType.GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT, DifficultyLevel.LEVEL5, questionTemplate));
    }

    private void fillSectionTemplateItemsForHardQuantitative(SectionTemplate sectionTemplate) {
        sectionTemplate.getItems().add(new SectionTemplateItem(1, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(2, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(3, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(4, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(5, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(6, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(7, QuestionType.GRE_QUANTITATIVE_COMPARISON, DifficultyLevel.LEVEL5));
        sectionTemplate.getItems().add(new SectionTemplateItem(8, QuestionType.GRE_NUMERIC, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(9, QuestionType.GRE_QUANTITATIVE_SINGLE_ANSWER, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(10, QuestionType.GRE_QUANTITATIVE_SINGLE_ANSWER, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(11, QuestionType.GRE_QUANTITATIVE_MULTIPLE_ANSWER, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(12, QuestionType.GRE_QUANTITATIVE_MULTIPLE_ANSWER, DifficultyLevel.LEVEL5));
        QuestionTemplate questionTemplate = questionTemplateRepository.findByLabel("GRE_DATA_INTERPRETATION_SET-HARD-1L3-1L4-2L5");
        sectionTemplate.getItems().add(new SectionTemplateItem(13, QuestionType.GRE_DATA_INTERPRETATION_SET, DifficultyLevel.LEVEL5, questionTemplate));
        sectionTemplate.getItems().add(new SectionTemplateItem(17, QuestionType.GRE_NUMERIC_FRACTION, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(18, QuestionType.GRE_QUANTITATIVE_SINGLE_ANSWER, DifficultyLevel.LEVEL3));
        sectionTemplate.getItems().add(new SectionTemplateItem(19, QuestionType.GRE_QUANTITATIVE_SINGLE_ANSWER, DifficultyLevel.LEVEL4));
        sectionTemplate.getItems().add(new SectionTemplateItem(20, QuestionType.GRE_QUANTITATIVE_MULTIPLE_ANSWER, DifficultyLevel.LEVEL3));
    }

    private void createSectionTemplate(SectionType sectionType, Difficulty difficulty, Boolean free){
        SectionTemplate sectionTemplate = new SectionTemplate(sectionType, difficulty);
        sectionTemplate.setFree(free);
        switch (sectionType) {
            case GRE_VERBAL_REASONING_1:
            case GRE_VERBAL_REASONING_2:
            case GRE_VERBAL_UNSCORE:
                switch (difficulty) {
                    case EASY:
                        fillSectionTemplateItemsForEasyVerbal(sectionTemplate);
                        break;
                    case MEDIUM:
                        fillSectionTemplateItemsForMediumVerbal(sectionTemplate);
                        break;
                    case HARD:
                        fillSectionTemplateItemsForHardVerbal(sectionTemplate);
                        break;
                }
                break;
            case GRE_QUANTITATIVE_REASONING_1:
            case GRE_QUANTITATIVE_REASONING_2:
            case GRE_QUANTITATIVE_UNSCORE:
                switch (difficulty) {
                    case EASY:
                        fillSectionTemplateItemsForEasyQuantitative(sectionTemplate);
                        break;
                    case MEDIUM:
                        fillSectionTemplateItemsForMediumQuantitative(sectionTemplate);
                        break;
                    case HARD:
                        fillSectionTemplateItemsForHardQuantitative(sectionTemplate);
                        break;
                }
                break;
            case GRE_ANALYTICAL_WRITING_ARGUMENT:
                sectionTemplate.getItems().add(new SectionTemplateItem(1, QuestionType.GRE_WRITING_ARGUMENT, DifficultyLevel.LEVEL3));
                break;
            case GRE_ANALYTICAL_WRITING_ISSUE:
                sectionTemplate.getItems().add(new SectionTemplateItem(1, QuestionType.GRE_WRITING_ISSUE, DifficultyLevel.LEVEL3));
                break;
        }
        repository.save(sectionTemplate);
    }

    private void createSectionTemplate(SectionType sectionType, Difficulty difficulty) {
        createSectionTemplate(sectionType, difficulty, false);
    }

    public void createFreeSectionTemplates() {
        createSectionTemplate(SectionType.GRE_VERBAL_REASONING_1, Difficulty.EASY, true);
        createSectionTemplate(SectionType.GRE_VERBAL_REASONING_1, Difficulty.MEDIUM, true);
        createSectionTemplate(SectionType.GRE_VERBAL_REASONING_1, Difficulty.HARD, true);

        createSectionTemplate(SectionType.GRE_VERBAL_UNSCORE, Difficulty.EASY, true);
        createSectionTemplate(SectionType.GRE_VERBAL_UNSCORE, Difficulty.MEDIUM, true);
        createSectionTemplate(SectionType.GRE_VERBAL_UNSCORE, Difficulty.HARD, true);

        createSectionTemplate(SectionType.GRE_QUANTITATIVE_REASONING_1, Difficulty.EASY, true);
        createSectionTemplate(SectionType.GRE_QUANTITATIVE_REASONING_1, Difficulty.MEDIUM, true);
        createSectionTemplate(SectionType.GRE_QUANTITATIVE_REASONING_1, Difficulty.HARD, true);

        createSectionTemplate(SectionType.GRE_QUANTITATIVE_UNSCORE, Difficulty.EASY, true);
        createSectionTemplate(SectionType.GRE_QUANTITATIVE_UNSCORE, Difficulty.MEDIUM, true);
        createSectionTemplate(SectionType.GRE_QUANTITATIVE_UNSCORE, Difficulty.HARD, true);

        createSectionTemplate(SectionType.GRE_ANALYTICAL_WRITING_ISSUE, Difficulty.MEDIUM, true);
        createSectionTemplate(SectionType.GRE_ANALYTICAL_WRITING_ARGUMENT, Difficulty.MEDIUM, true);

        createSectionTemplate(SectionType.GRE_VERBAL_REASONING_2, Difficulty.EASY, true);
        createSectionTemplate(SectionType.GRE_VERBAL_REASONING_2, Difficulty.MEDIUM, true);
        createSectionTemplate(SectionType.GRE_VERBAL_REASONING_2, Difficulty.HARD, true);

        createSectionTemplate(SectionType.GRE_QUANTITATIVE_REASONING_2, Difficulty.EASY, true);
        createSectionTemplate(SectionType.GRE_QUANTITATIVE_REASONING_2, Difficulty.MEDIUM, true);
        createSectionTemplate(SectionType.GRE_QUANTITATIVE_REASONING_2, Difficulty.HARD, true);
    }

    public void createNotFreeSectionTemplates() {
        createSectionTemplate(SectionType.GRE_VERBAL_REASONING_1, Difficulty.EASY);
        createSectionTemplate(SectionType.GRE_VERBAL_REASONING_1, Difficulty.MEDIUM);
        createSectionTemplate(SectionType.GRE_VERBAL_REASONING_1, Difficulty.HARD);

        createSectionTemplate(SectionType.GRE_VERBAL_UNSCORE, Difficulty.EASY);
        createSectionTemplate(SectionType.GRE_VERBAL_UNSCORE, Difficulty.MEDIUM);
        createSectionTemplate(SectionType.GRE_VERBAL_UNSCORE, Difficulty.HARD);

        createSectionTemplate(SectionType.GRE_QUANTITATIVE_REASONING_1, Difficulty.EASY);
        createSectionTemplate(SectionType.GRE_QUANTITATIVE_REASONING_1, Difficulty.MEDIUM);
        createSectionTemplate(SectionType.GRE_QUANTITATIVE_REASONING_1, Difficulty.HARD);

        createSectionTemplate(SectionType.GRE_QUANTITATIVE_UNSCORE, Difficulty.EASY);
        createSectionTemplate(SectionType.GRE_QUANTITATIVE_UNSCORE, Difficulty.MEDIUM);
        createSectionTemplate(SectionType.GRE_QUANTITATIVE_UNSCORE, Difficulty.HARD);

        createSectionTemplate(SectionType.GRE_ANALYTICAL_WRITING_ISSUE, Difficulty.MEDIUM);
        createSectionTemplate(SectionType.GRE_ANALYTICAL_WRITING_ARGUMENT, Difficulty.MEDIUM);

        createSectionTemplate(SectionType.GRE_VERBAL_REASONING_2, Difficulty.EASY);
        createSectionTemplate(SectionType.GRE_VERBAL_REASONING_2, Difficulty.MEDIUM);
        createSectionTemplate(SectionType.GRE_VERBAL_REASONING_2, Difficulty.HARD);

        createSectionTemplate(SectionType.GRE_QUANTITATIVE_REASONING_2, Difficulty.EASY);
        createSectionTemplate(SectionType.GRE_QUANTITATIVE_REASONING_2, Difficulty.MEDIUM);
        createSectionTemplate(SectionType.GRE_QUANTITATIVE_REASONING_2, Difficulty.HARD);
    }

    public void createAll() {
        createNotFreeSectionTemplates();
        createFreeSectionTemplates();
    }

}
