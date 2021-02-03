package io.adana.infinite.common.web.utils;


import io.adana.infinite.common.web.encrypted.Md5Util;

/**
 * the util class about user's password
 *
 * @author admin
 */
public class UserPwdUtil {
    private static final String CHECK_PASSWORD_LEGAL = "^[a-zA-Z0-9!@#$%^&*-_.,+?]{6,18}$";

    /**
     * encode user's password
     *
     * @param password String
     * @return String
     */
    public static String encodePassword(String password) {
        return Md5Util.encodeByMd5(password);
    }

    /**
     * check the password which access to satisfy system settings.
     *
     * @param password String
     * @return Boolean
     */
    public static Boolean checkInputPassword(String password) {
        return password.matches(CHECK_PASSWORD_LEGAL);
    }

//    public static BaseRespVo verifyPassword(String password) {
//        int numb = 6;
//        String spaceStr = " ";
//        if (password.length() < numb) {
//            return BaseRespVo.error("密码长度必须大于6位");
//        }else if (checkInputPassword(password)) {
//            return BaseRespVo.error("密码必须包含数字、字母、符号中至少2种");
//        }else if (password.contains(spaceStr)) {
//            return BaseRespVo.error("不允许有空格");
//        }
//        return null;
//    }
}
