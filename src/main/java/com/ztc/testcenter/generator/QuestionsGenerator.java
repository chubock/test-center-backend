package com.ztc.testcenter.generator;

import com.ztc.testcenter.domain.question.*;
import com.ztc.testcenter.domain.question.QuestionTemplate;
import com.ztc.testcenter.domain.question.QuestionTemplateItem;
import com.ztc.testcenter.repository.FileRepository;
import com.ztc.testcenter.repository.question.QuestionRepository;
import com.ztc.testcenter.repository.question.QuestionTemplateRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Yubar on 2/24/2017.
 */

@Service
public class QuestionsGenerator {

    private final ManagerService managerService;
    private final Random random = new Random(1);
    private final FileRepository fileRepository;

    private List<QuestionTemplate> dataInterpretationTemplates;
    private List<QuestionTemplate> readingComprehensionTemplates;

    @Autowired
    public QuestionsGenerator(ManagerService managerService, QuestionTemplateRepository questionTemplateRepository, FileRepository fileRepository) {
        this.managerService = managerService;
        this.fileRepository = fileRepository;

        dataInterpretationTemplates = questionTemplateRepository.findByQuestionType(QuestionType.GRE_DATA_INTERPRETATION_SET);
        readingComprehensionTemplates = questionTemplateRepository.findByQuestionType(QuestionType.GRE_READING_COMPREHENSION_SHORT);
        readingComprehensionTemplates.addAll(questionTemplateRepository.findByQuestionType(QuestionType.GRE_READING_COMPREHENSION_MEDIUM));
        readingComprehensionTemplates.addAll(questionTemplateRepository.findByQuestionType(QuestionType.GRE_READING_COMPREHENSION_LONG));
        readingComprehensionTemplates.addAll(questionTemplateRepository.findByQuestionType(QuestionType.GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT));

    }

