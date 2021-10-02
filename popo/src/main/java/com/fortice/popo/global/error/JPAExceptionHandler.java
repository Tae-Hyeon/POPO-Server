package com.fortice.popo.global.error;

import com.fortice.popo.global.common.response.ErrorResponse;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.jar.JarException;

@RestControllerAdvice(annotations = {RestController.class, Service.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JPAExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ErrorResponse handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(404, "이미 삭제되었거나, 없는 데이터에 접근하였습니다.(No Data)");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundDataException.class)
    protected ErrorResponse handleNotFoundDataException(NotFoundDataException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(404, "이미 삭제되었거나, 없는 데이터에 접근하였습니다.(No Data)");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        e.printStackTrace();
        final ErrorResponse errorResponse = new ErrorResponse(500, "데이터베이스 제약조건에 위배되는 작업이 발생했습니다. 관리자에게 문의바랍니다.");
        return errorResponse;
    }
}
