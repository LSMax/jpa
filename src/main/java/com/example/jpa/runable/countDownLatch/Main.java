package com.example.jpa.runable.countDownLatch;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2018/1/9
 */
public class Main {
    public static void main(String[] args)
    {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
    }
}