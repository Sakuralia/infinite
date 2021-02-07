package io.adana.infinite.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ywb
 * @version 1.1
 * @date 2021-02-07 10:00
 * @description MybatisPlusConfig
 * @Scope
 */
@Configuration
@MapperScan("io.adana.infinite.user.dao")
public class MybatisPlusConfig {
}
