package com.example.produktapi.service;

import com.example.produktapi.exception.BadRequestException;
import com.example.produktapi.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionHandlerAdviceTest {

    private ExceptionHandlerAdvice exceptionHandlerAdvice;

    @BeforeEach
    public void setUp() {
        exceptionHandlerAdvice = new ExceptionHandlerAdvice();
    }

    @Test
    public void testHandleEntityNotFoundException() {
        // Given: ett EntityNotFoundException-objekt med id 123
        EntityNotFoundException e = new EntityNotFoundException(123);

        // When: handleException() anropas med EntityNotFoundException-objektet
        ResponseEntity response = exceptionHandlerAdvice.handleException(e);

        // Then: en ResponseEntity med status NOT_FOUND och rätt meddelande returneras
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Produkt med id 123 hittades inte", response.getBody());
    }

    @Test
    public void testHandleBadRequestException() {
        // Given: ett BadRequestException-objekt med felmeddelande
        BadRequestException e = new BadRequestException("Felaktig förfrågan");

        // When: handleException() anropas med BadRequestException-objektet
        ResponseEntity response = exceptionHandlerAdvice.handleException(e);

        // Then: en ResponseEntity med status BAD_REQUEST och rätt meddelande returneras
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Felaktig förfrågan", response.getBody());
    }

    @Test
    public void testHandleNullPointerException() {
        // Given: ett NullPointerException-objekt
        NullPointerException e = new NullPointerException();

        // When: handleNullPointerException() anropas med NullPointerException-objektet
        ResponseEntity response = exceptionHandlerAdvice.handleNullPointerException(e);

        // Then: en ResponseEntity med status INTERNAL_SERVER_ERROR och rätt meddelande returneras
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal Server Error, retry or contact system admin", response.getBody());
    }

}
