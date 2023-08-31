package com.talentwunder.financetracker.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException {
    private HttpStatus httpStatus;
    private String path;

    public ApiException(HttpStatus httpStatus, String msg, String path) {
        super(msg);
        this.httpStatus = httpStatus;
        this.path = path;
    }

}
