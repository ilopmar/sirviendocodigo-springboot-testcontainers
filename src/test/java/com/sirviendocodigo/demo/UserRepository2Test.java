package com.sirviendocodigo.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class UserRepository2Test {

  @Container
  @ServiceConnection
  static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(
      DockerImageName.parse("postgres:16.1")
  );

  @Autowired
  private UserRepository userRepository;

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
