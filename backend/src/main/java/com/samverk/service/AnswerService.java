package com.samverk.service;

import com.samverk.domain.model.Answer;
import com.samverk.domain.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Answer getAnswerById(UUID answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found"));
    }

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer updateAnswer(UUID answerId, Answer answerDetails) {
        Answer answer = getAnswerById(answerId);
        answer.setAnswerType(answerDetails.getAnswerType());
        // Update other fields as necessary
        return answerRepository.save(answer);
    }

    public void deleteAnswer(UUID answerId) {
        answerRepository.deleteById(answerId);
    }
}
