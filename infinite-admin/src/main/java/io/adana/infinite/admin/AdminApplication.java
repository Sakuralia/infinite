package io.adana.infinite.admin;

import io.adana.infinite.common.annotation.InfiniteCommonsEnableConfigs;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author simonyang
 */
@EnableOpenApi
@EnableDubbo
@SpringCloudApplication
@InfiniteCommonsEnableConfigs
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
