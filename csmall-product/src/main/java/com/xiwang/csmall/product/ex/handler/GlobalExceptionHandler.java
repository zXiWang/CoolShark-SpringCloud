package com.xiwang.csmall.product.ex.handler;


import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.web.JsonResult;
import com.xiwang.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public JsonResult<Void> handleServiceException(ServiceException e) {
        log.debug("捕获到ServiceException：{}", e.getMessage());
        return JsonResult.fail(e.getServiceCode(), e.getMessage());
    }

    @ExceptionHandler
    public JsonResult<Void> handleBindException(BindException e) {
        log.debug("捕获到BindException：{}", e.getMessage());
        // 以下2行代码，如果有多种错误时，将随机获取其中1种错误的信息，并响应
        // 当配置了“快速失败”后，Spring Validation检查永远最多只有1种错误
        String message = e.getFieldError().getDefaultMessage();
        return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST, message);
        // ===============================
        // 以上代码，如果有多种错误时，将获取所有错误信息，并响应
        // StringBuilder stringBuilder = new StringBuilder();
        // List<FieldError> fieldErrors = e.getFieldErrors();
        // for (FieldError fieldError : fieldErrors) {
        //     stringBuilder.append(fieldError.getDefaultMessage());
        //  }
        // return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST, stringBuilder.toString());
    }

    @ExceptionHandler
    public JsonResult<Void> handleConstraintViolationException(ConstraintViolationException e) {
        log.debug("捕获到ConstraintViolationException：{}", e.getMessage());
        StringBuilder stringBuilder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            stringBuilder.append(constraintViolation.getMessage());
        }
        return JsonResult.fail(ServiceCode.ERR_BAD_REQUEST, stringBuilder.toString());
    }

    @ExceptionHandler
    public JsonResult<Void> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.debug("捕获到HttpRequestMethodNotSupportedException：{}", e.getMessage());
        String message = "请求失败，请求方式不支持！";
        return JsonResult.fail(ServiceCode.ERR_NOT_ALLOWED, message);
    }

    @ExceptionHandler({
            InternalAuthenticationServiceException.class, // AuthenticationServiceException >> AuthenticationException >> RuntimeException
            BadCredentialsException.class // AuthenticationException
    })
    public JsonResult<Void> handleAuthenticationException(AuthenticationException e) {
        log.debug("捕获到AuthenticationException");
        log.debug("异常类型：{}", e.getClass().getName());
        log.debug("异常信息：{}", e.getMessage());
        String message = "登录失败，用户名或密码错！";
        return JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED, message);
    }

    @ExceptionHandler
    public JsonResult handleDisabledException(DisabledException e) {
        log.debug("捕获到DisabledException：{}", e.getMessage());
        String message = "登录失败，此管理员账号已经被禁用！";
        return JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED_DISABLED, message);
    }

    @ExceptionHandler
    public JsonResult<Void> handleAccessDeniedException(AccessDeniedException e) {
        log.debug("捕获到AccessDeniedException：{}", e.getMessage());
        String message = "请求失败，当前登录的账号不具备此操作权限！";
        return JsonResult.fail(ServiceCode.ERR_FORBIDDEN, message);
    }

    @ExceptionHandler
    public JsonResult<Void> handleThrowable(Throwable e) {
        log.debug("捕获到Throwable：{}", e.getMessage());
        e.printStackTrace(); // 强烈建议
        String message = "程序运行时出现未知错误，请联系系统管理员！";
        return JsonResult.fail(ServiceCode.ERR_UNKNOWN, message);
    }
}
