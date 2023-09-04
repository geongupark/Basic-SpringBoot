package com.gus.demobasic.service;

import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import com.gus.demobasic.dao.FakeDataDao;
import com.gus.demobasic.model.User;
import com.gus.demobasic.model.User.Gender;

public class UserServiceTest {
  @Mock
  private FakeDataDao fakeDataDao;

  private UserService userService;

  @BeforeEach
  public void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    userService = new UserService(fakeDataDao);
  }

  @Test
  void testInsertUser() {
    UUID userUid = UUID.randomUUID();

    User anna = new User(userUid, "anna",
        "montana", Gender.FEMALE, 30, "anna@gmail.com");

    given(fakeDataDao.insertUser(any(UUID.class), any(User.class))).willReturn(1);

    ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

    int insertResult = userService.insertUser(anna);

    verify(fakeDataDao).insertUser(eq(userUid), captor.capture());

    User user = captor.getValue();

    assertAnnaFields(user);

    assertThat(insertResult).isEqualTo(1);
  }

  @Test
  void testUpdateUser() {
    UUID annaUid = UUID.randomUUID();
    User anna = new User(annaUid, "anna",
        "montana", Gender.FEMALE, 30, "anna@gmail.com");

    given(fakeDataDao.selectUserByUserUid(annaUid)).willReturn(Optional.of(anna));
    given(fakeDataDao.updateUser(anna)).willReturn(1);

    ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

    int updateResult = userService.updateUser(anna);

    verify(fakeDataDao).selectUserByUserUid(annaUid);
    verify(fakeDataDao).updateUser(captor.capture());

    User user = captor.getValue();
    assertAnnaFields(user);

    assertThat(updateResult).isEqualTo(1);
  }

  private void assertAnnaFields(User user) {
    assertThat(user.getAge()).isEqualTo(30);
    assertThat(user.getFirstName()).isEqualTo("anna");
    assertThat(user.getLastName()).isEqualTo("montana");
    assertThat(user.getGender()).isEqualTo(Gender.FEMALE);
    assertThat(user.getEmail()).isEqualTo("anna@gmail.com");
    assertThat(user.getUserUid()).isNotNull();
    assertThat(user.getUserUid()).isInstanceOf(UUID.class);
  }
}
