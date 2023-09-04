package com.gus.demobasic.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.gus.demobasic.model.User;
import com.gus.demobasic.model.User.Gender;

@Repository
public class FakeDataDao implements UserDao {
  private static Map<UUID, User> database;
  static {
    // Initialize the database
    database = new HashMap<>();
    UUID userUid = UUID.randomUUID();
    database.put(userUid, new User(userUid, "gus", "park", Gender.MALE, 30, "gus@samsung.com"));
  }

  @Override
  public int deleteUserByUserUid(UUID userUid) {
    database.remove(userUid);
    return 1;
  }

  @Override
  public int insertUser(UUID userUid, User user) {
    database.put(userUid, user);
    return 1;
  }

  @Override
  public List<User> selectAllUsers() {
    return new ArrayList<>(database.values());
  }

  @Override
  public int updateUser(User user) {
    database.put(user.getUserUid(), user);
    return 1;
  }

  @Override
  public Optional<User> selectUserByUserUid(UUID userUid) {
    return Optional.ofNullable(database.get(userUid));
  }

}
