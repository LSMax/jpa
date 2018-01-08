package com.example.jpa.control.common;

import com.example.jpa.cache.RedisService;
import com.example.jpa.cache.impl.RedisCache;
import com.example.jpa.dao.UserRepository;
import com.example.jpa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2017/12/7
 */
@Component
/*
    CommandLineRunner:项目启动则加载
*/
public class Runner implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(Runner.class);
    @Autowired
    private RedisService redisService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void run(String... strings) throws Exception {
        logger.info("--------------加载用户缓存--------------------");
        redisService.initUser();
//        redisService.initUserTen();
    }
}
