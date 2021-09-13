package com.fortice.popo.global.error;

import com.fortice.popo.global.common.response.ErrorResponse;
import com.fortice.popo.global.error.exception.NoPermissionException;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import com.fortice.popo.global.error.exception.NullParamsException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = {RestController.class, Service.class})
@Order(1)
public class CustomExceptionHandler {

    @ExceptionHandler(NullParamsException.class)
    protected ErrorResponse NullParamsException(NullParamsException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(400, "요청 내용이 비어있습니다.(Body is null)");
        return errorResponse;
    }

    @ExceptionHandler(NoPermissionException.class)
    protected ErrorResponse NoPermissionException(NoPermissionException e) {
        System.out.println(e);
        final ErrorResponse errorResponse = new ErrorResponse(403, "소유자가 아니거나, 권한이 없습니다.");
        return errorResponse;
    }
}
