package com.example.jpa.aop;

import com.example.jpa.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.net.InetAddress;
import java.util.Date;

import static com.example.jpa.control.common.exception.UserLoginException.userLoginException;

/**
 *
 * @version
 * <pre>
 * Author    liusu
 * Version   1.0
 * Date      2017/11/29
 * 注解式AOP
 */
@Aspect
@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
/* target： AnnotateAop目标对象 */
public class AnnotateAop {
    public static final String REGEX_IP_ADDR = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

    private String startDate = null;        //专门用于存放开始时间
    /*切入点，pointCut*/
    /*
    * 注意：spring默认代理模式是interface
    *       所以这里采用的是UserService，采用UserServiceImpl会报创建bean失败
    * 解决：
    *       xml添加:<aop proxy-target-class="true"></aop>
    *       则为子类代理模式
    * */
    @Pointcut("execution (* com.example.jpa.control..*.*(..))")
    public void pointOfPenetration(){

    }

    /**
     * advice,建议点，方法层面增强
     * 可以进行账号校验等相关操作
     */
    @Before("pointOfPenetration() && @annotation(userCheck)")
//    @Order(50)
    public void pointBefore(UserCheck userCheck) throws Exception{
//        int duty = userCheck.duty();
        String hostAddress = InetAddress.getLocalHost().getHostAddress();    //获取操作用户的IP地址
        boolean isLegal = hostAddress.matches(REGEX_IP_ADDR);    //验证IP地址
        if(StringUtils.isEmpty(hostAddress) && !isLegal){
            //如果IP地址为空并且IP地址不合法
            System.err.println("=============警告：非法用户操作==============");
        }
        startDate = DateUtil.format(DateUtil.YEAR_SECOND,new Date());    //开始时间
        System.err.println(startDate+"【开始】==="+hostAddress+"用户开始操作");
    }

//    @After("pointOfPenetration() && @annotation(UserCheck)")
//    public void pointAfter()  throws Exception{
//        String hostAddress = InetAddress.getLocalHost().getHostAddress();    //获取操作用户的IP地址
//        boolean isLegal = hostAddress.matches(REGEX_IP_ADDR);    //验证IP地址
//        if(StringUtils.isEmpty(hostAddress) && !isLegal){
//            //如果IP地址为空并且IP地址不合法
//            System.err.println("=============警告：非法用户操作==============");
//        }
//        String endDate = DateUtil.format(DateUtil.YEAR_SECOND,new Date());     //结束时间
//        System.err.println(endDate+"【终止】==="+hostAddress+"用户结束操作");
////        long startTime  = startDate.getTime();
////        long endTime = endDate.getTime();
//        long time = DateUtil.daysBetween(startDate,endDate);
//        System.err.println("================【共计耗时：" + time+ "】=====================");
//    }
}
