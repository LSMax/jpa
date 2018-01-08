package com.example.jpa.runable.threadPool;

import com.example.jpa.util.DateUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2018/1/4
 */
public class LockDemo{

    public static void main(String[] args) throws InterruptedException {

        ThreadOne threadOne = new ThreadOne();
        ThreadTwo threadTwo = new ThreadTwo();

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,5,60L,TimeUnit.MICROSECONDS,new LinkedBlockingQueue<>(5));
//
//        executor.execute(threadOne);
//        executor.execute(threadTwo);
//
//        executor.shutdown();

//        int COUNT_BITS = Integer.SIZE - 3;
//        int CAPACITY   = (1 << COUNT_BITS) - 1;
//
//        System.out.println((int)Math.pow(2D,29D));
//        System.out.println(CAPACITY);
//        System.out.println(0 << COUNT_BITS);


    }

}

/**
 * 线程1
 */
class ThreadOne implements Runnable{

    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            Long startTime = System.currentTimeMillis();
            lock.lock();
            try {
                System.out.println("线程1:" + Thread.currentThread().getName() + " acquired successfully!");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("线程1:" + Thread.currentThread().getName() + " done!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " unlock success");
            }
            Long endTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + ",花费时间："+ DateUtil.formatTime(endTime - startTime));
            break;
        }
    }
}

/**
 * 线程2
 */
class ThreadTwo implements Runnable{

    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        Long startTime = System.currentTimeMillis();
        while (!Thread.interrupted()) {
            lock.lock();
            try {
                System.out.println("线程2:" + Thread.currentThread().getName() + " acquired successfully!");
                TimeUnit.SECONDS.sleep(30);
                System.out.println("线程2:" + Thread.currentThread().getName() + " done!");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " unlock" + " unlock success");
            }
            Long endTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + ",花费时间："+ DateUtil.formatTime(endTime - startTime));
            break;
        }
    }
}