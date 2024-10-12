package com.samverk.application;

import com.samverk.domain.model.Answer;
import com.samverk.domain.service.AnswerDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerService {
    private final AnswerDomainService answerDomainService;

    public AnswerService(AnswerDomainService answerDomainService) {
        this.answerDomainService = answerDomainService;
    }

    public List<Answer> getAllAnswers() {
        return answerDomainService.getAllAnswers();
    }

    public Answer getAnswerById(UUID answerId) {
        return answerDomainService.getAnswerById(answerId);
    }

    public Answer createAnswer(Answer answer) {
        return answerDomainService.createAnswer(answer);
    }

    public Answer updateAnswer(UUID answerId, Answer answerDetails) {
        return answerDomainService.updateAnswer(answerId, answerDetails);
    }

    public void deleteAnswer(UUID answerId) {
        answerDomainService.deleteAnswer(answerId);
    }

    public List<Answer> getAnswersByRequestId(UUID requestId) {
        return answerDomainService.getAnswersByRequestId(requestId);
    }
}