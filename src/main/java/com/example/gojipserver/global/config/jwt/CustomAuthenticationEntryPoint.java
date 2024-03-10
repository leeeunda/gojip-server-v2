package com.example.gojipserver.global.config.jwt;

import com.example.gojipserver.global.response.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        Object exception = request.getAttribute("exception");
        ErrorCode errorCode;

        log.debug("log: exception: {} ", exception);

        //토큰이 없는 경우
        if(exception == null) {
            errorCode = ErrorCode.NON_LOGIN;
            setResponse(response, errorCode);
            return;
        }
        if(exception.equals(ErrorCode.EXPIRED_TOKEN)) {
            errorCode = ErrorCode.EXPIRED_TOKEN;
            setResponse(response, errorCode);
            return;
        }
        if(exception.equals(ErrorCode.INVALID_TOKEN)) {
            errorCode = ErrorCode.INVALID_TOKEN;
            setResponse(response, errorCode);
        }
        if (exception.equals(ErrorCode.NO_BEARER_TOKEN)) {
            errorCode = ErrorCode.NO_BEARER_TOKEN;
            setResponse(response, errorCode);
        }
        if (exception.equals(ErrorCode.LOGOUT_TOKEN)) {
            errorCode = ErrorCode.LOGOUT_TOKEN;
            setResponse(response, errorCode);
        }
    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("error", errorCode.getStatus());
        errorDetails.put("message", errorCode.getMessage());

        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        out.print(objectMapper.writeValueAsString(errorDetails));
        out.flush();
    }

}
