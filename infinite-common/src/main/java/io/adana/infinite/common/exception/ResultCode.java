package io.adana.infinite.common.exception;

/**
 * @author vega-yang
 * @description统一返回接口状态代码
 * @date 2020-07-22 01:09
 */
public enum ResultCode {
    RESULT_NOT_FOUND(4, "not found"),
    RESULT_ERROR(5, "error"),
    RESULT_OK(9, "success"),
    RESULT_EXIST(1, "the recode exists");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
