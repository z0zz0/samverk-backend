package com.samverk.domain.exception;

public class DuplicateOrganizationException extends RuntimeException 
{
    public DuplicateOrganizationException(String message) 
    {
        super(message);
    }
}
