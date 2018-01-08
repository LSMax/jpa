package com.example.jpa.runable.BlockingQueue;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2017/12/14
 */


import java.util.concurrent.*;

/**
 * 测试类
 */
public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        // 声明一个容量为10的缓存队列
        BlockingQueue queue = new LinkedBlockingQueue(10);

        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        // 借助Executors
        ExecutorService service = Executors.newCachedThreadPool();
//         启动线程
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer);

//         执行10s
        Thread.sleep(10 * 1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();

        Thread.sleep(2000);
//         退出Executor
        service.shutdown();

    }
}