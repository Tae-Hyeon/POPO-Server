package com.fortice.popo.global.common.response;

import lombok.*;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;
    //private List<FieldError> errors;
}
