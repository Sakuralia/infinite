package io.adana.infinite.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @author ywb
 * @version 1.0
 * @description
 * @date 2020/12/7 15:31
 */
public class AuthenticationFilter extends AbstractGatewayFilterFactory {
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            HttpHeaders headers = request.getHeaders();
            // TODO 需要先获取Token相关实体
            return null;
        };
    }
}
