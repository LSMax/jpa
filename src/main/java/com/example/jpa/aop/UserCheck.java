package com.example.jpa.aop;

import java.lang.annotation.*;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2017/11/29
 */
/*
* 自定义注解
* @Target 说明了Annotation所修饰的对象范围
* @Retention定义了该Annotation被保留的时间长短
* @Documented用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员
* @Inherited 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类
* */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserCheck {
//    String[] value() default {};
//
//    String[] authorities() default {};
//
//    String[] roles() default {};
}
