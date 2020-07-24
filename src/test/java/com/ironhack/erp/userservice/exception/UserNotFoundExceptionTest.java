package com.ironhack.erp.userservice.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNotFoundExceptionTest {
    @Test
    void assertThrows() {
        new UserNotFoundException(null);
    }
}