package com.rudecrab.demo.exception;

import lombok.Getter;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/8/4 9:36
 */
@Getter
public class APIException extends RuntimeException {
    private Integer code;
    private String msg;

    public APIException() {
        this(1001, "接口错误");
    }

    public APIException(String msg) {
        this(1001, msg);
    }

    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
