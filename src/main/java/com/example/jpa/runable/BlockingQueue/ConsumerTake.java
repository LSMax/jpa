package com.example.jpa.runable.BlockingQueue;

import com.example.jpa.util.DateUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2018/1/10
 */
public class ConsumerTake implements Runnable {

    private BlockingQueue<String> queue;
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public ConsumerTake(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        Long startTime = System.currentTimeMillis();
        System.out.println("启动消费者线程！" + Thread.currentThread().getName());
//        Random r = new Random();
        boolean isRunning = true;
        try {
            while (isRunning) {
                System.out.println("正从队列获取数据..." + Thread.currentThread().getName());
                /**
                 * take:若数据不存在，则会一直阻塞，直到有数据
                 */
                String data = queue.take();
                if (StringUtils.isNotBlank(data)) {
                    System.out.println("拿到数据：" + data);
                    System.out.println("正在消费数据：" + data);
                    Thread.sleep(1000);
                } else {
                    // 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
                    isRunning = false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            Long endTime = System.currentTimeMillis();
            System.out.println("退出消费者线程！花费时间：" + DateUtil.formatTime(endTime - startTime));
        }
    }
}
