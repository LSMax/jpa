package com.example.jpa.runable.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2018/1/9
 */
public class CacheHealthChecker extends BaseHealthChecker  {

    public CacheHealthChecker (CountDownLatch latch)  {
        super("Cache Service", latch);
    }

    @Override
    public void verifyService() throws Exception {
        System.out.println("Checking " + this.getServiceName());
//        if(true) throw new Exception("测试异常");
        try
        {
            Thread.sleep(7000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
