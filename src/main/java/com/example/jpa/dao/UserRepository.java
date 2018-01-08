package com.example.jpa.dao;

import com.example.jpa.model.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long>{
//    @Lock(LockModeType.READ)
    List<UserEntity> findById(String id);

    Optional<UserEntity> findByCityCodeAndId(String cityCode,String id);

    List<UserEntity> findByMobile(String mobile);

    @Query("select u from UserEntity u where name like concat('%',?1,'%')")
    List<UserEntity> findByNameLike(String name);

    List<UserEntity> findTop10ByDuty(int duty);
}
