package io.adana.infinite.common.job;


import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

import java.util.Objects;

/**
 * @author ywb
 * @version 1.1
 * @date 2021-02-04 11:52
 * @description ElasticJobService
 * @Scope
 */
public class ElasticJobService implements InitializingBean {

    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    private ApplicationContext ctx;

    public ElasticJobService(ZookeeperRegistryCenter zookeeperRegistryCenter, ApplicationContext ctx) {
        this.ctx = ctx;
        this.zookeeperRegistryCenter = zookeeperRegistryCenter;
    }

    public <T extends SimpleJob> void createJob(T job, String jobName, String cron, int shardingTotalCount,
                                                String shardingItemParameters, boolean misfire, boolean failover,
                                                String jobParameter, String description, boolean monitorExecution,
                                                boolean disabled, boolean overwrite, String jobShardingStrategyType,
                                                String jobExecutorServiceHandlerType, String jobErrorHandlerType,
                                                int maxTimeDiffSeconds, int reconcileIntervalMinutes,
                                                String zkServerNode, String zkServerNamespace) {
        Objects.requireNonNull(job);
        jobName = StringUtils.defaultIfBlank(jobName, job.getClass().getName());
        JobConfiguration configuration = JobConfiguration.newBuilder(jobName, shardingTotalCount)
                .cron(cron)
                .misfire(misfire)
                .jobParameter(jobParameter)
                .shardingItemParameters(shardingItemParameters)
                .description(description)
                .failover(failover)
                .monitorExecution(monitorExecution)
                .overwrite(overwrite)
                .disabled(disabled)
                .jobShardingStrategyType(jobShardingStrategyType)
                .maxTimeDiffSeconds(maxTimeDiffSeconds)
                .reconcileIntervalMinutes(reconcileIntervalMinutes)
                .jobErrorHandlerType(jobErrorHandlerType)
                .jobExecutorServiceHandlerType(jobExecutorServiceHandlerType)
                .build();

        ZookeeperRegistryCenter zkRegistryCenter = new ZookeeperRegistryCenter(
                new ZookeeperConfiguration(zkServerNode, zkServerNamespace));
        zkRegistryCenter.init();

        new ScheduleJobBootstrap(zkRegistryCenter, jobName, configuration).schedule();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Objects.requireNonNull(this.ctx);
        Objects.requireNonNull(this.zookeeperRegistryCenter);
    }
}
