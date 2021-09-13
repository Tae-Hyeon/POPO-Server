package com.fortice.popo.global.error;

public class ErrorMessage {
    public String minDigitError(String property, int value) {
        return property + "는 최소 " + value + " 까지만 설정 가능합니다.";
    }
    public String maxDigitError(String property, int value) {
        return property + "는 최대 " + value + " 까지만 설정 가능합니다.";
    }
    public String onlyDigitError() {
        return "정수형 숫자만 입력해주세요.";
    }
    public String emptyError(String property) {
        return property + "를 설정해주세요.";
    }
    public String blankError(String property) {
        return property + "를 설정해주세요.";
    }
}