    private List<ReadingComprehensionQuestion> getReadingComprehensionQuestionSamples() {
        final List<ReadingComprehensionQuestion> readingComprehensionQuestionSamples = new ArrayList<>();

        ReadingComprehensionQuestion readingComprehensionQuestion = new ReadingComprehensionQuestion();
        readingComprehensionQuestion.setText("Policymakers must confront the dilemma that fossil fuels continue to be an indispensable source of energy even though burning them produces atmospheric accumulations of carbon dioxide that increase the likelihood of potentially disastrous global climate change. Currently, technology that would capture carbon dioxide emitted by power plants and sequester it harmlessly underground or undersea instead of releasing it into the atmosphere might double the cost of generating electricity. But because sequestration does not affect the cost of electricity transmission and distribution, delivered prices will rise less, by no more than 50 percent. Research into better technologies for capturing carbon dioxide will undoubtedly lead to lowered costs.");

        ReadingComprehensionSingleAnswerQuestion readingComprehensionSingleAnswerQuestion = new ReadingComprehensionSingleAnswerQuestion(readingComprehensionQuestion, 1);
        readingComprehensionSingleAnswerQuestion.setText("The passage implies which of the following about the current cost of generating electricity?");
        readingComprehensionSingleAnswerQuestion.getChoices().add(new Choice("It is higher than it would be if better technologies for capturing carbon dioxide were available."));
        readingComprehensionSingleAnswerQuestion.getChoices().add(new Choice("It is somewhat less than the cost of electricity transmission and distribution."));
        readingComprehensionSingleAnswerQuestion.getChoices().add(new Choice("It constitutes at most half of the delivered price of electricity."));
        readingComprehensionSingleAnswerQuestion.getChoices().add(new Choice("It is dwelt on by policymakers to the exclusion of other costs associated with electricity delivery."));
        readingComprehensionSingleAnswerQuestion.getChoices().add(new Choice("It is not fully recovered by the prices charged directly to electricity consumers."));
        readingComprehensionSingleAnswerQuestion.getChoices().get(2).setAnswer(true);
        readingComprehensionQuestion.getSingleAnswerQuestions().add(readingComprehensionSingleAnswerQuestion);

        ReadingComprehensionMultipleAnswerQuestion readingComprehensionMultipleAnswerQuestion = new ReadingComprehensionMultipleAnswerQuestion(readingComprehensionQuestion, 2);
        readingComprehensionMultipleAnswerQuestion.setText("The passage suggests that extensive use of sequestration would, over time, have which of the following consequences?");
        readingComprehensionMultipleAnswerQuestion.getChoices().add(new Choice("The burning of fossil fuels would eventually cease to produce atmospheric accumulations of carbon dioxide."));
        readingComprehensionMultipleAnswerQuestion.getChoices().add(new Choice("The proportion of the delivered price of electricity due to generation would rise and then decline."));
        readingComprehensionMultipleAnswerQuestion.getChoices().add(new Choice("Power plants would consume progressively lower quantities of fossil fuels."));
        readingComprehensionMultipleAnswerQuestion.getChoices().get(1).setAnswer(true);
        readingComprehensionQuestion.getMultipleAnswerQuestions().add(readingComprehensionMultipleAnswerQuestion);

        SelectInPassageQuestion selectInPassageQuestion = new SelectInPassageQuestion(readingComprehensionQuestion, 3);
        selectInPassageQuestion.setText("<p>Select the sentence that explains why an outcome of sequestration that might have been expected would not occur.</p><p><u>Policymakers must confront the dilemma that fossil fuels continue to be an indispensable source of energy even though burning them produces atmospheric accumulations of carbon dioxide that increase the likelihood of potentially disastrous global climate change./<u> <u>Currently, technology that would capture carbon dioxide emitted by power plants and sequester it harmlessly underground or undersea instead of releasing it into the atmosphere might double the cost of generating electricity.</u> <u>But because sequestration does not affect the cost of electricity transmission and distribution, delivered prices will rise less, by no more than 50 percent.</u> <u>Research into better technologies for capturing carbon dioxide will undoubtedly lead to lowered costs.</u></p>");
        selectInPassageQuestion.setAnswer(2);
        readingComprehensionQuestion.getSelectInPassageQuestions().add(selectInPassageQuestion);

        readingComprehensionQuestionSamples.add(readingComprehensionQuestion);

        readingComprehensionQuestion = new ReadingComprehensionQuestion();
        readingComprehensionQuestion.setText("Reviving the practice of using elements of popular music in classical composition, an approach that had been in hibernation in the United States during the 1960s, composer Philip Glass (born 1937) embraced the ethos of popular music in his compositions. Glass based two symphonies on music by rock musicians David Bowie and Brian Eno, but the symphonies' sound is distinctively his. Popular elements do not appear out of place in Glass's classical music, which from its early days has shared certain harmonies and rhythms with rock music. Yet this use of popular elements has not made Glass a composer of popular music. His music is not a version of popular music packaged to attract classical listeners; it is high art for listeners steeped in rock rather than the classics.");

        readingComprehensionSingleAnswerQuestion = new ReadingComprehensionSingleAnswerQuestion(readingComprehensionQuestion, 1);
        readingComprehensionSingleAnswerQuestion.setText("The passage addresses which of the following issues related to Glass's use of popular elements in his classical compositions?");
        readingComprehensionSingleAnswerQuestion.getChoices().add(new Choice("How it is regarded by listeners who prefer rock to the classics"));
        readingComprehensionSingleAnswerQuestion.getChoices().add(new Choice("How it has affected the commercial success of Glass's music"));
        readingComprehensionSingleAnswerQuestion.getChoices().add(new Choice("Whether it has contributed to a revival of interest among other composers in using popular elements in their compositions"));
        readingComprehensionSingleAnswerQuestion.getChoices().add(new Choice("Whether it has had a detrimental effect on Glass's reputation as a composer of classical music"));
        readingComprehensionSingleAnswerQuestion.getChoices().add(new Choice("Whether it has caused certain of Glass's works to be derivative in quality"));
        readingComprehensionSingleAnswerQuestion.getChoices().get(4).setAnswer(true);
        readingComprehensionQuestion.getSingleAnswerQuestions().add(readingComprehensionSingleAnswerQuestion);

        readingComprehensionMultipleAnswerQuestion = new ReadingComprehensionMultipleAnswerQuestion(readingComprehensionQuestion, 2);
        readingComprehensionMultipleAnswerQuestion.setText("The passage suggests that Glass's work displays which of the following qualities?");
        readingComprehensionMultipleAnswerQuestion.getChoices().add(new Choice("A return to the use of popular music in classical compositions"));
        readingComprehensionMultipleAnswerQuestion.getChoices().add(new Choice("An attempt to elevate rock music to an artistic status more closely approximating that of classical music"));
        readingComprehensionMultipleAnswerQuestion.getChoices().add(new Choice("A long-standing tendency to incorporate elements from two apparently disparate musical styles"));
        readingComprehensionMultipleAnswerQuestion.getChoices().get(0).setAnswer(true);
        readingComprehensionMultipleAnswerQuestion.getChoices().get(2).setAnswer(true);
        readingComprehensionQuestion.getMultipleAnswerQuestions().add(readingComprehensionMultipleAnswerQuestion);

        selectInPassageQuestion = new SelectInPassageQuestion(readingComprehensionQuestion, 3);
        selectInPassageQuestion.setText("<p>Select the sentence that distinguishes two ways of integrating rock and classical music.</p><p>Reviving the practice of using elements of popular music in classical composition, an approach that had been in hibernation in the United States during the 1960s, <u>composer Philip Glass (born 1937) embraced the ethos of popular music in his compositions.</u> <u>Glass based two symphonies on music by rock musicians David Bowie and Brian Eno, but the symphonies' sound is distinctively his.</u> <u>Popular elements do not appear out of place in Glass's classical music, which from its early days has shared certain harmonies and rhythms with rock music.</u> <u>Yet this use of popular elements has not made Glass a composer of popular music.</u> <u>His music is not a version of popular music packaged to attract classical listeners; it is high art for listeners steeped in rock rather than the classics.</u></p>");
        selectInPassageQuestion.setAnswer(4);
        readingComprehensionQuestion.getSelectInPassageQuestions().add(selectInPassageQuestion);

        readingComprehensionQuestionSamples.add(readingComprehensionQuestion);

        return readingComprehensionQuestionSamples;
    }

