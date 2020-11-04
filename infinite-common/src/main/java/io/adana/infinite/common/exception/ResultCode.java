package io.adana.infinite.common.exception;

/**
 * @author vega-yang
 * @description 统一返回接口状态代码
 * @date 2020-07-22 01:09
 */
public enum ResultCode {
    //============== system ===============
    REQUEST_PARAM_ERROR(400, "parameters error"),
    RESOURCE_FORBIDDEN(403, "access denied "),
    RESULT_NOT_FOUND(404, "not found"),
    RESULT_ERROR(500, "error"),
    RESULT_OK(999, "success"),
    RESULT_EXIST(111, "the recode exists"),
    //============== service ===============
    SERVICE_ERROR(555, "the service errors"),
    //============== user ==================
    LOGIN_PARAMS_ERROR(881, "params errors in which login.For detail,look the api doc"),
    LOGIN_ERROR(885, "the sign-on service errors"),
    LOGIN_CAPTCHA(884, "the verification code errors"),
    USER_NOT_FOUND(886, "the user is not existed"),
    USER_LOCKED(887, "the account is locked"),
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
