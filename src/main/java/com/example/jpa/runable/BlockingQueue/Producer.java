package com.example.jpa.runable.BlockingQueue;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2017/12/14
 */

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者线程
 *
 *
 */
public class Producer implements Runnable {

    /* volatile
     * 共享变量，相当于 synchronized
     * 保证线程间的可见性
     */
    private volatile boolean  isRunning = true;
    private BlockingQueue queue;
    private static AtomicInteger count = new AtomicInteger();
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        String data = null;
        Random r = new Random();

        System.out.println("启动生产者线程！" + Thread.currentThread().getName());
        try {
            System.out.println("线程分组" + Thread.currentThread().getThreadGroup());
            while (isRunning) {
                System.out.println("正在生产数据..." + Thread.currentThread().getName());
                Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));

                data = "data:" + count.incrementAndGet();
                System.out.println("将数据：" + data + "放入队列...");
                //设置放入数据时间
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("放入数据失败：" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出生产者线程！");
        }
    }

    public void stop() {
        isRunning = false;
    }
}