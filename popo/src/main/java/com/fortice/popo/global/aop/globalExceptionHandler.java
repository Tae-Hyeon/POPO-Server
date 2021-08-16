package com.fortice.popo.global.aop;

import com.fortice.popo.global.common.response.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.BindException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice(annotations = RestController.class)
public class globalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(-400, "서버 에러 발생");
        return errorResponse;
    }

//    @ExceptionHandler(BindException.class)
//    protected ErrorResponse handleBindException(BindException e) {
//    }
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    protected ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
//
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    protected ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
//
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    protected ErrorResponse handleAccessDeniedException(AccessDeniedException e) {
//    }
//
    @ExceptionHandler(Exception.class)
    protected ErrorResponse handleException(Exception e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(-400, "서버 에러 발생");
        return errorResponse;
    }

}
