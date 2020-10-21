package io.adana.infinite.common.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"code", "msg", "data"})
public class BaseResp<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public BaseResp() {
    }

    public BaseResp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * callback the method of which represents the status on success(no data)
     *
     * @return Base_Response
     */
    public static BaseResp<Object> ok() {
        return new BaseResp<>(ResultCode.RESULT_OK.getCode(), ResultCode.RESULT_OK.getMsg(), "");
    }

    /**
     * callback the method which is success and transmit data
     *
     * @param data data
     * @return Base_Response
     */
    public static <T> BaseResp<T> ok(T data) {
        return new BaseResp<>(ResultCode.RESULT_OK.getCode(), ResultCode.RESULT_OK.getMsg(), data);
    }

    /**
     * callback the fail with transmitting the enum Object named resultCode.
     *
     * @param result the enum Object
     * @return Base_Response
     */
    public static BaseResp<Object> error(ResultCode result) {
        return new BaseResp<>(result.getCode(), result.getMsg(), "");
    }

    /**
     * callback the fail which transmitting the enum Object named resultCode and data.
     *
     * @param result the enum Object
     * @param data   data
     * @return Base_Response
     */
    public static <T> BaseResp<T> error(ResultCode result, T data) {
        return new BaseResp<>(result.getCode(), result.getMsg(), data);
    }
}
