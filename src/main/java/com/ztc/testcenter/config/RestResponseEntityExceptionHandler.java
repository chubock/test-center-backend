package com.ztc.testcenter.config;

import com.ztc.testcenter.exceptions.BadRequestException;
import com.ztc.testcenter.exceptions.NotFoundException;
import com.ztc.testcenter.exceptions.TestCenterException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by yubar on 9/30/17.
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        return handleExceptionInternal(ex, getErrorBody(ex),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, getErrorBody(ex),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private String getErrorBody(TestCenterException exception) {
        return new StringBuilder()
                .append('{')
                .append('"')
                .append("code")
                .append('"')
                .append(':')
                .append(exception.getCode())
                .append(',')
                .append('"')
                .append("message")
                .append('"')
                .append(':')
                .append('"')
                .append(exception.getClass().getSimpleName())
                .append('"')
                .append('}')
                .toString();
    }

}
