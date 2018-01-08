package com.example.jpa.control.common.exception;

public class UserNotExistException extends RuntimeException{
	
	private final Long id;

	private UserNotExistException(Long id){
		this.id = id;
	}

	public static UserNotExistException userNotExistException(Long id){
		return new UserNotExistException(id);
	}
}