    private List<TextCompletionQuestion> getTextCompletionQuestionSamples() {
        final List<TextCompletionQuestion> textCompletionQuestionSamples = new ArrayList<>();

        TextCompletionQuestion textCompletionQuestion = new TextCompletionQuestion();
        textCompletionQuestion.setText("From the outset, the concept of freedom of the seas from the proprietary claims of nations was challenged by a contrary notion — that of the _______ of the oceans for reasons of national security and profit.");

        TextCompletionQuestionItem textCompletionQuestionItem = new TextCompletionQuestionItem();
        textCompletionQuestionItem.getChoices().add(new Choice("promotion"));
        textCompletionQuestionItem.getChoices().add(new Choice("exploration"));
        textCompletionQuestionItem.getChoices().add(new Choice("surveying"));
        textCompletionQuestionItem.getChoices().add(new Choice("conservation"));
        textCompletionQuestionItem.getChoices().add(new Choice("appropriation"));
        textCompletionQuestionItem.setAnswer(4);
        textCompletionQuestion.getItems().add(textCompletionQuestionItem);

        textCompletionQuestionSamples.add(textCompletionQuestion);

        textCompletionQuestion = new TextCompletionQuestion();
        textCompletionQuestion.setText("The author's (1)__________ style renders a fascinating subject, the role played by luck in everyday life, extraordinarily (2)__________.");

        textCompletionQuestionItem = new TextCompletionQuestionItem();
        textCompletionQuestionItem.getChoices().add(new Choice("soporific"));
        textCompletionQuestionItem.getChoices().add(new Choice("lucid"));
        textCompletionQuestionItem.getChoices().add(new Choice("colloquial"));
        textCompletionQuestionItem.setAnswer(0);
        textCompletionQuestion.getItems().add(textCompletionQuestionItem);

        textCompletionQuestionItem = new TextCompletionQuestionItem();
        textCompletionQuestionItem.getChoices().add(new Choice("pedantic"));
        textCompletionQuestionItem.getChoices().add(new Choice("tedious"));
        textCompletionQuestionItem.getChoices().add(new Choice("opaque"));
        textCompletionQuestionItem.setAnswer(1);
        textCompletionQuestion.getItems().add(textCompletionQuestionItem);

        textCompletionQuestionSamples.add(textCompletionQuestion);

        textCompletionQuestion = new TextCompletionQuestion();
        textCompletionQuestion.setText("Vain and prone to violence, Caravaggio could not handle success: the more his (1)__________ as an artist increased, the more (2)__________ his life became.");

        textCompletionQuestionItem = new TextCompletionQuestionItem();
        textCompletionQuestionItem.getChoices().add(new Choice("temperance"));
        textCompletionQuestionItem.getChoices().add(new Choice("notoriety"));
        textCompletionQuestionItem.getChoices().add(new Choice("eminence"));
        textCompletionQuestionItem.setAnswer(2);
        textCompletionQuestion.getItems().add(textCompletionQuestionItem);

        textCompletionQuestionItem = new TextCompletionQuestionItem();
        textCompletionQuestionItem.getChoices().add(new Choice("tumultuous"));
        textCompletionQuestionItem.getChoices().add(new Choice("providential"));
        textCompletionQuestionItem.getChoices().add(new Choice("dispassionate"));
        textCompletionQuestionItem.setAnswer(0);
        textCompletionQuestion.getItems().add(textCompletionQuestionItem);

        textCompletionQuestionSamples.add(textCompletionQuestion);

        textCompletionQuestion = new TextCompletionQuestion();
        textCompletionQuestion.setText("It is refreshing to read a book about our planet by an author who does not allow facts to be (1)__________ by politics: well aware of the political disputes about the effects of human activities on climate and biodiversity, this author does not permit them to (2)__________ his comprehensive description of what we know about our biosphere. He emphasizes the enormous gaps in our knowledge, the sparseness of our observations, and the (3)__________, calling attention to the many aspects of planetary evolution that must be better understood before we can accurately diagnose the condition of our planet.");

        textCompletionQuestionItem = new TextCompletionQuestionItem();
        textCompletionQuestionItem.getChoices().add(new Choice("overshadowed"));
        textCompletionQuestionItem.getChoices().add(new Choice("invalidated"));
        textCompletionQuestionItem.getChoices().add(new Choice("illuminated"));
        textCompletionQuestionItem.setAnswer(0);
        textCompletionQuestion.getItems().add(textCompletionQuestionItem);

        textCompletionQuestionItem = new TextCompletionQuestionItem();
        textCompletionQuestionItem.getChoices().add(new Choice("enhance"));
        textCompletionQuestionItem.getChoices().add(new Choice("obscure"));
        textCompletionQuestionItem.getChoices().add(new Choice("underscore"));
        textCompletionQuestionItem.setAnswer(1);
        textCompletionQuestion.getItems().add(textCompletionQuestionItem);

        textCompletionQuestionItem = new TextCompletionQuestionItem();
        textCompletionQuestionItem.getChoices().add(new Choice("plausibility of our hypotheses"));
        textCompletionQuestionItem.getChoices().add(new Choice("certainty of our entitlement"));
        textCompletionQuestionItem.getChoices().add(new Choice("superficiality of our theories"));
        textCompletionQuestionItem.setAnswer(2);
        textCompletionQuestion.getItems().add(textCompletionQuestionItem);

        textCompletionQuestionSamples.add(textCompletionQuestion);

        return textCompletionQuestionSamples;
    }

    private List<SentenceEquivalenceQuestion> getSentenceEquivalenceQuestionSamples() {
        final List<SentenceEquivalenceQuestion> sentenceEquivalenceQuestionSamples = new ArrayList<>();

        SentenceEquivalenceQuestion sentenceEquivalenceQuestion = new SentenceEquivalenceQuestion();
        sentenceEquivalenceQuestion.setText("Although it does contain some pioneering ideas, one would hardly characterize the work as __________.");
        sentenceEquivalenceQuestion.getChoices().add(new Choice("orthodox"));
        sentenceEquivalenceQuestion.getChoices().add(new Choice("eccentric"));
        sentenceEquivalenceQuestion.getChoices().add(new Choice("original"));
        sentenceEquivalenceQuestion.getChoices().add(new Choice("trifling"));
        sentenceEquivalenceQuestion.getChoices().add(new Choice("conventional"));
        sentenceEquivalenceQuestion.getChoices().add(new Choice("innovative"));
        sentenceEquivalenceQuestion.getChoices().get(2).setAnswer(true);
        sentenceEquivalenceQuestion.getChoices().get(5).setAnswer(true);

        sentenceEquivalenceQuestionSamples.add(sentenceEquivalenceQuestion);

        sentenceEquivalenceQuestion = new SentenceEquivalenceQuestion();
        sentenceEquivalenceQuestion.setText("The corporation expects only _______ increases in sales next year despite a yearlong effort to revive its retailing business.");
        sentenceEquivalenceQuestion.getChoices().add(new Choice("dynamic"));
        sentenceEquivalenceQuestion.getChoices().add(new Choice("predictable"));
        sentenceEquivalenceQuestion.getChoices().add(new Choice("expanding"));
        sentenceEquivalenceQuestion.getChoices().add(new Choice("modest"));
        sentenceEquivalenceQuestion.getChoices().add(new Choice("slight"));
        sentenceEquivalenceQuestion.getChoices().add(new Choice("volatile"));
        sentenceEquivalenceQuestion.getChoices().get(3).setAnswer(true);
        sentenceEquivalenceQuestion.getChoices().get(4).setAnswer(true);

        sentenceEquivalenceQuestionSamples.add(sentenceEquivalenceQuestion);

        return sentenceEquivalenceQuestionSamples;
    }

