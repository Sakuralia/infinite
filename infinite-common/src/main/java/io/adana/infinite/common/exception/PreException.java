package io.adana.infinite.common.exception;

/**
 * @author Lenovo
 * @date 2020/7/22 16:38
 * @description
 */
public class PreException extends RuntimeException {
    private String msg = "server errors,try again please";
    private int code = 5000;

    public PreException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public PreException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public PreException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public PreException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
