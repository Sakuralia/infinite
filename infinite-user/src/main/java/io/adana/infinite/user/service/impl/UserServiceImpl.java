package io.adana.infinite.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.adana.infinite.user.dao.IUserMapper;
import io.adana.infinite.user.domain.UserEntity;
import io.adana.infinite.user.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * @version 1.0
 * @description
 * @date 2020/10/21 19:01
 */
@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, UserEntity> implements IUserService {
}
