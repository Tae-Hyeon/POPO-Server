package com.fortice.popo.global.common.response;

import lombok.*;

import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Body<T> {
    private int code;
    private String message;
    private T data;
}
