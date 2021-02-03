package io.adana.infinite.common.job;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * the annotation of schedule-job-distribute-system
 * @author admin
 * @date 2021-02-03 16:56
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ElasticJobProperties.class, ElasticJobConfig.class})
public @interface InfiniteElasticJob {

    /**
     * the expression of cron, used by control it occurred in the time.
     * @return {@link java.lang.String}
     */
    String cron() default "";
    /**
     * job's Name
     * @return {@link java.lang.String}
     */
    String jobName() default "";


}
