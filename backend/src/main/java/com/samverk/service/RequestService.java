package com.samverk.service;

import com.samverk.domain.model.Request;
import com.samverk.domain.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Request getRequestById(UUID requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }

    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    public Request updateRequest(UUID requestId, Request requestDetails) {
        Request request = getRequestById(requestId);
        request.setRequestType(requestDetails.getRequestType());
        request.setRequestDescription(requestDetails.getRequestDescription());
        request.setStartTime(requestDetails.getStartTime());
        request.setEndTime(requestDetails.getEndTime());
        request.setAnswerExpirationTime(requestDetails.getAnswerExpirationTime());
        // Update other fields as necessary
        return requestRepository.save(request);
    }

    public void deleteRequest(UUID requestId) {
        requestRepository.deleteById(requestId);
    }
}
