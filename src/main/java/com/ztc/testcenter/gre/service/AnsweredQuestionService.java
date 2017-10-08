package com.ztc.testcenter.gre.service;

import com.ztc.testcenter.gre.domain.question.Question;
import com.ztc.testcenter.gre.domain.question.QuestionType;
import com.ztc.testcenter.gre.domain.test.AnsweredQuestion;
import com.ztc.testcenter.gre.exceptions.AnsweredQuestionNotFoundException;
import com.ztc.testcenter.gre.exceptions.AnsweredWritingQuestionNotFoundException;
import com.ztc.testcenter.gre.exceptions.TestSectionTimeFinishedException;
import com.ztc.testcenter.gre.repository.test.AnsweredQuestionRepository;
import com.ztc.testcenter.gre.specification.AnsweredQuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * Created by yubar on 7/7/17.
 */

@Service
@Transactional
public class AnsweredQuestionService {

    private final AnsweredQuestionRepository answeredQuestionRepository;
    private final QuestionService questionService;

    @Autowired
    public AnsweredQuestionService(AnsweredQuestionRepository answeredQuestionRepository, QuestionService questionService) {
        this.answeredQuestionRepository = answeredQuestionRepository;
        this.questionService = questionService;
    }

    public Page<AnsweredQuestion> findAnsweredQuestions(AnsweredQuestionSpecification specification, Pageable pageable) {
        Page<AnsweredQuestion> page = answeredQuestionRepository.findAll(specification, pageable);
        findQuestions(page.getContent());
        return page;
    }

    public void seeQuestion(Long id) {
        AnsweredQuestion question = answeredQuestionRepository.findOne(id);
        question.setSeen();
        answeredQuestionRepository.save(question);
        updateLastActivityInfo(question);
    }

    public void answerQuestion(Long answeredQuestionId, String answer) {
        answerQuestion(answeredQuestionId, answer, true);
    }

    public void markQuestion(Long id) {
        AnsweredQuestion question = answeredQuestionRepository.findOne(id);
        question.setMarked(true);
        answeredQuestionRepository.save(question);
        updateLastActivityInfo(question);
    }

    public void unMarkQuestion(Long id) {
        AnsweredQuestion question = answeredQuestionRepository.findOne(id);
        question.setMarked(false);
        answeredQuestionRepository.save(question);
        updateLastActivityInfo(question);
    }

    void answerQuestions(Map<Long, String> answers) {
        answers.keySet().forEach(questionId -> answerQuestion(questionId, answers.get(questionId), false));
    }

    private void answerQuestion(Long answeredQuestionId, String answer, boolean updateLastActivity) {
        AnsweredQuestion answeredQuestion = answeredQuestionRepository.findOne(answeredQuestionId);
        if (answeredQuestion == null)
            throw new AnsweredQuestionNotFoundException();
        if (answeredQuestion.getTestSection().getEndDate() != null)
            throw new TestSectionTimeFinishedException();
        answeredQuestion.setUserAnswer(answer);
        if (answer == null || answer.equals(""))
            answeredQuestion.setStatus(AnsweredQuestion.Status.NOT_ANSWERED);
        else {
            Question question = questionService.findQuestion(answeredQuestion.getQuestion().getId(), answeredQuestion.getQuestionType());
            answeredQuestion.setStatus(question.isCorrect(answer) ? AnsweredQuestion.Status.CORRECT : AnsweredQuestion.Status.INCORRECT);
        }
        if (updateLastActivity)
            updateLastActivityInfo(answeredQuestion);
    }


    private void updateLastActivityInfo(AnsweredQuestion answeredQuestion) {
        long questionElapsedTime = Duration.between(answeredQuestion.getTestSection().getLastActivityDate().toInstant(), Instant.now()).getSeconds();
        answeredQuestion.getTestSection().setRemainingSeconds(answeredQuestion.getTestSection().getRemainingSeconds() - questionElapsedTime);
        answeredQuestion.getTestSection().setLastQuestionNumber(answeredQuestion.getNumber());
        answeredQuestion.getTestSection().setLastActivityDate(new Date());
    }

    public void scoreQuestion(Long id, Double score, String comment) {
        AnsweredQuestion answeredQuestion = answeredQuestionRepository.findOne(id);
        if (answeredQuestion == null)
            throw new AnsweredQuestionNotFoundException();
        if (!answeredQuestion.getQuestionType().equals(QuestionType.GRE_WRITING_ISSUE) && !answeredQuestion.getQuestionType().equals(QuestionType.GRE_WRITING_ARGUMENT))
            throw new AnsweredWritingQuestionNotFoundException();
        answeredQuestion.setScore(score);
        answeredQuestion.setComment(comment);
    }

    private void findQuestions(List<AnsweredQuestion> answeredQuestions) {
        Map<QuestionType, List<Long>> questionTypeIds = new HashMap<>();
        answeredQuestions.forEach(answeredQuestion -> {
            if (!questionTypeIds.containsKey(answeredQuestion.getQuestionType()))
                questionTypeIds.put(answeredQuestion.getQuestionType(), new ArrayList<>());
            questionTypeIds.get(answeredQuestion.getQuestionType()).add(answeredQuestion.getQuestion().getId());
        });
        questionTypeIds.forEach((questionType, ids) -> {
            List<? extends Question> questions = questionService.findQuestions(questionType, ids);
            questions.forEach(question -> {
                for (AnsweredQuestion answeredQuestion : answeredQuestions) {
                    if (answeredQuestion.getQuestion().getId().equals(question.getId())){
                        answeredQuestion.setQuestion(question);
                        break;
                    }
                }
            });
        });
    }

}
