package com.example.jpa.runable.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2018/1/9
 */
public class DatabaseHealthChecker  extends BaseHealthChecker  {

    public DatabaseHealthChecker (CountDownLatch latch)  {
        super("DataBase Service", latch);
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
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
