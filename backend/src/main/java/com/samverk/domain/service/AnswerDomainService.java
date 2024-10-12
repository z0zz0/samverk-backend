package com.samverk.domain.service;

import com.samverk.domain.entity.Answer;
import com.samverk.domain.repository.AnswerRepository;
import com.samverk.util.Log;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerDomainService {
    private final AnswerRepository answerRepository;

    public AnswerDomainService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Cacheable("answers")
    public List<Answer> getAllAnswers() {
        Log.info("Fetching all answers");
        return answerRepository.findAll();
    }

    @Cacheable(value = "answers", key = "#answerId")
    public Answer getAnswerById(UUID answerId) {
        Log.info("Fetching answer with id: " + answerId);
        return answerRepository.findById(answerId)
                .orElseThrow(() -> {
                    Log.error("Answer not found with id: " + answerId);
                    return new RuntimeException("Answer not found");
                });
    }

    @Transactional
    @CacheEvict(value = "answers", allEntries = true)
    public Answer createAnswer(Answer answer) {
        Log.info("Creating new answer");
        return answerRepository.save(answer);
    }

    @Transactional
    @CacheEvict(value = "answers", key = "#answerId")
    public Answer updateAnswer(UUID answerId, Answer answerDetails) {
        Log.info("Updating answer with id: " + answerId);
        Answer answer = getAnswerById(answerId);
        answer.setAnswerType(answerDetails.getAnswerType());
        return answerRepository.save(answer);
    }

    @Transactional
    @CacheEvict(value = "answers", key = "#answerId")
    public void deleteAnswer(UUID answerId) {
        Log.info("Deleting answer with id: " + answerId);
        answerRepository.deleteById(answerId);
    }

    @Cacheable(value = "answersByRequest", key = "#requestId")
    public List<Answer> getAnswersByRequestId(UUID requestId) {
        Log.info("Fetching answers for request id: " + requestId);
        return answerRepository.findByRequestId(requestId);
    }
}