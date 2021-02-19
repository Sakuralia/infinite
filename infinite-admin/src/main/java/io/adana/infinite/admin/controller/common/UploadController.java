package io.adana.infinite.admin.controller.common;

import io.adana.infinite.common.web.controller.InfiniteCommonController;
import io.adana.infinite.common.web.exception.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sakura
 * @version 1.1
 * @date 2021-02-09 11:00
 * @description UploadController
 * @Scope
 */
@RestController
@RequestMapping(value = "/api/admin")
public class UploadController extends InfiniteCommonController {

    @PostMapping(value = "/upload_file",produces = "multipart/form-data;charset=utf-8")
    public BaseResponse uploadFile(HttpServletRequest request) {
        //TODO coding
        return BaseResponse.success();
    }
}
