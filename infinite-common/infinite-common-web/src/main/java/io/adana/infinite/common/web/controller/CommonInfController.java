package io.adana.infinite.common.web.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.adana.infinite.common.web.domain.enums.ResultCode;
import io.adana.infinite.common.web.exception.BaseResponse;

/**
 * @author sakura
 * @version 1.1
 * @date 2021-02-09 11:41
 * @description CommonInfController
 * @Scope
 */
public abstract class CommonInfController<T> {

    protected BaseResponse<T> success() {
        return BaseResponse.success();
    }

    protected BaseResponse<T> success(String msg) {
        return BaseResponse.success(msg, null);
    }

    protected BaseResponse<T> success(T data) {
        return BaseResponse.success(data);
    }

    protected BaseResponse<T> success(ResultCode rc, T data) {
        return BaseResponse.success(rc, data);
    }


    protected BaseResponse<T> error() {
        return BaseResponse.error();
    }

    protected BaseResponse<T> error(String msg) {
        return BaseResponse.error(msg, null);
    }

    protected BaseResponse<T> error(T data) {
        return BaseResponse.error(data);
    }

    protected BaseResponse<T> error(ResultCode rc, T data) {
        return BaseResponse.error(rc, data);
    }

    protected BaseResponse<T> error(Exception e) {
        BaseResponse<T> error = error(ResultCode.RESULT_ERROR, null);
        error.setMsg(String.format("%s: %s", e.getClass().getSimpleName(), e.getLocalizedMessage()));
        return error;
    }
}
