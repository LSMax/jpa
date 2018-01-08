package com.example.jpa.control.common;

import com.example.jpa.control.common.exception.UserLoginException;
import com.example.jpa.control.common.exception.UserNotExistException;
import com.example.jpa.control.common.exception.UserPassWordErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


//@ControllerAdvice & @ResponseBody
@RestControllerAdvice
public class ExceptionMapping {

	private Logger logger = LoggerFactory.getLogger(ExceptionMapping.class);
	
//	@ExceptionHandler
//	public ApplicationError runtimeException(RuntimeException e){
//		e.printStackTrace();
//		return applicationError("运行异常", "请联系管理员");
//	}

	//400的异常会被这个方法捕获
	@ExceptionHandler(UserNotExistException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApplicationError userNotExistException(UserNotExistException e){
		return applicationError("user_not_exist_error", "用户不存在");
	}

	@ExceptionHandler(UserPassWordErrorException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApplicationError userPassWordErrorException(UserPassWordErrorException e){
		return applicationError("user_password_error", "密码错误");
	}

	@ExceptionHandler(UserLoginException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApplicationError userLoginException(UserLoginException e){
		return applicationError("user_login_error", "登录失效");
	}

	private ApplicationError applicationError(String message,Object data){
		return new ApplicationError(message, data);
	}
}
