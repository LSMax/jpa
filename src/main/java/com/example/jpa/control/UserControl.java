package com.example.jpa.control;

import com.alibaba.fastjson.JSON;
import com.example.jpa.aop.UserCheck;
import com.example.jpa.cache.RedisService;
import com.example.jpa.cache.impl.RedisCache;
import com.example.jpa.cache.impl.RedisServiceImpl;
import com.example.jpa.control.dto.UserDto;
import com.example.jpa.dao.UserRepository;
import com.example.jpa.model.UserEntity;
import com.example.jpa.service.UserService;
import com.example.jpa.util.DateUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

@RestController
@RequestMapping("/api"+"/user")
public class UserControl {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisCache redisCache;

    private final String preUser = "USER";
    private final String preUserTen = "USER_TEN";
    private final String preUserField = "USER_FIELD";

    /*
    sql注入;
        不安全性
     mybatis：
        xml：采用#{}
        param形式传参
     hibernate:
        param形式传参
    */
    @RequestMapping(value="/injection",method = RequestMethod.GET)
    public List<UserEntity> allUser(@Param("id") String id){
        System.out.println("-----------------");
        List<UserEntity> userEntities = userService.allUser(id);
        return userEntities;
    }

    @RequestMapping(value="/find",method = RequestMethod.GET)
//    @UserCheck()
    public UserDto findUser(@Param("id") String id){
        //008cf3b9a29211e7b5f26c92bf13bd36
        UserEntity entity = userService.findById(id);
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(entity,dto);
        dto.setCreateTime(DateUtil.format(DateUtil.YEAR_SECOND,entity.getCreateTime()));
        dto.setCreateTime(DateUtil.format(DateUtil.YEAR_SECOND,entity.getUpdateTime()));
        return  dto;
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public UserDto login(HttpServletRequest request, @Param("phone")String phone, @Param("password") String password){
        UserDto dto = userService.login(phone,password);
        request.getSession().setAttribute("userId",dto.getId());
        return dto;
    }

    @RequestMapping(value="/interceptor",method = RequestMethod.GET)
    @UserCheck
    public String interceptor(){
        return "interceptor";
    }

    @RequestMapping(value = "/findCityCode")
    public UserDto findCityCode(@Param("cityCode") String cityCode,@Param("id")String id){
        UserEntity entity = userService.findByCityCodeAndId(cityCode,id);
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(entity,dto);
        dto.setCreateTime(DateUtil.format(DateUtil.YEAR_SECOND,entity.getCreateTime()));
        dto.setCreateTime(DateUtil.format(DateUtil.YEAR_SECOND,entity.getUpdateTime()));
        return  dto;
    }

    /**
     * 反射
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/reflection")
    public String classReflection() throws Exception{
        Class clazz = Class.forName("com.example.jpa.control.dto.UserDto");
        Method method = clazz.getMethod("setName",new Class[]{String.class});
        UserDto dto = (UserDto) clazz.newInstance();
        method.invoke(dto,"caroline");
        System.out.println(dto.toString());
        return "";
    }

    @RequestMapping(value = "/findName",method = RequestMethod.GET)
    public UserEntity findByName(@Param("name") String name){
        return userService.searchUser(name).get(0);
    }

    @RequestMapping(value = "/redis",method = RequestMethod.GET)
    public UserEntity redis(@Param("name") String name){
        List<UserEntity> users = (List<UserEntity>) redisCache.getV(preUser);
        String jsonValue = redisCache.hget(preUserTen,preUserField);
        UserEntity userEntity = JSON.parseObject(jsonValue,UserEntity.class);
        return userEntity;
    }
}
