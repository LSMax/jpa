package com.example.jpa.runable.init;

import com.example.jpa.runable.BlockingQueue.ConsumerPoll;
import com.example.jpa.runable.BlockingQueue.ConsumerTake;
import com.example.jpa.runable.BlockingQueue.Producer;
import com.example.jpa.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2018/1/10
 */
public class Init {

    public static void main(String[] args) throws InterruptedException {

        Long time = 3821967556L;
        System.out.println(DateUtil.formatTime(time));
        System.out.println(DateUtil.formatSecond(time));
//        LazyInitRace lazyInitRace = LazyInitRace.getInstance();
//
//        LazyInitRace lazyInitRace2 = LazyInitRace.getInstance();
//
//        System.out.println(lazyInitRace);
//        System.out.println(lazyInitRace2);
//
//        System.out.println(lazyInitRace == lazyInitRace2);
//
////         声明一个容量为10的缓存队列
//        BlockingQueue queue = new LinkedBlockingQueue(5);
//
//        Producer producer1 = new Producer(queue);
//        Producer producer2 = new Producer(queue);
////        Producer producer3 = new Producer(queue);
//        ConsumerTake consumerTake = new ConsumerTake(queue);
//
//        // 借助Executors
//        ExecutorService service = Executors.newCachedThreadPool();
////         启动线程
//        service.execute(producer1);
//        service.execute(producer2);
//        service.execute(consumerTake);
//
////         执行10s
//        Thread.sleep(10 * 1000);
//        producer1.stop();
//        producer2.stop();
////        producer3.stop();
//
//        Thread.sleep(2000);
////         退出Executor
//        service.shutdown();
    }
}
