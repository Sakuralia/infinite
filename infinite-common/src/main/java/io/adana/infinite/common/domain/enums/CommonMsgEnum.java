package io.adana.infinite.common.domain.enums;

/**
 * @author sakura
 * @date 2021-01-04 00:08
 * @description
 */
public enum CommonMsgEnum {
    ACCESS_FORBIDDEN("COMMON_ACCESS_FORBIDDEN", "FORBIDDEN,you don't have not enough permission to access");
    private String code;
    private String msg;

    CommonMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}