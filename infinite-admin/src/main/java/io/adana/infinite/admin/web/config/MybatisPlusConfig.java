package io.adana.infinite.admin.web.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：
 * <p>
 * mybatis-plus 插件配置类
 * </p>
 *
 * @author simonyang
 * @date 2020-10-22 16:58
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
