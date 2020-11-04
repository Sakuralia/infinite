package io.adana.infinite.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.adana.infinite.user.dao.IUserMapper;
import io.adana.infinite.user.domain.UserEntity;
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
public class UserServiceImpl extends ServiceImpl<IUserMapper, UserEntity> implements IUserService {

}
