package com.samverk.application;

import com.samverk.domain.entity.Request;
import com.samverk.domain.service.RequestDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RequestService {
    private final RequestDomainService requestDomainService;

    public RequestService(RequestDomainService requestDomainService) {
        this.requestDomainService = requestDomainService;
    }

    public List<Request> getAllRequests() {
        return requestDomainService.getAllRequests();
    }

    public Request getRequestById(UUID requestId) {
        return requestDomainService.getRequestById(requestId);
    }

    public Request createRequest(Request request) {
        return requestDomainService.createRequest(request);
    }

    public Request updateRequest(UUID requestId, Request requestDetails) {
        return requestDomainService.updateRequest(requestId, requestDetails);
    }

    public void deleteRequest(UUID requestId) {
        requestDomainService.deleteRequest(requestId);
    }
}