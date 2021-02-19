package io.adana.infinite.admin.interceptor;

import com.alibaba.fastjson.JSONObject;
import io.adana.infinite.admin.annotation.RateLimit;
import io.adana.infinite.admin.config.RedisConfig;
import io.adana.infinite.common.web.domain.enums.ResultCode;
import io.adana.infinite.common.web.exception.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author sakura
 * @version 1.1
 * @date 2020-02-19 09:46
 * @description AccessInterfaceLimitInterceptor
 * @scope io.adana.infinite.admin
 */
@Component
public class InterfaceLimitInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisConfig redisConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            RateLimit rateLimit = ((HandlerMethod) handler).getMethodAnnotation(RateLimit.class);
            if (Objects.isNull(rateLimit)) {
                return true;
            }
            int maxCount = rateLimit.maxCount();
            int expireTime = rateLimit.expireTime();
            boolean hasLogin = rateLimit.toSigneIn();

            if (hasLogin) {
                // TODO 判断是否登录

            }
            String ip = request.getRemoteAddr();
            String key = request.getServletPath() + ":" + ip;
            ValueOperations operations = redisConfig.valueOperations(redisTemplate);
            Integer count = (Integer) operations.get(key);
            if (Objects.isNull(count) || count == -1) {
                operations.set(key, 1, expireTime, TimeUnit.SECONDS);
                return true;
            } else if (count < maxCount) {
                count++;
                operations.set(key, count, 0);
                return true;
            } else {
                responseInfo(response, ResultCode.RESULT_OPERATE_FREQUENTLY);
                return false;
            }
        }
        return true;
    }

    private void responseInfo(HttpServletResponse response, ResultCode code) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BaseResponse baseResponse = new BaseResponse<>(code,null);
        Object obj = JSONObject.toJSON(baseResponse);
        response.getWriter().write(JSONObject.toJSONString(obj));
    }
}
