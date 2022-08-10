package com.zuoshiyue.genshin.genshin_tool.config;

import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

import java.util.concurrent.*;

/**
 * @author lupengfei
 * @date 2022/8/10 20:15
 * @desc
 **/
public class GuavaCacheLoader extends CacheLoader<String, String> {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_CAPACITY),
            new ThreadPoolExecutor.CallerRunsPolicy());


    @Override
    public String load(String s) throws Exception {
        if (s.equals("hello")) {
            Thread.sleep(3000);
            return "world";
        } else if (s.equals("world")) {
            Thread.sleep(5000);
            return "hello";
        }
        return "no value";
    }

    @Override
    public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
        ListenableFutureTask<String> task = ListenableFutureTask.create(() -> {
            if (key.equals("hello")) {
                return "nihao";
            } else if (key.equals("world")) {
                return "shijie";
            }
            return "no value";
        });

        executor.submit(task);
        return task;
    }
}
