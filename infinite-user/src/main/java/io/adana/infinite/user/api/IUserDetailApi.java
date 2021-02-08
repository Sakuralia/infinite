package io.adana.infinite.user.api;

import io.adana.infinite.user.domain.vo.UserVo;

/**
 * @author sakura
 * @version 1.0
 * @description
 * @date 2020/10/22 18:48
 */
public interface IUserDetailApi {
    /**
     * 验证用户身份
     *
     * @param name     用户名
     * @param password 密码
     * @return true or false
     */
    Boolean verifyUser(String name, String password, String salt);

    /**
     * 根据用户名获取用户详情
     *
     * @param userName 用户名
     * @return 用户对象
     */
    UserVo getUserDetail(String userName);
}
