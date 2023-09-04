package com.gus.demobasic.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gus.demobasic.dao.UserDao;
import com.gus.demobasic.model.User;

@Service
public class UserService {
  private UserDao userDao;

  @Autowired
  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public int deleteUserByUserUid(UUID userUid) {
    return userDao.deleteUserByUserUid(userUid);
  }

  public int insertUser(User user) {
    UUID userUid = user.getUserUid() == null ? UUID.randomUUID() : user.getUserUid();
    return userDao.insertUser(userUid, user);
  }

  public List<User> selectAllUsers() {
    return userDao.selectAllUsers();
  }

  public int updateUser(User user) {
    Optional<User> res = selectUserByUserUid(user.getUserUid());
    if (res.isPresent()) {
      return userDao.updateUser(user);
    }
    return -1;
  }

  // Optional을 잘 사용하면 null 처리를 간편하게 할 수 있음
  public Optional<User> selectUserByUserUid(UUID userUid) {
    return userDao.selectUserByUserUid(userUid);
  }
}
