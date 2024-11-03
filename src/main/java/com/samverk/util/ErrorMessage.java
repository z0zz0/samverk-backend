package com.samverk.util;

public final class ErrorMessage {

    // Authentication messages
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String USER_NOT_FOUND = "User not found";

    // Request messages
    public static final String REQUEST_ATTRIBUTES_ERROR = "Could not obtain request attributes";

    public static final String ORGANIZATION_RELATIONSHIP_NOT_FOUND = "Organization Relationship not found";

    // Private constructor to prevent instantiation
    private ErrorMessage() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
