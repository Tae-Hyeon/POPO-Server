package com.fortice.popo.global.error;

import com.fortice.popo.global.common.response.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.BindException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice(annotations = Service.class)
public class globalExceptionHandler {
    /**
     * 서버의 잘못으로 발생하는 전체적인 오류에 대한 예외처리
     * 매개변수 타입 불일치, 매개변수 유효성 불통과, 널 포인터 참조
     * 바인드(포트) 사용중, 리퀘스트 메소드 미지원, 접근 거부
     * @param Exception e
     * @return Error Response With code, message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(500, "서버에서 메소드를 잘못 호출했습니다. 관리자에게 문의바랍니다.");
        return errorResponse;
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(500, "서버에서 메소드의 매개변수 타입이 일치하지 않습니다. 관리자에게 문의바랍니다.");
        return errorResponse;
    }

    @ExceptionHandler(NullPointerException.class)
    protected ErrorResponse handleNullPointerException(NullPointerException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(500, "서버에서 비어있는 객체를 호출했습니다. 관리자에게 문의바랍니다.");
        return errorResponse;
    }

    @ExceptionHandler(BindException.class)
    protected ErrorResponse handleBindException(BindException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(500, "특정 포트가 이미 사용중입니다. 관리자에게 문의바랍니다.");
        return errorResponse;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(501, "특정 요청 메소드를 지원하지 않습니다. 관리자에게 문의바랍니다.");
        return errorResponse;
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ErrorResponse handleAccessDeniedException(AccessDeniedException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(403, "서버에서 허용되지 않는 데이터에 접근을 시도했습니다. 관리자에게 문의바랍니다.");
        return errorResponse;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ErrorResponse handleNoHandlerFoundException(NoHandlerFoundException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(404, "서버에서 지원하지 않는 요청입니다. 관리자에게 문의바랍니다.");
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    protected ErrorResponse handleException(Exception e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(500, "서버에서 예상하지 못한 오류가 발생했습니다. 관리자에게 문의바랍니다.");
        return errorResponse;
    }

}
