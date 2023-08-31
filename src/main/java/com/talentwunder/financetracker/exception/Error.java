package com.talentwunder.financetracker.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private HttpStatus status;
    private String message;
    private String path;
}
