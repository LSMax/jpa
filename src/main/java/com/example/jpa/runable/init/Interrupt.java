package com.example.jpa.runable.init;

import com.example.jpa.util.DateUtil;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2018/1/10
 */
public class Interrupt implements Runnable {

    @Override
    public void run() {
        try{
            for (int i = 0; i < 10; i++) {
                if(i == 5){
                    System.out.println("should be stopped and exit");
                    Thread.currentThread().interrupt();
//                    Thread.interrupted();
                }

                if (Thread.currentThread().isInterrupted()){
                    System.out.println("already interrupt.....");
                    return;
                }
                System.out.println("i=" + i);
//                Thread.sleep(1000);
            }
            System.out.println("this line cannot be executed. cause thread throws exception");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        Interrupt interrupt = new Interrupt();
//        interrupt.run();

        String str = "{0}已将该充电工单撤回{1}";
        String name = "max";

        System.out.println(MessageFormat.format(str, name,"just now"));

        String phone = "15700353256";
        HashSet<String> phoneSet = new HashSet<>();
        phoneSet.add(phone);
        phoneSet.add("15700353242");

        Long startTime = System.currentTimeMillis();
        System.out.println(parsePhone(phoneSet));
//        System.out.println(getPhone(phoneSet));

        Long endTime = System.currentTimeMillis();
        System.out.println(DateUtil.formatTime(endTime - startTime));
        System.out.println(endTime - startTime);
    }

    public static String parsePhone(Set<String> phoneSet){
        if(phoneSet!=null && phoneSet.size() > 0){
            StringBuilder builder = new StringBuilder();
            for (String phone : phoneSet) {
                builder.append(phone);
                builder.append(",");
            }

            return builder.toString().substring(0,builder.toString().length() - 1);
        }else{
            return null;
        }
    }

    public static String getPhone(Set<String> phoneSet) {
        if (phoneSet!=null && phoneSet.size() > 0) {
            Iterator<String> iter = phoneSet.iterator();
            StringBuilder builder = new StringBuilder();
            String split = ",";
            while (iter.hasNext()) {
                builder.append(iter.next());
                builder.append(split);
            }
            return builder.substring(0, builder.length() - 1);
        } else {
            return null;
        }
    }
}
