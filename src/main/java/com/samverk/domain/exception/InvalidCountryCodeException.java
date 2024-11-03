package com.samverk.domain.exception;

public class InvalidCountryCodeException extends RuntimeException 
{
    public InvalidCountryCodeException(String message) 
    {
        super(message);
    }
}
