package com.jie.result;

import lombok.AllArgsConstructor;

@AllArgsConstructor
// Use enumeration class to hold constants
public enum ResultCode {

    SUCCESS(200),
    FAIL(400);

    public int code;

}
