package com.example.jpa.interceptor;

import com.example.jpa.aop.UserCheck;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.example.jpa.control.common.exception.UserLoginException.userLoginException;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2017/11/30
 */
// 自定义一个权限拦截器, 继承HandlerInterceptorAdapter类
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    // 在调用方法之前执行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的UserCheck注解
        UserCheck userCheck = method.getAnnotation(UserCheck.class);
        if (userCheck == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        String userId = request.getSession().getAttribute("userId").toString();
        if(StringUtils.isNotBlank(userId)) return true;
        else throw userLoginException();
}

}
