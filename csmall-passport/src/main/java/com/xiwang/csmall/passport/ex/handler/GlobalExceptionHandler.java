package com.xiwang.csmall.passport.ex.handler;


import com.xiwang.csmall.passport.ex.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String handlerServiceException(ServiceException e) {
        log.debug("捕获到一条ServiceException: {}", e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.debug("捕获到HttpRequestMethodNotSupportedException：{}", e.getMessage());
        return "非法访问！";
    }
    ;
    @ExceptionHandler
    public String handlerThrowable(Throwable e) {
        log.debug("捕获到一条Throwable: {}", e.getMessage());
        e.printStackTrace();
        return e.getMessage();
    }
}
