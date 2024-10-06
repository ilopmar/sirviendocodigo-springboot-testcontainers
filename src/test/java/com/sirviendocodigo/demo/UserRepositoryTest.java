package com.sirviendocodigo.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;

@DisplayName("Given a UserRepository")
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @DisplayName("When saving and retrieving a user then it is saved and retrieved properly")
  @Test
  void saveUser() {
    User user = new User("Iván");

    userRepository.save(user);

    assertThat(user.getId()).isNotNull();

    Optional<User> optUser = userRepository.findByName(user.getName());
    assertThat(optUser)
        .isPresent()
        .get()
        .extracting(User::getId, User::getName)
        .containsExactly(user.getId(), user.getName());
  }

}

