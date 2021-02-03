package io.adana.infinite.common.web.annotation;

import io.adana.infinite.common.web.CommonWebConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ywb
 * @version 1.0
 * @description
 * @date 2020/12/21 10:37
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CommonWebConfig.class})
public @interface InfiniteCommonsEnableConfigs {
}
