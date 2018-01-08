package com.example.jpa.cache.impl;

import com.alibaba.fastjson.JSON;
import com.example.jpa.cache.RedisService;

import com.example.jpa.dao.UserRepository;
import com.example.jpa.model.UserEntity;
import com.example.jpa.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2017/12/7
 */
@Component
public class RedisServiceImpl implements RedisService {

    private final static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserRepository userRepository;
    private final String preUser = "USER";
    private final String preUserTen = "USER_TEN";
    private final String preUserField = "USER_FIELD";

    @Override
    public void initUser(){
        List<UserEntity> userEntities = null;
//        redisCache.del(preUser);
        if(redisCache.existKey(preUser)){
            logger.info("-----------读取用户缓存数据------------");
            userEntities = (List<UserEntity>)redisCache.getV(preUser);
            logger.info("-----------读取用户缓存结束------------");
        }else{
            logger.info("--------------写入用户缓存数据-------------");
            userEntities = (List<UserEntity>)userRepository.findAll();
            //seconds 单位秒
            redisCache.setV(preUser,new ArrayList<>(userEntities.subList(0,9)),60*60);
//            redisCache.set(preUser,JSON.toJSONString(userEntities.subList(0,9)),5);
            logger.info("--------------写入用户缓存结束-------------");
        }
    }

    @Override
    public void initUserTen(){
        UserEntity userTen = null;
//        redisCache.hdel(preUserTen,preUserField);
        if(redisCache.hexists(preUserTen,preUserField)){
            redisCache.hget(preUserTen,preUserField);
            logger.info("-----------读取用户缓存结束(10)------------");
        }else{
            userTen = userRepository.findTop10ByDuty(0).get(0);
            redisCache.hset(preUserTen,preUserField,JSON.toJSONString(userTen));
            logger.info("-----------写入用户缓存结束(10)-------------");
        }
    }
}