    private List<QuantitativeComparisonQuestion> getQuantitativeComparisonQuestionSamples() {
        final List<QuantitativeComparisonQuestion> quantitativeComparisonQuestionSamples = new ArrayList<>();

        QuantitativeComparisonQuestion quantitativeComparisonQuestion = new QuantitativeComparisonQuestion();
        quantitativeComparisonQuestion.setText("\\(x^2 + 1\\)");
        quantitativeComparisonQuestion.setQuantityB("\\(2x - 1\\)");
        quantitativeComparisonQuestion.setAnswer(0);

        quantitativeComparisonQuestionSamples.add(quantitativeComparisonQuestion);

        quantitativeComparisonQuestion = new QuantitativeComparisonQuestion();
        quantitativeComparisonQuestion.setText("\\(x\\)");
        quantitativeComparisonQuestion.setQuantityB("\\(y\\)");
        quantitativeComparisonQuestion.setImage(fileRepository.getOne((long) 1));
        quantitativeComparisonQuestion.setAnswer(3);

        quantitativeComparisonQuestionSamples.add(quantitativeComparisonQuestion);

        return quantitativeComparisonQuestionSamples;
    }

    private List<QuantitativeSingleAnswerQuestion> getQuantitativeSingleAnswerQuestionSamples() {
        final List<QuantitativeSingleAnswerQuestion> quantitativeSingleAnswerQuestionSamples = new ArrayList<>();

        QuantitativeSingleAnswerQuestion quantitativeSingleAnswerQuestion = new QuantitativeSingleAnswerQuestion();
        quantitativeSingleAnswerQuestion.setText("The figure above shows a circle with center C and radius 6. What is the sum of the areas of the two shaded regions?");
        quantitativeSingleAnswerQuestion.setImage(fileRepository.getOne((long) 2));
        quantitativeSingleAnswerQuestion.getChoices().add(new Choice("\\(7.5\\pi\\)"));
        quantitativeSingleAnswerQuestion.getChoices().add(new Choice("\\(6\\pi\\)"));
        quantitativeSingleAnswerQuestion.getChoices().add(new Choice("\\(4.5\\pi\\)"));
        quantitativeSingleAnswerQuestion.getChoices().add(new Choice("\\(4\\pi\\)"));
        quantitativeSingleAnswerQuestion.getChoices().add(new Choice("\\(3\\pi\\)"));
        quantitativeSingleAnswerQuestion.getChoices().get(3).setAnswer(true);

        quantitativeSingleAnswerQuestionSamples.add(quantitativeSingleAnswerQuestion);

        quantitativeSingleAnswerQuestion = new QuantitativeSingleAnswerQuestion();
        quantitativeSingleAnswerQuestion.setText("The figure above shows the graph of the function f defined by \\(f(x) = |2x| + 4\\) for all numbers \\(x\\). For which of the following functions \\(g\\), defined for all numbers \\(x\\), does the graph of \\(g\\) intersect the graph of \\(f\\)");
        quantitativeSingleAnswerQuestion.setImage(fileRepository.getOne((long) 3));
        quantitativeSingleAnswerQuestion.getChoices().add(new Choice("\\(g(x) = x - 2\\)"));
        quantitativeSingleAnswerQuestion.getChoices().add(new Choice("\\(g(x) = x + 3\\)"));
        quantitativeSingleAnswerQuestion.getChoices().add(new Choice("\\(g(x) = 2x - 2\\)"));
        quantitativeSingleAnswerQuestion.getChoices().add(new Choice("\\(g(x) = 2x + 3\\)"));
        quantitativeSingleAnswerQuestion.getChoices().add(new Choice("\\(g(x) = 3x - 2\\)"));
        quantitativeSingleAnswerQuestion.getChoices().get(4).setAnswer(true);

        quantitativeSingleAnswerQuestionSamples.add(quantitativeSingleAnswerQuestion);

        return quantitativeSingleAnswerQuestionSamples;
    }

