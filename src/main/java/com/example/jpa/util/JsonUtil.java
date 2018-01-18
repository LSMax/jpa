package com.example.jpa.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description fastjson工具类
 **/
public class JsonUtil {

    /**
     * 将json转化成map
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> convertJsonStrToMap(String jsonStr){
        Map<String, Object> map = new HashMap();

        if(StringUtils.isNotBlank(jsonStr)){
            map = JSON.parseObject(jsonStr,new TypeReference<Map<String,Object>>(){});
        }

        return map;
    }

    /**
     * 将json转换成对象
     * @param jsonStr
     * @return
     */
    public static Object convertJsonToObject(String jsonStr,Class clazz){
        Object obj = null;
        if(StringUtils.isNotBlank(jsonStr)){
            obj = JSON.parseObject(jsonStr,clazz);
        }

        return obj;
    }
}
