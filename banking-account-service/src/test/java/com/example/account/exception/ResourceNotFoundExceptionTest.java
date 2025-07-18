package com.example.account.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void throwsCorrectMessage_whenExceptionIsCreated() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Account", "id", 12345);

        assertEquals("Account not found with id : '12345'", exception.getMessage());
    }

    @Test
    void returnsCorrectResourceName_whenGetterIsCalled() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Account", "id", 12345);

        assertEquals("Account", exception.getResourceName());
    }

    @Test
    void returnsCorrectFieldName_whenGetterIsCalled() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Account", "id", 12345);

        assertEquals("id", exception.getFieldName());
    }

    @Test
    void returnsCorrectFieldValue_whenGetterIsCalled() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Account", "id", 12345);

        assertEquals(12345, exception.getFieldValue());
    }

    @Test
    void handlesNullFieldValueCorrectly() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Account", "id", null);

        assertEquals("Account not found with id : 'null'", exception.getMessage());
        assertNull(exception.getFieldValue());
    }
}