package io.adana.infinite.common.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
    public static BaseResponse<Object> ok() {
        return new BaseResponse<>(ResultCode.RESULT_OK.getCode(), ResultCode.RESULT_OK.getMsg(), "");
    }

    /**
     * callback the method which is success and transmit data
     *
     * @param data data
     * @return Base_Response
     */
    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<>(ResultCode.RESULT_OK.getCode(), ResultCode.RESULT_OK.getMsg(), data);
    }

    /**
     * callback the fail with transmitting the enum Object named resultCode.
     *
     * @param result the enum Object
     * @return Base_Response
     */
    public static BaseResponse<Object> error(ResultCode result) {
        return new BaseResponse<>(result.getCode(), result.getMsg(), "");
    }

    /**
     * callback the fail which transmitting the enum Object named resultCode and data.
     *
     * @param result the enum Object
     * @param data   data
     * @return Base_Response
     */
    public static <T> BaseResponse<T> error(ResultCode result, T data) {
        return new BaseResponse<>(result.getCode(), result.getMsg(), data);
    }
}
