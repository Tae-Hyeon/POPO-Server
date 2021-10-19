package com.fortice.popo.global.error;

import com.fortice.popo.global.common.response.ErrorResponse;
import com.fortice.popo.global.error.exception.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = {RestController.class, Service.class})
@Order(1)
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullParamsException.class)
    protected ErrorResponse handleNullParamsException(NullParamsException e) {
        e.printStackTrace();
        final ErrorResponse errorResponse = new ErrorResponse(400, "요청 내용이 비어있습니다.(Body is null)");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyCreatedException.class)
    protected ErrorResponse handleAlreadyCreatedException(AlreadyCreatedException e) {
        e.printStackTrace();
        final ErrorResponse errorResponse = new ErrorResponse(400, "이미 생성된 상태입니다.");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TokenValidFailedException.class)
    protected ErrorResponse handleTokenValidFailedException(TokenValidFailedException e) {
        e.printStackTrace();
        final ErrorResponse errorResponse = new ErrorResponse(403, "토큰이 유효하지 않습니다.");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationFailedException.class)
    protected ErrorResponse handleAuthenticationFailedException(AuthenticationFailedException e) {
        e.printStackTrace();
        final ErrorResponse errorResponse = new ErrorResponse(403, "인증에 실패했습니다.");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NoPermissionException.class)
    protected ErrorResponse handleNoPermissionException(NoPermissionException e) {
        e.printStackTrace();
        final ErrorResponse errorResponse = new ErrorResponse(403, "소유자가 아니거나, 권한이 없습니다.");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(MultipartFileTypeRestrictException.class)
    protected ErrorResponse handleMultipartFileTypeRestrictException(MultipartFileTypeRestrictException e) {
        e.printStackTrace();
        final ErrorResponse errorResponse = new ErrorResponse(403, "파일의 타입이 옳지 않습니다.(이미지 외 불가능)");
        return errorResponse;
    }
}
