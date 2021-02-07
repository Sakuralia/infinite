package io.adana.infinite.common.web.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.adana.infinite.common.web.domain.enums.ResultCode;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author sakura
 * @description <p>
 * 统一返回前端数据格式类
 * </p>
 */
@JsonPropertyOrder({"code", "msg", "data"})
public class BaseResponse<T> implements Serializable {

    @ApiModelProperty(value = "返回代码")
    private int code;
    @ApiModelProperty(value = "返回消息内容")
    private String msg;
    @ApiModelProperty(value = "返回数据内容")
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(ResultCode rc, T data) {
        this.msg = rc.getMsg();
        this.code = rc.getCode();
        this.data = data;
    }

    public BaseResponse(int code, String msg, T data) {
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
    public static <T> BaseResponse<T> success() {
        return success(ResultCode.RESULT_OK.getMsg(), null);
    }

    /**
     *
     * @param msg  message
     * @param data the data
     * @return any Type
     */
    public static <T> BaseResponse<T> success(String msg, T data) {
        return success(ResultCode.RESULT_OK.getCode(), msg, data);
    }
    /**
     * callback the method which is success and transmit data
     *
     * @param data data
     * @return Base_Response
     */
    public static <T> BaseResponse<T> success(T data) {
        return success(ResultCode.RESULT_OK.getMsg(), data);
    }


    /**
     *
     * @param rc the enum class of result.
     * @param data back the data
     * @param <T> all types
     * @return any type which function back the result.
     */
    public static <T> BaseResponse<T> success(ResultCode rc, T data) {
        return new BaseResponse<>(rc, data);
    }

    public static <T> BaseResponse<T> success(int code, String msg, T data) {
        return new BaseResponse<>(code, msg, data);
    }

    public static <T> BaseResponse<T> error() {
        return error(ResultCode.RESULT_ERROR);
    }
    /**
     * callback the fail with transmitting the enum Object named resultCode.
     *
     * @param rc the enum Object
     * @return Base_Response
     */
    public static <T> BaseResponse<T> error(ResultCode rc) {
        return error(rc.getCode(), rc.getMsg(), null);
    }

    public static <T> BaseResponse<T> error(String msg, T data) {
        return error(ResultCode.RESULT_ERROR.getCode(), msg, data);
    }

    /**
     * the function of return
     *
     * @param data the data
     * @return any type
     */
    public static <T> BaseResponse<T> error(T data) {
        return error(ResultCode.RESULT_ERROR.getMsg(), data);
    }

    /**
     * callback the fail which transmitting the enum Object named resultCode and data.
     *
     * @param rc the enum Object
     * @param data   data
     * @return Base_Response
     */
    public static <T> BaseResponse<T> error(ResultCode rc, T data) {
        return new BaseResponse<>(rc.getCode(), rc.getMsg(), data);
    }

    public static <T> BaseResponse<T> error(int code, String msg, T data) {
        return new BaseResponse<>(code, msg, data);
    }
}
