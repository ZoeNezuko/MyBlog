package com.jie.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// Use lombok annotation to give attribute methods and constructors
public class Result {

    private int code;
    private String message;
    private Object result;
}
