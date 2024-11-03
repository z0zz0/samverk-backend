package com.samverk.api.exception;

import com.samverk.domain.exception.AddressNotFoundException;
import com.samverk.domain.exception.CountryNotFoundException;
import com.samverk.domain.exception.DuplicateOrganizationException;
import com.samverk.domain.exception.InvalidCountryCodeException;
import com.samverk.domain.exception.OrganizationNotFoundException;
import com.samverk.util.Log;

import jakarta.validation.ConstraintViolationException;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * A global exception handler that provides centralized exception handling for the application.
 * It handles various types of exceptions, including:
 * - Generic exceptions
 * - Duplicate organization exceptions
 * - Organization not found exceptions
 * - Validation exceptions (MethodArgumentNotValidException and ConstraintViolationException)
 * - Address not found exceptions
 * - Country not found exceptions
 * - Invalid country code exceptions
 * - Illegal argument exceptions
 *
 * For each exception type, it logs the exception and returns a corresponding HTTP response with an appropriate status code and an error message.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // Generic exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) 
    {
        // Log the exception
        Log.error("An unexpected error occurred.", ex);

        ErrorResponse errorResponse = new ErrorResponse("Server Error", "An unexpected error occurred.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateOrganizationException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateOrganizationException(DuplicateOrganizationException ex) 
    {
        ErrorResponse errorResponse = new ErrorResponse("Duplicate Organization", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrganizationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrganizationNotFoundException(OrganizationNotFoundException ex) 
    {
        // Log the exception
        Log.error("Organization not found: {}", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Organization Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) 
    {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handler for ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) 
    {
        String errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));

        // Log the validation errors
        Log.warn("Validation failed: {}", errors);

        ErrorResponse errorResponse = new ErrorResponse("Validation Error", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAddressNotFoundException(AddressNotFoundException ex) 
    {
        Log.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Address Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCountryNotFoundException(CountryNotFoundException ex) 
    {
        Log.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Country Not Found", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCountryCodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCountryCodeException(InvalidCountryCodeException ex) 
    {
        Log.warn("Invalid country code: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Invalid Country Code", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) 
    {
        Log.warn("Invalid input: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Invalid Input", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    // ErrorResponse class (could be an inner class or separate file)
    public static class ErrorResponse 
    {
        private String error;
        private String message;

        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

        // Getters
        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }
    }
}