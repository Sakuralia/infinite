package io.adana.infinite.common.job;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ywb
 * @version 1.1
 * @date 2021-02-04 09:21
 * @description ElasticJobProperties
 * @Scope
 */
@ConfigurationProperties(prefix = "elastic.job")
@Component
public class ElasticJobProperties<T> {

    /**
     * ZooKeeper server IP list
     * Include IP and port, such as: ZkNode1:2181,ZkNode2:2181
     */
    private String zkServerNode;

    /**
     * ZooKeeper namespace
     */
    private String zkServerNamespace;

    /**
     * The initial value of milliseconds for the retry interval
     */
    private int baseSleepTimeMills = 5000;

    /**
     * The maximum value of milliseconds for the retry interval
     */
    private int maxSleepTimeMills = 5000;

    /**
     * Maximum number of retries
     */
    private int maxRetries = 5;

    /**
     * Session timeout in milliseconds
     */
    private int sessionTimeoutMills = 60000;

    /**
     * Connection timeout in milliseconds
     */
    private int connectTimeoutMills = 15000;

    /**
     * Distributed Listener Configuration
     * <p>
     * The timeout in milliseconds before the last job is executed
     * </p>
     */
    private long startedTimeoutMills = 60000;

    /**
     * Distributed Listener Configuration
     * <p>
     * The timeout in milliseconds after the last job is executed
     * </p>
     */
    private long completedTimeoutMills = 60000;

    /**
     * Permission token to connect to ZooKeeper
     * <b>
     * no need
     * </b>
     */
    private String digest;

    /**
     * Event Tracing Configuration
     * <p>
     * The type of event tracing storage adapter
     * </p>
     */
    private String type;

    /**
     * Event Tracing Configuration
     * <p>
     * The object of event tracing storage adapter
     * </p>
     */
    private T storage;

    public String getZkServerNode() {
        return zkServerNode;
    }

    public void setZkServerNode(String zkServerNode) {
        this.zkServerNode = zkServerNode;
    }

    public String getZkServerNamespace() {
        return zkServerNamespace;
    }

    public void setZkServerNamespace(String zkServerNamespace) {
        this.zkServerNamespace = zkServerNamespace;
    }

    public int getBaseSleepTimeMills() {
        return baseSleepTimeMills;
    }

    public void setBaseSleepTimeMills(int baseSleepTimeMills) {
        this.baseSleepTimeMills = baseSleepTimeMills;
    }

    public int getMaxSleepTimeMills() {
        return maxSleepTimeMills;
    }

    public void setMaxSleepTimeMills(int maxSleepTimeMills) {
        this.maxSleepTimeMills = maxSleepTimeMills;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getSessionTimeoutMills() {
        return sessionTimeoutMills;
    }

    public void setSessionTimeoutMills(int sessionTimeoutMills) {
        this.sessionTimeoutMills = sessionTimeoutMills;
    }

    public int getConnectTimeoutMills() {
        return connectTimeoutMills;
    }

    public void setConnectTimeoutMills(int connectTimeoutMills) {
        this.connectTimeoutMills = connectTimeoutMills;
    }

    public long getStartedTimeoutMills() {
        return startedTimeoutMills;
    }

    public void setStartedTimeoutMills(long startedTimeoutMills) {
        this.startedTimeoutMills = startedTimeoutMills;
    }

    public long getCompletedTimeoutMills() {
        return completedTimeoutMills;
    }

    public void setCompletedTimeoutMills(long completedTimeoutMills) {
        this.completedTimeoutMills = completedTimeoutMills;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getStorage() {
        return storage;
    }

    public void setStorage(T storage) {
        this.storage = storage;
    }
}
