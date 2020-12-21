package io.adana.infinite.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sakura
 * @description 项目网关启动类
 * @date 2020-12-07 11:19
 */
@EnableDiscoveryClient
@SpringBootApplication
public class InfiniteGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfiniteGatewayApplication.class, args);
    }

}
