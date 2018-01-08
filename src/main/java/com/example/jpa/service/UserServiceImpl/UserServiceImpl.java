package com.example.jpa.service.UserServiceImpl;

import com.example.jpa.cache.RedisService;
import com.example.jpa.cache.impl.RedisCache;
import com.example.jpa.cache.RedisConn;
import com.example.jpa.control.dto.UserDto;
import com.example.jpa.dao.UserRepository;
import com.example.jpa.model.UserEntity;
import com.example.jpa.service.UserService;
import com.example.jpa.util.DateUtil;
import com.example.jpa.util.EncryptUtil;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.example.jpa.control.common.exception.UserNotExistException.userNotExistException;
import static com.example.jpa.control.common.exception.UserPassWordErrorException.userPassWordErrorException;

@Service
public class UserServiceImpl  implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    protected EntityManager em;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private RedisConn redisConn;
    @Autowired
    private RedisService redisService;


    @Override
    public List<UserEntity> allUser(String id){
        String sql = "select u from UserEntity u where u.id="+id;
        Session session =  em.unwrap(Session.class);
        Query query = session.createQuery(sql);
        List<UserEntity> entity = query.list();
        return query.list();
    }

    @Override
    public UserEntity findById(String id){
        List<UserEntity> entities = userRepository.findById(id);
        return entities.get(0);
    }

    @Override
    public UserDto login(String phone,String password){
        Boolean validate = false;
        List<UserEntity> entities = userRepository.findByMobile(phone);
        Optional<UserEntity> users = entities
                .stream()
                .filter(line-> line.getMobile() != null)
//                .sorted(Comparator.comparing(ContractLine::getTonnage).reversed())
                .findFirst();

        if(CollectionUtils.isEmpty(entities)) throw userNotExistException(1L);
        UserEntity userEntity = entities.get(0);
        validate = EncryptUtil.validatePassword(password, userEntity.getPassword());
        if(!validate) throw userPassWordErrorException(password);

        UserDto dto = new UserDto();
        BeanUtils.copyProperties(userEntity,dto);
        dto.setCreateTime(DateUtil.format(DateUtil.YEAR_SECOND,userEntity.getCreateTime()));
        dto.setCreateTime(DateUtil.format(DateUtil.YEAR_SECOND,userEntity.getUpdateTime()));


        return dto;
    }

    @Override
    public UserEntity findByCityCodeAndId(String cityCode,String id){
        Optional<UserEntity> userEntity = userRepository.findByCityCodeAndId(cityCode,id);
        return userEntity.get();
    }

    @Override
    public List<UserEntity> searchUser(String keyWords){
//        List<UserEntity> userEntities = null;
//        if(redisCache.existKey("userInfo")){
//            userEntities = (List<UserEntity>) redisCache.getV("userInfo");
//            logger.info("UserServiceImpl.searchUser(),从缓存获取数据:"+userEntities);
//            return userEntities;
//        }else{
//            userEntities = userRepository.findByNameLike(keyWords);
//            redisCache.setV("userInfo", userEntities);
//            logger.info("UserServiceImpl.searchUser(),从数据库读出并将数据插入缓存:" + userEntities);
//            return userEntities;
//        }
        return null;
    }
}
