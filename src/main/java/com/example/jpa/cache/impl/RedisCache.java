package com.example.jpa.cache.impl;

import com.example.jpa.cache.RedisConn;
import com.example.jpa.util.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import redis.clients.util.JedisByteHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2017/12/6
 */
@Component
@EnableCaching
public class RedisCache<K,V> {

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
    @Autowired
    private RedisConn redisConn;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValueOperations operations;
    //用于分布式，集群处理
    private ShardedJedisPool shardedJedisPool;
    private JedisPool jedisPool;


    public Jedis getResource(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConn.getMaxIdle());
        poolConfig.setMinIdle(redisConn.getMinIdle());
        jedisPool = new JedisPool(poolConfig,redisConn.getHost(),redisConn.getPort(),redisConn.getTimeout(),null,redisConn.getDatabase());
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    public ShardedJedis getShardedResource(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConn.getMaxIdle());
        poolConfig.setMinIdle(redisConn.getMinIdle());
        List<JedisShardInfo> shards = new ArrayList<>();
        //weight:权值，权值越大，出现概率越大
        JedisShardInfo info = new JedisShardInfo(redisConn.getHost(),redisConn.getPort());
        JedisShardInfo info2 = new JedisShardInfo(redisConn.getHost(),6380);
        JedisShardInfo info3 = new JedisShardInfo(redisConn.getHost(),"pd_dispacher",6381,redisConn.getTimeout(),1);
        JedisShardInfo info4 = new JedisShardInfo(redisConn.getHost(),"pd_dispacher",6382,redisConn.getTimeout(),1);
        JedisShardInfo info5 = new JedisShardInfo(redisConn.getHost(),"pd_dispacher",6383,redisConn.getTimeout(),1);
        JedisShardInfo info6 = new JedisShardInfo(redisConn.getHost(),"pd_dispacher",6384,redisConn.getTimeout(),1);
        shards.add(info);
//        shards.add(info2);
//        shards.add(info3);
//        shards.add(info4);
//        shards.add(info5);
//        shards.add(info6);
        shardedJedisPool = new ShardedJedisPool(poolConfig,shards);
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        return shardedJedis;
    }


    public boolean existKey(String key){
        ShardedJedis shardedJedis = null;
        Boolean flag = false;
        try{
            shardedJedis = getShardedResource();
            logger.info(String.valueOf(shardedJedis.getShard(key).getClient().getPort()));
            flag = shardedJedis.exists(key);
        }catch (Exception e){
                e.printStackTrace();
        }finally {
            shardedJedis.close();
        }
        return flag;
    }

    public String setV(String key,Object value){
        ShardedJedis shardedJedis = null;
        String jedisResult = "";
        try{
            shardedJedis = getShardedResource();
            jedisResult = shardedJedis.set(key.getBytes(), SerializeUtil.serialize(value));
        }catch (Exception e){

        }finally {
            shardedJedis.close();
        }
        return jedisResult;
    }


    public void set(String key,String value,int seconds){
        ShardedJedis shardedJedis = null;
        try{
            shardedJedis = getShardedResource();
            shardedJedis.setex(key, seconds,value);
        }catch (Exception e){
            logger.info("RedisCache.setV()发生异常："+e);
            e.printStackTrace();
        }finally {
            if(shardedJedis != null )shardedJedis.close();
        }
    }

    public String get(String key){
        ShardedJedis shardedJedis = null;
        String result = null;
        try{
            shardedJedis = getShardedResource();
            result = shardedJedis.get(key);
        }catch (Exception e){
            logger.info("RedisCache.get()发生异常："+e);
            e.printStackTrace();
        }finally {
            if(shardedJedis != null )shardedJedis.close();
        }
        return result;
    }

    /**
     * 设置过期时间
     * @param key
     * @param value
     * @param seconds
     */
    public String setV(String key,Object value,int seconds){
        ShardedJedis shardedJedis = null;
        String jedisResult = "";
        try{
            shardedJedis = getShardedResource();
            logger.info(String.valueOf(shardedJedis.getShard(key).getClient().getPort()));
            jedisResult = shardedJedis.setex(key.getBytes(), seconds,SerializeUtil.serialize(value));
//            jedisResult = shardedJedis.set(key.getBytes(), SerializeUtil.serialize(value));
//            shardedJedis.expire(key,seconds);
        }catch (Exception e){
            logger.info("RedisCache.setV()发生异常："+e);
            e.printStackTrace();
        }finally {
            shardedJedis.close();
        }
        return jedisResult;
    }

    public V getV(String key){
        ShardedJedis shardedJedis = null;
        V jedisResult = null;
        try{
            shardedJedis = getShardedResource();

            logger.info(String.valueOf(shardedJedis.getShard(key).getClient().getPort()));
            jedisResult = (V)SerializeUtil.unserialize(shardedJedis.get(key.getBytes()));
        }catch (Exception e){
            logger.info("RedisCache.getV()发生异常："+e);
        }finally {
            shardedJedis.close();
        }
        return jedisResult;
    }

    /**
     * list缓存
     * @param key
     * @param value
     * @return
     */
    public Long rpush(String key,List<V> value,int seconds){
        ShardedJedis shardedJedis = null;
        Long jedisResult = null;
        try{
            String[] arr = new String[value.size()];
//            value.toArray(arr);
            arr = (String[]) value.toArray();
            shardedJedis = getShardedResource();
            jedisResult = shardedJedis.rpush(key,arr);
            shardedJedis.expire(key,seconds);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("----------RedisCache.rpush()发生异常-----------");
        }finally {
            shardedJedis.close();
        }
        return jedisResult;
    }

    public List<V> lrange(String key){
        List<V> list = null;
        ShardedJedis shardedJedis = null;
        try{
            list = (List<V>) shardedJedis.lrange(key,0,-1);
        }catch (Exception e){
            logger.info("-----------RedisCache.lrange发生异常--------------");
        }finally {
            shardedJedis.close();
        }
        return list;
    }

    public Long del(String key){
        ShardedJedis shardedJedis = null;
        Long flag = null;
        try{
            shardedJedis = getShardedResource();
            flag = shardedJedis.del(key);
        }catch (Exception e){
            logger.info("RedisCache.del()发生异常："+e);
            e.printStackTrace();
        }finally {
            shardedJedis.close();
        }
        return flag;
    }

    /**
     *
     * @param key      hash表，可重复
     * @param field    键，不可重复
     * @param value    值，可重复
     */
    public void hset(String key, String field, String value){
        ShardedJedis shardedJedis = null;
        try{
            shardedJedis = getShardedResource();
            shardedJedis.hset(key,field,value);
//            shardedJedis.expire(key,seconds);
        }catch (Exception e){
            logger.info("RedisCache.hset()发生异常："+e);
            e.printStackTrace();
        }finally {
            shardedJedis.close();
        }
    }

    /**
     *
     * @param key hash表
     * @param field 键
     * @return
     */
    public String hget(String key,String field){
        ShardedJedis shardedJedis = null;
        String jedisResult = null;
        try{
            shardedJedis = getShardedResource();
            jedisResult = shardedJedis.hget(key,field);
        }catch (Exception e){
            logger.info("RedisCache.hget()发生异常："+e);
            e.printStackTrace();
        }finally {
            shardedJedis.close();
        }
        return jedisResult;
    }

    public Boolean hexists(String key,String field){
        ShardedJedis shardedJedis = null;
        Boolean flag = false;
        try{
            shardedJedis = getShardedResource();
            flag = shardedJedis.hexists(key,field);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(shardedJedis!=null) shardedJedis.close();
        }
        return  flag;
    }

    public void hdel(String key, String... fields){
        ShardedJedis shardedJedis = null;
        try{
            shardedJedis  = getShardedResource();
            shardedJedis.hdel(key,fields);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (shardedJedis != null) shardedJedis.close();
        }
    }

    @Bean
    public ValueOperations<String, Object> valueOperations() {
        return redisTemplate.opsForValue();
    }

    public void setO(String key, Object value){
        operations.set(key,value);
    }

    public Object getO(Object key){
        getResource();
        return operations.get(key);
    }

    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

}
