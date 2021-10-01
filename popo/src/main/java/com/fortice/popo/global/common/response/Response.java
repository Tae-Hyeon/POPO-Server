package com.fortice.popo.global.common.response;

import lombok.*;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;
}
