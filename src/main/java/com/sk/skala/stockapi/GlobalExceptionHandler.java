package com.sk.skala.stockapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sk.skala.stockapi.config.Error;
import com.sk.skala.stockapi.data.common.Response;
import com.sk.skala.stockapi.exception.ParameterException;
import com.sk.skala.stockapi.exception.ResponseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 롬복 에러 대비: 직접 Logger 선언
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 핵심 추가: @Valid 검증 실패 시 발생하는 예외 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response handleValidationException(MethodArgumentNotValidException e) {
        // 에러 메시지 중 첫 번째 내용을 가져옴
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        
        Response response = new Response();
        // Parameter 에러 코드를 사용하거나 직접 숫자를 넣으세요.
        response.setResult(-1); 
        response.setMessage(errorMessage);
        response.setError("INVALID_PARAMETER");
        
        log.error("Validation Error: {}", errorMessage);
        return response;
    }

    @ExceptionHandler(value = Exception.class)
    public @ResponseBody Response takeException(Exception e) {
        Response response = new Response();
        response.setResult(-1);
        response.setMessage(e.getMessage());
        response.setError("SYSTEM_ERROR");
        log.error("GlobalExceptionHandler.Exception: {}", e.getMessage());
        return response;
    }

    @ExceptionHandler(value = NullPointerException.class)
    public @ResponseBody Response takeNullPointerException(NullPointerException e) {
        Response response = new Response();
        response.setResult(-1);
        response.setMessage("Null Pointer Exception occurred");
        response.setError("NULL_POINTER_ERROR");
        log.error("GlobalExceptionHandler.NullPointerException: {}", e.getMessage());
        return response;
    }

    @ExceptionHandler(value = ParameterException.class)
    public @ResponseBody Response takeParameterException(ParameterException e) {
        Response response = new Response();
        // response.setError(e.getCode(), e.getMessage()); // 기존 방식이 에러나면 아래처럼 수정
        response.setResult(-1);
        response.setMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler(value = ResponseException.class)
    public @ResponseBody Response takeResponseException(ResponseException e) {
        Response response = new Response();
        response.setResult(-1);
        response.setMessage(e.getMessage());
        return response;
    }
}