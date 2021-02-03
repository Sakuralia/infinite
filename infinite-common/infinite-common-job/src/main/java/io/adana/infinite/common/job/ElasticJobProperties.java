package io.adana.infinite.common.job;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ywb
 * @version 1.1
 * @date ${DATE} ${TIME}
 * @description ElasticJobProperties
 * @Scope
 */
@ConfigurationProperties(prefix = "elastic.job")
public class ElasticJobProperties {

    /**
     * the server list of zookeeper
     */
    private String zkServerNode;

    /**
     * the zookeeper server's namespace.
     */
    private String zkServerNamespace;

    private int baseSleepTimeMills = 5000;

    private int maxSleepTimeMills = 5000;

    private int maxRetries = 20;

    private int sessionTimeoutMills = 300000;

    private int connectTimeoutMills = 5000;


}
