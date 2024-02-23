package com.example.gojipserver.global.exception;

import com.example.gojipserver.global.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse illegalArgExHandle(IllegalArgumentException e) {
        log.error("[illegalArgExHandle]", e);
        return ApiResponse.create400Error(e.getMessage());
    }

    // @Valid 검증 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse methodArgNotValidExHandle(MethodArgumentNotValidException e) {
        log.error("[methodArgNotValidExHandle]", e);

        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        Map<String, String> fieldErrorMap = new HashMap<>();

        // 모든 필드 에러를 담기
        for (FieldError fieldError : fieldErrors) {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            fieldErrorMap.put(fieldName, errorMessage);
        }

        return ApiResponse.create400Error(fieldErrorMap.toString());
    }

    @ExceptionHandler(DuplicateException.class)
    public ApiResponse duplicateExHandle(DuplicateException e) {
        log.error("[duplicateExHandle]", e);
        return ApiResponse.create400Error(e.getMessage());
    }

    @ExceptionHandler(NotOwnerException.class)
    public ApiResponse notOwnerExHandle(NotOwnerException e) {
        log.error("[notOwnerExHandle]", e);
        return ApiResponse.create403Error(e.getMessage());
    }



}
