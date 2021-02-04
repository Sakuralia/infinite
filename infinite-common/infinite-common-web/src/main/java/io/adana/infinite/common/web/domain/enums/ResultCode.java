package io.adana.infinite.common.web.domain.enums;

/**
 * @author vega-yang
 * @description 统一返回接口状态代码
 * @date 2020-07-22 01:09
 */
public enum ResultCode {
    //============== system ===============
    REQUEST_PARAM_ERROR(4000, "parameters error"),
    RESOURCE_FORBIDDEN(4003, "access denied "),
    RESULT_NOT_FOUND(4004, "not found"),
    RESULT_ERROR(5000, "error"),
    RESULT_OK(9999, "success"),
    RESULT_EXIST(1111, "the recode exists"),
    //============== service ===============
    SERVER_ERROR(5555, "the service errors"),
    //============== user ==================
    LOGIN_PARAMS_ERROR(8881, "params errors in which login.For detail,look the api doc"),
    LOGIN_ERROR(8885, "the sign-on service errors"),
    LOGIN_CAPTCHA(8884, "the verification code errors"),
    USER_NOT_FOUND(8886, "the user is not existed"),
    USER_LOCKED(8887, "the account is locked"),
    ;
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
