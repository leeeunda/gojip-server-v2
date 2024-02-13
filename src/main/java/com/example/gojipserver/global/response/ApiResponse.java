package com.example.gojipserver.global.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private boolean isSuccess;
    private StatusEnum status;
    private T data;
    private String message;

    public static <T> ApiResponse<T> createSuccess(T data) {
        return new ApiResponse<>(true, StatusEnum.OK, data, null);
    }

    public static ApiResponse<?> createSuccessWithNoContent() {
        return new ApiResponse<>(true, StatusEnum.OK, null, null);
    }


    // 예외 발생으로 API 호출 실패시 반환
    public static ApiResponse<?> createError(String message) {
        return new ApiResponse<>(false, StatusEnum.BAD_REQUEST, null, message);
    }

    @Builder
    private ApiResponse(boolean isSuccess, StatusEnum status, T data, String message) {
        this.isSuccess = isSuccess;
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
