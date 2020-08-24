package com.rex.demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 *  異常統一處理類
 *
 * @author rex
 */
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> defaultErrorHandler(Exception e) {
        // 可以根據 exception 的 type 來做不同處理
        if (e instanceof IllegalArgumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(e.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
