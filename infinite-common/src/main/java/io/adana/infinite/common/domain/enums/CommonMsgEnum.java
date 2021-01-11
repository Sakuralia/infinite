package io.adana.infinite.common.domain.enums;

import io.adana.infinite.common.domain.constants.CommonConstant;

import java.util.Objects;

/**
 * @author sakura
 * @date 2021-01-04 00:08
 * @description common Message template
 */
public enum CommonMsgEnum {
    // show forbidden, code: 403
    ACCESS_FORBIDDEN(CommonConstant.COMMON_ACCESS_FORBIDDEN, "FORBIDDEN,you don't have not enough permission to access");

    /**
     * code
     */
    private String code;
    /**
     * code to it's name
     */
    private String value;

    CommonMsgEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * get its object from its code
     *
     * @param code code of enum
     * @return {@link CommonMsgEnum}
     */
    public static CommonMsgEnum getValue(String code) {
        CommonMsgEnum[] values = CommonMsgEnum.values();
        for (CommonMsgEnum msgEnum : values) {
            if (Objects.equals(msgEnum.code, code)) {
                return msgEnum;
            }
        }
        return null;
    }
}