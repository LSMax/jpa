package com.example.jpa.control.common.exception;

/**
 * @version <pre>
 * Author    liusu
 * Version   1.0
 * Date      2017/11/30
 */
public class UserPassWordErrorException extends RuntimeException {
    private final String mobile;

    private UserPassWordErrorException(String mobile){
        this.mobile = mobile;
    }

    public static UserPassWordErrorException userPassWordErrorException(String mobile){
        return new UserPassWordErrorException(mobile);
    }
}
