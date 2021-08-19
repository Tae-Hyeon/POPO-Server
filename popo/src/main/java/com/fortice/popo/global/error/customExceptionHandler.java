package com.fortice.popo.global.error;

import com.fortice.popo.global.common.response.ErrorResponse;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import com.fortice.popo.global.error.exception.NullParamsException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = Service.class)
public class customExceptionHandler {
    @ExceptionHandler(NotFoundDataException.class)
    protected ErrorResponse handleNotFoundDataException(NotFoundDataException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(400, "원하는 내용의 데이터가 없습니다.(No Data)");
        return errorResponse;
    }

    @ExceptionHandler(NullParamsException.class)
    protected ErrorResponse NullParamsException(NullParamsException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(400, "요청 내용이 비어있습니다.(Body is null)");
        return errorResponse;
    }
}
