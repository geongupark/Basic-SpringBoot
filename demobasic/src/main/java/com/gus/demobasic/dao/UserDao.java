package com.gus.demobasic.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gus.demobasic.model.User;

public interface UserDao {
    // 보통 한 model에 대해 이렇게 5개의 Interface를 구성
    List<User> selectAllUsers();

    Optional<User> selectUserByUserUid(UUID userUid);

    int updateUser(User user);

    int deleteUserByUserUid(UUID userUid);

    int insertUser(UUID userUid, User user);
}
