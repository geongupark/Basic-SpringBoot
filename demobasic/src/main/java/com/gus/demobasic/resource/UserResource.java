package com.gus.demobasic.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gus.demobasic.model.User;
import com.gus.demobasic.service.UserService;

// 이 annotation 안 붙이면 endpoint 등록 안됨
@RestController
public class UserResource {
  private UserService userService;

  // DI(의존성주입)을 통해 객체 생성
  @Autowired
  public UserResource(UserService userService) {
    this.userService = userService;
  }

  // For get request
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List<User> fetchUsers() {
    return userService.selectAllUsers();
  }
}
