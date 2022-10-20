package com.xiwang.csmall.product.ex;

import com.xiwang.csmall.product.web.ServiceCode;

public class ServiceException extends RuntimeException {
    private ServiceCode serviceCode;

    public ServiceException(ServiceCode serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }

    public ServiceCode getServiceCode() {
        return serviceCode;
    }}
