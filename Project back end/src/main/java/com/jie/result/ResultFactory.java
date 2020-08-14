package com.jie.result;

// Use factory pattern to encapsulate the return results
public class ResultFactory {

    // Successful status code 200
    public static Result SuccessResult(Object data ) {
        return buildResult(ResultCode.SUCCESS, "Success",data);
    }

    // Failed status code 400
    public static Result FailResult(String message) {
        return buildResult(ResultCode.FAIL, message, null);
    }

    public static Result buildResult(ResultCode resultCode, String message, Object data) {
        return buildResult(resultCode.code, message, data);
    }

    public static Result buildResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }
}
