package com.xiwang.csmall.passport.ex;

import com.xiwang.csmall.passport.web.ServiceCode;

public class ServiceException extends RuntimeException {
    private ServiceCode serviceCode;

    public ServiceException(ServiceCode serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }

    public ServiceCode getServiceCode() {
        return serviceCode;
    }}
