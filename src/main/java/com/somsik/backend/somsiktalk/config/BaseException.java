package com.somsik.backend.somsiktalk.config;

import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseException extends Exception {
    private ResponseStatus status;

    public ResponseStatus getStatus() {
        return this.status;
    }

    public BaseException(ResponseStatus status) {
        this.status = status;
    }
}
