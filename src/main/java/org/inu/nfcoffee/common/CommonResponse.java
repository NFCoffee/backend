package org.inu.nfcoffee.common;

public class CommonResponse<T> {
    private String code;
    private String message;
    private T data;

    private CommonResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResponse<T> from(ErrorCode errorCode, T data) {
        return new CommonResponse<>(errorCode.errCode(), errorCode.description(), data);
    }
}
