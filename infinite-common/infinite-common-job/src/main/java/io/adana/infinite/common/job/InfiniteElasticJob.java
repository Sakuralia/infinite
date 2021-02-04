package io.adana.infinite.common.job;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * the annotation of schedule-job-distribute-system
 *
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
     *
     * @return {@link java.lang.String}
     */
    String cron() default "";

    /**
     * job's Name
     *
     * @return {@link java.lang.String}
     */
    String jobName() default "";

    /**
     * Sharding total count
     *
     * @return {@link int}
     */
    int shardingTotalCount() default 1;

    /**
     * Sharding item parameters
     *
     * @return {@link java.lang.String}
     */
    String shardingItemParameters() default "";

    /**
     * Job parameter
     *
     * @return {@link java.lang.String}
     */
    String jobParameter() default "";

    /**
     * Enable or disable job failover
     *
     * @return {@link java.lang.Boolean}
     */
    boolean failover() default false;

    /**
     * Enable or disable the missed task to re-execute
     *
     * @return {@link java.lang.Boolean}
     */
    boolean misfire() default true;

    /**
     * Job description
     *
     * @return {@link java.lang.String}
     */
    String description() default "";

    /**
     * Monitor job execution status
     *
     * @return {@link java.lang.Boolean}
     */
    boolean monitorExecution() default true;

    /**
     * The maximum value for time difference between server
     * and registry center in seconds
     *
     * @return {@link int}
     */
    int maxTimeDiffSeconds() default -1;

    /**
     * Service scheduling interval in minutes for repairing
     * job server inconsistent state
     *
     * @return {@link int}
     */
    int reconcileIntervalMinutes() default 10;

    /**
     * Job sharding strategy type
     *
     * @return {@link java.lang.String}
     */
    String jobShardingStrategyType() default "AVG_ALLOCATION";

    /**
     * Job thread pool handler type
     *
     * @return {@link java.lang.String}
     */
    String jobExecutorServiceHandlerType() default "CPU";

    /**
     * Job error handler type
     *
     * @return {@link java.lang.String}
     */
    String jobErrorHandlerType() default "";

    /**
     * Enable or disable start the job
     *
     * @return {@link java.lang.Boolean}
     */
    boolean disabled() default false;

    /**
     * Enable or disable local configuration override
     * registry center configuration
     *
     * @return {@link java.lang.Boolean}
     */
    boolean overwrite() default false;
}
