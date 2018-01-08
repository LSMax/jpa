package com.example.jpa.control.common.exception;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2017/12/1
 */
public class UserLoginException extends RuntimeException{

    private UserLoginException(){

    }

    public static UserLoginException userLoginException(){
        return new UserLoginException();
    }
}
