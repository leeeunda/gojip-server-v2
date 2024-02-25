package com.example.gojipserver.global.config.jwt;

import com.example.gojipserver.global.response.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("error", ErrorCode.ACCESS_DENIED.getStatus());
        errorDetails.put("code", ErrorCode.ACCESS_DENIED.getCode());
        errorDetails.put("message", ErrorCode.ACCESS_DENIED.getMessage());

        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        out.print(objectMapper.writeValueAsString(errorDetails));
        out.flush();
    }
}
