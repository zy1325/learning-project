package com.zxh.common.exception;

import lombok.Data;

@Data
public class BizException extends RuntimeException {
    /**
     * 错误码
     */
    private String code;

    /**
     * 信息
     */
    private String msg;

    public BizException() {
        super();
    }

    public BizException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BizException(String code, String msg) {
        super(code);
        this.code = code;
        this.msg = msg;
    }

}
