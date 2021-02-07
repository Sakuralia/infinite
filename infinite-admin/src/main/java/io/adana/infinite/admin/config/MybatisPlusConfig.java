package io.adana.infinite.admin.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：
 * <p>
 * mybatis-plus 插件配置类
 * </p>
 *
 * @author sakura
 * @date 2020-10-22 16:58
 */
@Configuration
@MapperScan("io.adana.infinite.admin.mapper")
public class MybatisPlusConfig {


    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        // 设置最大单页数量
        interceptor.setLimit(500);
        // 开启count 的join优化
        interceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return interceptor;
    }
}
