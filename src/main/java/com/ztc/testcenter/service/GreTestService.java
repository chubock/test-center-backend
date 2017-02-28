package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.User;
import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.test.Test;
import com.ztc.testcenter.repository.UserRepository;
import com.ztc.testcenter.repository.question.QuestionRepository;
import com.ztc.testcenter.repository.test.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by yubar on 2/28/17.
 */

@Service
@Transactional
public class GreTestService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    @Autowired
    public GreTestService(UserRepository userRepository, QuestionRepository questionRepository, TestRepository testRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
    }

    public Test createTest(User user) {
        Test test = new Test();
        test.setUser(user);
        test.setDate(new Date());
        test.setDifficulty(Difficulty.MEDIUM);
        return test;
    }
}
