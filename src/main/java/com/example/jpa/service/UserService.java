package com.example.jpa.service;

import com.example.jpa.control.dto.UserDto;
import com.example.jpa.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserEntity> allUser(String id);

    public UserEntity findById(String id);

    public UserDto login(String phone, String password);

    public UserEntity findByCityCodeAndId(String cityCode,String id);

    public List<UserEntity> searchUser(String keyWords);
}
