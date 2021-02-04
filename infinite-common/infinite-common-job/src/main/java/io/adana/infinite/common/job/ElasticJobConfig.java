package io.adana.infinite.common.job;


import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;

/**
 * @author ywb
 * @version 1.1
 * @date ${DATE} ${TIME}
 * @description ElasticJobConfig
 * @Scope
 */
@ComponentScan(basePackageClasses = ElasticJobConfig.class)
@Configuration
public class ElasticJobConfig implements InitializingBean {

    private ElasticJobProperties jobProperties;

    private ApplicationContext ctx;

    public ElasticJobConfig(ElasticJobProperties jobProperties, ApplicationContext ctx) {
        this.ctx = ctx;
        this.jobProperties = jobProperties;
    }
    @Bean
    public ZookeeperRegistryCenter zookeeperRegistryCenter() {
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(jobProperties.getZkServerNode(), jobProperties.getZkServerNamespace());
        zkConfig.setBaseSleepTimeMilliseconds(jobProperties.getBaseSleepTimeMills());
        zkConfig.setMaxRetries(jobProperties.getMaxRetries());
        zkConfig.setDigest(jobProperties.getDigest());
        zkConfig.setConnectionTimeoutMilliseconds(jobProperties.getConnectTimeoutMills());
        zkConfig.setSessionTimeoutMilliseconds(jobProperties.getSessionTimeoutMills());
        ZookeeperRegistryCenter zkCenter = new ZookeeperRegistryCenter(zkConfig);
        zkCenter.init();
        return zkCenter;
    }

    @Bean
    public ElasticJobService elasticJobService() {
        return new ElasticJobService(zookeeperRegistryCenter(), ctx);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Objects.requireNonNull(jobProperties.getZkServerNode(), "ZooKeeper server IP list can not be null.");
        Objects.requireNonNull(jobProperties.getZkServerNamespace(), "ZooKeeper namespace can not be null.");
        Map<String, SimpleJob> simpleJobMap = ctx.getBeansOfType(SimpleJob.class);
        for (Map.Entry<String, SimpleJob> jobEntry : simpleJobMap.entrySet()) {
            SimpleJob job = jobEntry.getValue();
            InfiniteElasticJob annotation = job.getClass().getAnnotation(InfiniteElasticJob.class);
            if (Objects.nonNull(annotation)) {
                String jobName = StringUtils.defaultIfBlank(annotation.jobName(), job.getClass().getName());
                elasticJobService().createJob(job, jobName, annotation.cron(), annotation.shardingTotalCount(),
                        annotation.shardingItemParameters(), annotation.misfire(), annotation.failover(),
                        annotation.jobParameter(), annotation.description(), annotation.monitorExecution(),
                        annotation.disabled(), annotation.overwrite(), annotation.jobShardingStrategyType(),
                        annotation.jobExecutorServiceHandlerType(), annotation.jobErrorHandlerType(),
                        annotation.maxTimeDiffSeconds(), annotation.reconcileIntervalMinutes(),
                        jobProperties.getZkServerNode(), jobProperties.getZkServerNamespace());
            }
        }
    }
}
