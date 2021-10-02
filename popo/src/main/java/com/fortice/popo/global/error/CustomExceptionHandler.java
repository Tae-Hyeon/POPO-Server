package com.fortice.popo.global.error;

import com.fortice.popo.global.common.response.ErrorResponse;
import com.fortice.popo.global.error.exception.MultipartFileTypeRestrictException;
import com.fortice.popo.global.error.exception.NoPermissionException;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import com.fortice.popo.global.error.exception.NullParamsException;
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
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(400, "요청 내용이 비어있습니다.(Body is null)");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NoPermissionException.class)
    protected ErrorResponse handleNoPermissionException(NoPermissionException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(403, "소유자가 아니거나, 권한이 없습니다.");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(MultipartFileTypeRestrictException.class)
    protected ErrorResponse handleMultipartFileTypeRestrictException(MultipartFileTypeRestrictException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(403, "파일의 타입이 옳지 않습니다.(이미지 외 불가능)");
        return errorResponse;
    }
}
