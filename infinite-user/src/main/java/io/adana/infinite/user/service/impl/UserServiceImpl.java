package io.adana.infinite.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.adana.infinite.user.api.IUserDetailApi;
import io.adana.infinite.user.dao.IUserMapper;
import io.adana.infinite.user.domain.po.User;
import io.adana.infinite.user.domain.vo.UserVo;
import io.adana.infinite.user.service.IUserService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author sakura
 * @version 1.0
 * @description <p>
 * the implement service class of user.
 * </p>
 * @date 2020/10/21 19:01
 */
@Service(version = "1.0.0", group = "user")
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService, IUserDetailApi {

    @Override
    public Boolean verifyUser(String name, String password, String salt) {
        return null;
    }

    /**
     * 根据用户名获取用户详情
     *
     * @param userName 用户名
     * @return 用户对象
     */
    @Override
    public UserVo getUserDetail(String userName){
        return null;
    }
}
