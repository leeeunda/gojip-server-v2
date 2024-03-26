package com.example.gojipserver.global.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private boolean isSuccess;
    private StatusEnum status;
    private T data;
    private String message;
    private Map<String, String> errors; // 파라미터 여러 개에서 예외가 발생했을 경우

    public static <T> ApiResponse<T> responseSuccess(T data) {
        return new ApiResponse<>(true, StatusEnum.OK, data, null, null);
    }
    public static <T> ApiResponse<T> responseSuccess(T data, String message) {
        return new ApiResponse<>(true, StatusEnum.OK, data, message, null);
    }

    public static <T> ApiResponse<T> responseSuccess(T data, StatusEnum status, String message) {
        return new ApiResponse<>(true, status, data, message, null);
    }

    public static <T> ApiResponse<T> response201Success(T data, String message) {
        return new ApiResponse<>(true, StatusEnum.CREATED, data, message, null);
    }



    public static ApiResponse<?> responseSuccessWithNoContent(String message) {
        return new ApiResponse<>(true, StatusEnum.OK, null, message, null);
    }


    // 예외 발생으로 API 호출 실패시 반환
    public static ApiResponse<?> response400Error(String message, Map<String, String> errors) {
        return new ApiResponse<>(false, StatusEnum.BAD_REQUEST, null, message, errors);
    }

    public static ApiResponse<?> response403Error(String message, Map<String, String> errors) {
        return new ApiResponse<>(false, StatusEnum.FORBIDDEN, null, message, errors);
    }

    public static ApiResponse<?> response404Error(String message) {
        return new ApiResponse<>(false, StatusEnum.NOT_FOUND, null, message, null);
    }

    @Builder
    private ApiResponse(boolean isSuccess, StatusEnum status, T data, String message, Map<String, String> errors) {
        this.isSuccess = isSuccess;
        this.status = status;
        this.data = data;
        this.message = message;
        this.errors = errors;
    }


}
