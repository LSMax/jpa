package com.example.jpa.runable.init;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2018/1/10
 */
public class LazyInitRace {

    private static volatile LazyInitRace instance = null;

    public static LazyInitRace getInstance() {
        if (instance == null){
            return instance = new LazyInitRace();
        }
        return instance;
    }
}