    private List<QuantitativeMultipleAnswerQuestion> getQuantitativeMultipleAnswerQuestionSamples() {
        final List<QuantitativeMultipleAnswerQuestion> quantitativeMultipleAnswerQuestionSamples = new ArrayList<>();

        QuantitativeMultipleAnswerQuestion quantitativeMultipleAnswerQuestion = new QuantitativeMultipleAnswerQuestion();
        quantitativeMultipleAnswerQuestion.setText("<p>Each employee of a certain company is in either Department X or Department Y, and there are more than twice as many employees in Department X as in Department Y. The average (arithmetic mean) salary is $25,000 for the employees in Department X and $35,000 for the employees in Department Y. Which of the following amounts could be the average salary for all of the employees of the company?</p><p><br></p><p>Indicate <u>all</u> such amounts.</p>");
        quantitativeMultipleAnswerQuestion.getChoices().add(new Choice("$26,000"));
        quantitativeMultipleAnswerQuestion.getChoices().add(new Choice("$28,000"));
        quantitativeMultipleAnswerQuestion.getChoices().add(new Choice("$29,000"));
        quantitativeMultipleAnswerQuestion.getChoices().add(new Choice("$30,000"));
        quantitativeMultipleAnswerQuestion.getChoices().add(new Choice("$31,000"));
        quantitativeMultipleAnswerQuestion.getChoices().add(new Choice("$32,000"));
        quantitativeMultipleAnswerQuestion.getChoices().add(new Choice("$34,000"));
        quantitativeMultipleAnswerQuestion.getChoices().get(0).setAnswer(true);
        quantitativeMultipleAnswerQuestion.getChoices().get(1).setAnswer(true);

        quantitativeMultipleAnswerQuestionSamples.add(quantitativeMultipleAnswerQuestion);

        quantitativeMultipleAnswerQuestion = new QuantitativeMultipleAnswerQuestion();
        quantitativeMultipleAnswerQuestion.setText("<p>If \\(f\\), \\(g\\), and \\(h\\) are positive integers such that \\(f\\) is a factor of \\(g\\), and \\(g\\) is a factor of \\(h\\), which of the following statements must be true?</p><p><br></p><p>Indicate <u>all</u> such statements.</p>");
        quantitativeMultipleAnswerQuestion.getChoices().add(new Choice("\\(f\\) is a factor of \\(g^2\\)"));
        quantitativeMultipleAnswerQuestion.getChoices().add(new Choice("\\(f\\) is a factor of \\(gh\\)"));
        quantitativeMultipleAnswerQuestion.getChoices().add(new Choice("\\(f\\) is a factor of \\(h - g\\)"));
        quantitativeMultipleAnswerQuestion.getChoices().get(0).setAnswer(true);
        quantitativeMultipleAnswerQuestion.getChoices().get(1).setAnswer(true);
        quantitativeMultipleAnswerQuestion.getChoices().get(2).setAnswer(true);

        quantitativeMultipleAnswerQuestionSamples.add(quantitativeMultipleAnswerQuestion);

        return quantitativeMultipleAnswerQuestionSamples;
    }

    private List<NumericQuestion> getNumericQuestionSamples() {
        final List<NumericQuestion> numericQuestionSamples = new ArrayList<>();

        NumericQuestion numericQuestion = new NumericQuestion();
        numericQuestion.setText("if \\(x = 10^{-1}\\), what is the value of \\((x + \\frac1x)(\\frac1x)\\)");
        numericQuestion.setNominatorAnswer((double) 101);

        numericQuestionSamples.add(numericQuestion);

        numericQuestion = new NumericQuestion();
        numericQuestion.setText("<p>A university admitted 100 students who transferred from other institutions. Of these students, 34 transferred from two-year community colleges, 25 transferred from private four-year institutions, and the rest transferred from public four-year institutions. If two different students are to be selected at random from the 100 students, what is the probability that both students selected will be students who transferred from two-year community colleges?</p><p><br></p><p>Give your answer as a fraction.</p>");
        numericQuestion.setNominatorAnswer((double) 17);
        numericQuestion.setDenominatorAnswer((double) 150);
        numericQuestion.setFraction(true);

        numericQuestionSamples.add(numericQuestion);

        return numericQuestionSamples;
    }

