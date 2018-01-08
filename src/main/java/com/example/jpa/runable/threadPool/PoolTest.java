package com.example.jpa.runable.threadPool;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2018/1/4
 */
public class PoolTest {

    public static void main(String[] args) throws InterruptedException {
    /**
     * LinkedBlockingQueue,
     * 默认无界（如果不定义大小，则会往队列里面一直放数据，不会出现堵塞的情况）
     *
     */
        BlockingQueue queue = new LinkedBlockingQueue(5);

        /**
         * corePoolSize:与启动线程保持一致
         * maximumPoolSize:最大线程数量
         * keepAliveTime:存活时间
         * unit:时间单位
         * workQueue:工作队列
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,5,60L, TimeUnit.MICROSECONDS,queue);

        for (int i = 0 ;i < 2;i++){
            Producer producer = new Producer(queue);
            executor.execute(producer);
        }

        Consumer consumer = new Consumer(queue);
        executor.execute(consumer);

        executor.shutdown();

//        System.out.println(Thread.currentThread().getThreadGroup());
//        System.out.println(System.getSecurityManager());
//        AccessControlContext context = AccessController.getContext();
//        System.out.println(context.toString());

    }
}
