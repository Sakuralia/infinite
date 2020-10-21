package io.adana.infinite.admin.web.exception;

import io.adana.infinite.common.exception.BaseResp;
import io.adana.infinite.common.exception.PreException;
import io.adana.infinite.common.exception.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

/**
 * @author Lenovo
 * @date 2020/7/22 16:33
 * @description the function of intercepted Exception within the back-end
 */
@RestControllerAdvice
public class PreExceptionHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * the function which display in back-end.
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(PreException.class)
    public BaseResp handlePreException(PreException ex) {
        BaseResp resp = new BaseResp();
        resp.setCode(ex.getCode());
        resp.setMsg(ex.getMsg());
        log.error(ex.getMessage(), ex);
        return resp;
    }

    /**
     * deal the situation which is not found.
     *
     * @param e exception
     * @return BaseResp
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResp handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResp.error(ResultCode.RESULT_NOT_FOUND, "路径不存在，请检查路径是否正确");
    }

    /**
     * deal the situation which exist same records.
     *
     * @param e exception
     * @return BaseResp
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public BaseResp handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return BaseResp.error(ResultCode.RESULT_EXIST, "数据库中已存在该记录");
    }

    /**
     * deal the situation which display the message of thread exception.
     *
     * @param e exception
     * @return BaseResp
     */
    @ExceptionHandler(Exception.class)
    public BaseResp handleException(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResp.error(ResultCode.RESULT_ERROR, e.getCause());
    }
}