    private List<DataInterpretationSetQuestion> getDataInterpretationSetQuestionSamples() {
        final List<DataInterpretationSetQuestion> dataInterpretationSetQuestionSamples = new ArrayList<>();

        DataInterpretationSetQuestion dataInterpretationSetQuestion = new DataInterpretationSetQuestion();
        dataInterpretationSetQuestion.setText("");
        dataInterpretationSetQuestion.setImage(fileRepository.getOne((long) 4));

        DataInterpretationSingleAnswerQuestion dataInterpretationSingleAnswerQuestion = new DataInterpretationSingleAnswerQuestion(dataInterpretationSetQuestion, 1);
        dataInterpretationSingleAnswerQuestion.setText("If the value of the inventory at Business K was $30,000 for April, what was the value of the inventory at Business K for June?");
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("$22,500"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("$29,925"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("$30,000"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("$33,000"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("$33,075"));
        dataInterpretationSingleAnswerQuestion.getChoices().get(1).setAnswer(true);
        dataInterpretationSetQuestion.getSingleAnswerQuestions().add(dataInterpretationSingleAnswerQuestion);

        DataInterpretationNumericQuestion dataInterpretationNumericQuestion = new DataInterpretationNumericQuestion(dataInterpretationSetQuestion, 2);
        dataInterpretationNumericQuestion.setText("<p>At Business M, the value of the inventory for May was what percent of the value of the inventory for June?</p><p><br></p><p>Give your answer to the <u>nearest 0.1 percent</u>.</p>");
        dataInterpretationNumericQuestion.setNominatorAnswer(89.3);
        dataInterpretationSetQuestion.getNumericQuestions().add(dataInterpretationNumericQuestion);

        dataInterpretationSingleAnswerQuestion = new DataInterpretationSingleAnswerQuestion(dataInterpretationSetQuestion, 3);
        dataInterpretationSingleAnswerQuestion.setText("For which of the six businesses shown was the percent change in the value of the inventory from April to June greatest?");
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("G"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("K"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("M"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("R"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("V"));
        dataInterpretationSingleAnswerQuestion.getChoices().get(0).setAnswer(true);
        dataInterpretationSetQuestion.getSingleAnswerQuestions().add(dataInterpretationSingleAnswerQuestion);

        dataInterpretationNumericQuestion = new DataInterpretationNumericQuestion(dataInterpretationSetQuestion, 2);
        dataInterpretationNumericQuestion.setText("At Business M, the value of the inventory for May was what percent of the value of the inventory for June? (Give your answer to the nearest 0.1 percent.)");
        dataInterpretationNumericQuestion.setNominatorAnswer(89.3);
        dataInterpretationSetQuestion.getNumericQuestions().add(dataInterpretationNumericQuestion);

        dataInterpretationSetQuestionSamples.add(dataInterpretationSetQuestion);

        dataInterpretationSetQuestion = new DataInterpretationSetQuestion();
        dataInterpretationSetQuestion.setText("");
        dataInterpretationSetQuestion.setImage(fileRepository.getOne((long) 5));

        dataInterpretationSingleAnswerQuestion = new DataInterpretationSingleAnswerQuestion(dataInterpretationSetQuestion, 1);
        dataInterpretationSingleAnswerQuestion.setText("Approximately how many people are in the production and transportation sector of the workforce?");
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("9 million"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("12 million"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("15 million"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("18 million"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("21 million"));
        dataInterpretationSingleAnswerQuestion.getChoices().get(4).setAnswer(true);
        dataInterpretationSetQuestion.getSingleAnswerQuestions().add(dataInterpretationSingleAnswerQuestion);

        dataInterpretationSingleAnswerQuestion = new DataInterpretationSingleAnswerQuestion(dataInterpretationSetQuestion, 3);
        dataInterpretationSingleAnswerQuestion.setText("Approximately what fraction of the workforce in the food service area of the service sector consists of males?");
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("\\(\frac14\\)"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("\\(\frac13\\)"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("\\(\frac37\\)"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("\\(\frac47\\)"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("\\(\frac7{10}\\)"));
        dataInterpretationSingleAnswerQuestion.getChoices().get(2).setAnswer(true);
        dataInterpretationSetQuestion.getSingleAnswerQuestions().add(dataInterpretationSingleAnswerQuestion);

        dataInterpretationSingleAnswerQuestion = new DataInterpretationSingleAnswerQuestion(dataInterpretationSetQuestion, 1);
        dataInterpretationSingleAnswerQuestion.setText("In the workforce, the ratio of the number of males to the number of females is the same for the sales sector as it is for the protective service area of the service sector. Which of the following is closest to the number of females in the sales sector?");
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("2.9 million"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("3.6 million"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("10.4 million"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("11.1 million"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("14.0 million"));
        dataInterpretationSingleAnswerQuestion.getChoices().get(0).setAnswer(true);
        dataInterpretationSetQuestion.getSingleAnswerQuestions().add(dataInterpretationSingleAnswerQuestion);

        dataInterpretationSingleAnswerQuestion = new DataInterpretationSingleAnswerQuestion(dataInterpretationSetQuestion, 3);
        dataInterpretationSingleAnswerQuestion.setText("Approximately what fraction of the workforce in the food service area of the service sector consists of males?");
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("\\(\frac14\\)"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("\\(\frac13\\)"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("\\(\frac37\\)"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("\\(\frac47\\)"));
        dataInterpretationSingleAnswerQuestion.getChoices().add(new Choice("\\(\frac7{10}\\)"));
        dataInterpretationSingleAnswerQuestion.getChoices().get(2).setAnswer(true);
        dataInterpretationSetQuestion.getSingleAnswerQuestions().add(dataInterpretationSingleAnswerQuestion);

        dataInterpretationSetQuestionSamples.add(dataInterpretationSetQuestion);

        return dataInterpretationSetQuestionSamples;
    }

    private List<WritingQuestion> getWritingQuestionSamples() {
        final List<WritingQuestion> writingQuestionSamples = new ArrayList<>();

        WritingQuestion writingQuestion = new WritingQuestion();
        writingQuestion.setText("<p><strong>As people rely more and more on technology to solve problems, the ability of humans to think for themselves will surely deteriorate.</strong></p><p><br></p><p><em>Write a response in which you discuss the extent to which you agree or disagree with the statement and explain your reasoning for the position you take. In developing and supporting your position, you should consider ways in which the statement might or might not hold true and explain how these considerations shape your position.</em></p>");
        writingQuestion.setType(WritingQuestion.Type.ANALYZE_AND_ISSUE);

        writingQuestionSamples.add(writingQuestion);

        writingQuestion = new WritingQuestion();
        writingQuestion.setText("<p><strong>A nation should require all of its students to study the same national curriculum until they enter college.</strong></p><p><br></p><p><em>Write a response in which you discuss the extent to which you agree or disagree with the recommendation and explain your reasoning for the position you take. In developing and supporting your position, describe specific circumstances in which adopting the recommendation would or would not be advantageous and explain how these examples shape your position.</em></p>");
        writingQuestion.setType(WritingQuestion.Type.ANALYZE_AND_ISSUE);

        writingQuestionSamples.add(writingQuestion);

        writingQuestion = new WritingQuestion();
        writingQuestion.setText("<p><strong>In surveys Mason City residents rank water sports (swimming, boating, and fishing) among their favorite recreational activities. The Mason River flowing through the city is rarely used for these pursuits, however, and the city park department devotes little of its budget to maintaining riverside recreational facilities. For years there have been complaints from residents about the quality of the river's water and the river's smell. In response, the state has recently announced plans to clean up Mason River. Use of the river for water sports is, therefore, sure to increase. The city government should for that reason devote more money in this year's budget to riverside recreational facilities. </strong></p><p><br></p><p><em>Write a response in which you examine the stated and/or unstated assumptions of the argument. Be sure to explain how the argument depends on the assumptions and what the implications are if the assumptions prove unwarranted.</em></p>");
        writingQuestion.setType(WritingQuestion.Type.ANALYZE_AND_ARGUMENT);

        writingQuestionSamples.add(writingQuestion);

        writingQuestion = new WritingQuestion();
        writingQuestion.setText("<p>The following is a memorandum from the business manager of a television station. </p><p><br></p><p><strong>\"Over the past year, our late-night news program has devoted increased time to national news and less time to weather and local news. During this time period, most of the complaints received from viewers were concerned with our station's coverage of weather and local news. In addition, local businesses that used to advertise during our late-night news program have just canceled their advertising contracts with us. Therefore, in order to attract more viewers to the program and to avoid losing any further advertising revenues, we should restore the time devoted to weather and local news to its former level.\" </strong></p><p><br></p><p><em>Write a response in which you discuss what specific evidence is needed to evaluate the argument and explain how the evidence would weaken or strengthen the argument.</em></p>");
        writingQuestion.setType(WritingQuestion.Type.ANALYZE_AND_ARGUMENT);

        writingQuestionSamples.add(writingQuestion);

        return writingQuestionSamples;
    }

    private void prepareQuestion(Question question, Integer number) {
        question.setText(question.getText() + " " + number);
        question.setDifficulty(Difficulty.values()[random.nextInt(3)]);
        question.setDifficultyLevel(DifficultyLevel.values()[random.nextInt(5)]);
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
        question.setNominatorAnswer((double) (random.nextInt(99) + 1));
        question.setFraction(random.nextBoolean());
        if (question.isFraction())
            question.setDenominatorAnswer((double) (random.nextInt(99) + 1));
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
        NumericQuestion question = getNumericQuestionSamples().get(random.nextInt(getNumericQuestionSamples().size()));
        prepareQuestion(question, number);
        managerService.save(question);
    }

    @Transactional
    public void createQuantitativeComparisonQuestion(Integer number) {
        QuantitativeComparisonQuestion question = getQuantitativeComparisonQuestionSamples().get(random.nextInt(getQuantitativeComparisonQuestionSamples().size()));
        prepareQuestion(question, number);
        managerService.save(question);
    }

    @Transactional
    public void createQuantitativeMultipleAnswerQuestion(Integer number) {
        QuantitativeMultipleAnswerQuestion question = getQuantitativeMultipleAnswerQuestionSamples().get(random.nextInt(getQuantitativeMultipleAnswerQuestionSamples().size()));
        prepareQuestion(question, number);
        managerService.save(question);
    }

    @Transactional
    public void createQuantitativeSingleAnswerQuestion(Integer number) {
        QuantitativeSingleAnswerQuestion question = getQuantitativeSingleAnswerQuestionSamples().get(random.nextInt(getQuantitativeSingleAnswerQuestionSamples().size()));
        prepareQuestion(question, number);
        managerService.save(question);
    }

    @Transactional
    public void createSentenceEquivalenceQuestion(Integer number) {
        SentenceEquivalenceQuestion question = getSentenceEquivalenceQuestionSamples().get(random.nextInt(getSentenceEquivalenceQuestionSamples().size()));
        prepareQuestion(question, number);
        managerService.save(question);
    }

    @Transactional
    public void createTextCompletionQuestion(Integer number) {
        TextCompletionQuestion question = getTextCompletionQuestionSamples().get(random.nextInt(getTextCompletionQuestionSamples().size()));
        prepareQuestion(question, number);
        managerService.save(question);
    }

    @Transactional
    public void createWritingQuestion(Integer number) {
        WritingQuestion question = getWritingQuestionSamples().get(random.nextInt(getWritingQuestionSamples().size()));
        prepareQuestion(question, number);
        managerService.save(question);
    }

    @Transactional
    public void createDataInterpretationSetQuestion(int number) {
        QuestionTemplate template = dataInterpretationTemplates.get(random.nextInt(dataInterpretationTemplates.size()));
        DataInterpretationSetQuestion question = new DataInterpretationSetQuestion();
        DataInterpretationSetQuestion sampleQuestion = getDataInterpretationSetQuestionSamples().get(random.nextInt(getDataInterpretationSetQuestionSamples().size()));
        question.setTemplate(template);
        question.setText(sampleQuestion.getText() + " " + number);
        question.setDifficulty(template.getDifficulty());
        question.setImage(sampleQuestion.getImage());
        int n = 1;
        int difficultyWeight = 0;
        for (QuestionTemplateItem item : template.getQuestionTemplateItems()) {
            for (int i=0; i< item.getCount(); i++) {
                Question sampleInnerQuestion = sampleQuestion.innerQuestions().get((n - 1) % sampleQuestion.innerQuestions().size());
                Question innerQuestion = null;
                switch (sampleInnerQuestion.getQuestionType()) {
                    case GRE_DATA_INTERPRETATION_SET_NUMERIC:
                        innerQuestion = new DataInterpretationNumericQuestion(question, n++);
                        innerQuestion.setText(sampleInnerQuestion.getText() + " " + number);
                        ((DataInterpretationNumericQuestion)innerQuestion).setNominatorAnswer(((DataInterpretationNumericQuestion)sampleInnerQuestion).getNominatorAnswer());
                        ((DataInterpretationNumericQuestion)innerQuestion).setDenominatorAnswer(((DataInterpretationNumericQuestion)sampleInnerQuestion).getDenominatorAnswer());
                        question.getNumericQuestions().add((DataInterpretationNumericQuestion) innerQuestion);
                        break;
                    case GRE_DATA_INTERPRETATION_SET_MULTIPLE_ANSWER:
                        innerQuestion = new DataInterpretationMultipleAnswerQuestion(question, n++);
                        innerQuestion.setText(sampleInnerQuestion.getText() + " " + number);
                        ((DataInterpretationMultipleAnswerQuestion)innerQuestion).setChoices(new ArrayList<>(((DataInterpretationMultipleAnswerQuestion)sampleInnerQuestion).getChoices()));
                        question.getMultipleAnswerQuestions().add((DataInterpretationMultipleAnswerQuestion) innerQuestion);
                        break;
                    case GRE_DATA_INTERPRETATION_SET_SINGLE_ANSWER:
                        innerQuestion = new DataInterpretationSingleAnswerQuestion(question, n++);
                        innerQuestion.setText(sampleInnerQuestion.getText() + " " + number);
                        ((DataInterpretationSingleAnswerQuestion)innerQuestion).setChoices(new ArrayList<>(((DataInterpretationSingleAnswerQuestion)sampleInnerQuestion).getChoices()));
                        question.getSingleAnswerQuestions().add((DataInterpretationSingleAnswerQuestion) innerQuestion);
                }
                innerQuestion.setDifficulty(template.getDifficulty());
                innerQuestion.setDifficultyLevel(item.getDifficultyLevel());
                difficultyWeight += item.getDifficultyLevel().ordinal();
            }
        }
        int questionsCount = question.getMultipleAnswerQuestions().size() + question.getSingleAnswerQuestions().size() + question.getNumericQuestions().size();
        if (questionsCount == 0)
            return ;
        question.setDifficultyLevel(DifficultyLevel.values()[Math.round((float)difficultyWeight / questionsCount)]);
        managerService.save(question);
    }

    @Transactional
    private void createReadingComprehensionQuestion(int number) {
        QuestionTemplate template = readingComprehensionTemplates.get(random.nextInt(readingComprehensionTemplates.size()));
        ReadingComprehensionQuestion question = new ReadingComprehensionQuestion();
        ReadingComprehensionQuestion sampleQuestion = getReadingComprehensionQuestionSamples().get(random.nextInt(getReadingComprehensionQuestionSamples().size()));
        switch (template.getQuestionType()) {
            case GRE_READING_COMPREHENSION_LONG:
                question.setType(ReadingComprehensionQuestion.Type.LONG);
                break;
            case GRE_READING_COMPREHENSION_MEDIUM:
                question.setType(ReadingComprehensionQuestion.Type.MEDIUM);
                break;
            case GRE_READING_COMPREHENSION_SHORT:
                question.setType(ReadingComprehensionQuestion.Type.SHORT);
                break;
            case GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT:
                question.setType(ReadingComprehensionQuestion.Type.PARAGRAPH_ARGUMENT);
        }
        question.setTemplate(template);
        question.setText(sampleQuestion.getText() + " " + number);
        question.setDifficulty(template.getDifficulty());
        question.setImage(sampleQuestion.getImage());
        int n = 1;
        int difficultyWeight = 0;
        for (QuestionTemplateItem item : template.getQuestionTemplateItems()) {
            for (int i=0; i< item.getCount(); i++) {
                Question sampleInnerQuestion = sampleQuestion.innerQuestions().get((n - 1) % sampleQuestion.innerQuestions().size());
                Question innerQuestion = null;
                switch (sampleInnerQuestion.getQuestionType()) {
                    case GRE_READING_COMPREHENSION_SELECT_IN_PASSAGE:
                        innerQuestion = new SelectInPassageQuestion(question, n++);
                        innerQuestion.setText(sampleInnerQuestion.getText());
                        ((SelectInPassageQuestion)innerQuestion).setAnswer(((SelectInPassageQuestion)sampleInnerQuestion).getAnswer());
                        question.getSelectInPassageQuestions().add((SelectInPassageQuestion) innerQuestion);
                        break;
                    case GRE_READING_COMPREHENSION_MULTIPLE_ANSWER:
                        innerQuestion = new ReadingComprehensionMultipleAnswerQuestion(question, n++);
                        innerQuestion.setText(sampleInnerQuestion.getText() + " " + number);
                        ((ReadingComprehensionMultipleAnswerQuestion)innerQuestion).setChoices(new ArrayList<>(((ReadingComprehensionMultipleAnswerQuestion)sampleInnerQuestion).getChoices()));
                        question.getMultipleAnswerQuestions().add((ReadingComprehensionMultipleAnswerQuestion) innerQuestion);
                        break;
                    case GRE_READING_COMPREHENSION_SINGLE_ANSWER:
                        innerQuestion = new ReadingComprehensionSingleAnswerQuestion(question, n++);
                        innerQuestion.setText(sampleInnerQuestion.getText() + " " + number);
                        ((ReadingComprehensionSingleAnswerQuestion)innerQuestion).setChoices(new ArrayList<>(((ReadingComprehensionSingleAnswerQuestion)sampleInnerQuestion).getChoices()));
                        question.getSingleAnswerQuestions().add((ReadingComprehensionSingleAnswerQuestion) innerQuestion);
                }
                innerQuestion.setDifficulty(template.getDifficulty());
                innerQuestion.setDifficultyLevel(item.getDifficultyLevel());
                difficultyWeight += item.getDifficultyLevel().ordinal();
            }
        }
        int questionsCount = question.getMultipleAnswerQuestions().size() + question.getSingleAnswerQuestions().size() + question.getSelectInPassageQuestions().size();
        if (questionsCount == 0)
            return ;
        question.setDifficultyLevel(DifficultyLevel.values()[Math.round((float) difficultyWeight / questionsCount)]);
        managerService.save(question);
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
