package com.samverk.domain.service;

import com.samverk.domain.model.Request;
import com.samverk.domain.repository.RequestRepository;
import com.samverk.util.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RequestDomainService {
    private final RequestRepository requestRepository;

    public RequestDomainService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> getAllRequests() {
        Log.info("Fetching all requests");
        return requestRepository.findAll();
    }

    public Request getRequestById(UUID requestId) {
        Log.info("Fetching request with id: " + requestId);
        return requestRepository.findById(requestId)
                .orElseThrow(() -> {
                    Log.error("Request not found with id: " + requestId);
                    return new RuntimeException("Request not found");
                });
    }

    @Transactional
    public Request createRequest(Request request) {
        Log.info("Creating new request");
        return requestRepository.save(request);
    }

    @Transactional
    public Request updateRequest(UUID requestId, Request requestDetails) {
        Log.info("Updating request with id: " + requestId);
        Request request = getRequestById(requestId);
        request.setRequestType(requestDetails.getRequestType());
        request.setRequestDescription(requestDetails.getRequestDescription());
        request.setStartTime(requestDetails.getStartTime());
        request.setEndTime(requestDetails.getEndTime());
        request.setAnswerExpirationTime(requestDetails.getAnswerExpirationTime());
        return requestRepository.save(request);
    }

    @Transactional
    public void deleteRequest(UUID requestId) {
        Log.info("Deleting request with id: " + requestId);
        requestRepository.deleteById(requestId);
    }
}
