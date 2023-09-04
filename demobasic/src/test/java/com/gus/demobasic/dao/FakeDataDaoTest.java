package com.gus.demobasic.dao;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gus.demobasic.model.User;
import com.gus.demobasic.model.User.Gender;

public class FakeDataDaoTest {
  private FakeDataDao fakeDataDao;

  @BeforeEach
  public void setUp() throws Exception {
    fakeDataDao = new FakeDataDao();
  }

  @Test
  void testSelectAllUsers() {
    List<User> users = fakeDataDao.selectAllUsers();
    assertThat(users).hasSize(1);

    User user = users.get(0);

    assertThat(user.getAge()).isEqualTo(30);
    assertThat(user.getFirstName()).isEqualTo("gus");
    assertThat(user.getLastName()).isEqualTo("park");
    assertThat(user.getGender()).isEqualTo(Gender.MALE);
    assertThat(user.getEmail()).isEqualTo("gus@samsung.com");
    assertThat(user.getUserUid()).isNotNull();
  }
}
