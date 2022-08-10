package com.zuoshiyue.genshin.genshin_tool.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author lupengfei
 * @date 2022/8/10 20:17
 * @desc
 **/
@Configuration
@PropertySource("classpath:guava.properties")
@ConfigurationProperties(prefix = "guava.cache.config")
public class GuavaProperties {
    private long maximumSize;

    private long maximumWeight;

    private long expireAfterWriteDuration;

    private long expireAfterAccessDuration;

    private long refreshDuration;

    private int initialCapacity;

    private int concurrencyLevel;

    public long getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(long maximumSize) {
        this.maximumSize = maximumSize;
    }

    public long getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(long maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    public long getExpireAfterWriteDuration() {
        return expireAfterWriteDuration;
    }

    public void setExpireAfterWriteDuration(long expireAfterWriteDuration) {
        this.expireAfterWriteDuration = expireAfterWriteDuration;
    }

    public long getExpireAfterAccessDuration() {
        return expireAfterAccessDuration;
    }

    public void setExpireAfterAccessDuration(long expireAfterAccessDuration) {
        this.expireAfterAccessDuration = expireAfterAccessDuration;
    }

    public long getRefreshDuration() {
        return refreshDuration;
    }

    public void setRefreshDuration(long refreshDuration) {
        this.refreshDuration = refreshDuration;
    }

    public int getInitialCapacity() {
        return initialCapacity;
    }

    public void setInitialCapacity(int initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    public int getConcurrencyLevel() {
        return concurrencyLevel;
    }

    public void setConcurrencyLevel(int concurrencyLevel) {
        this.concurrencyLevel = concurrencyLevel;
    }
}
