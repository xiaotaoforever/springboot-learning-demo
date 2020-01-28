package com.ins.learning.springbootlearningdemorabbitmq.mq.log;

/**
 * Created by administrator on 2018/1/10.
 */
public class LogManager {
    /*

    private static final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("oererationlog-%d").build();
    private static final ExecutorService singleThreadPool = new ThreadPoolExecutor(10, 20,
            1L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(10), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public LogManager() {
    }

    public static LogManager logManager = new LogManager();

    public static LogManager me() {
        return logManager;
    }

    public void executeLog(TimerTask task) {
        singleThreadPool.execute(task);
    }

    */


}
