package com.marufh.localizeapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
public class ProductException extends RuntimeException{

    private final String message;

    private final HttpStatus status;
}
