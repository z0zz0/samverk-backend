package com.samverk.controller;

import com.samverk.application.RequestService;
import com.samverk.domain.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @GetMapping
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable UUID id) {
        return ResponseEntity.ok(requestService.getRequestById(id));
    }

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        return new ResponseEntity<>(requestService.createRequest(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable UUID id, @RequestBody Request requestDetails) {
        return ResponseEntity.ok(requestService.updateRequest(id, requestDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable UUID id) {
        requestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }
}
