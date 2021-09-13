package com.fortice.popo.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;
}
