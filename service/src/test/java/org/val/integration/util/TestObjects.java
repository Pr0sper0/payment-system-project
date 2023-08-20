package org.val.integration.util;

import java.time.LocalDate;
import org.val.entity.Role;
import org.val.entity.User;

public class TestObjects {

  public static final Role ADMIN = Role.builder()
      .id(1)
      .role("ADMIN")
      .description("Administrator")
      .build();

  public static final Role USER = Role.builder()
      .id(2)
      .role("USER")
      .description("User")
      .build();

  public static final User IVAN = User.builder()
          .id(1)
          .userName("User1")
          .name("Ivan")
          .surname("Ivanov")
          .birthday(LocalDate.of(1993, 1, 1))
          .email("ivan@mail.com")
          .password("111")
          .role(ADMIN)
          .build();

  public static final User PETR = User.builder()
          .id(2)
          .userName("User2")
          .name("Petr")
          .surname("Petrov")
          .birthday(LocalDate.of(1997, 1, 1))
          .email("petr@mail.com")
          .password("222")
          .role(USER)
          .build();

  public static final User SERGEY = User.builder()
          .id(3)
          .userName("User3")
          .name("Sergey")
          .surname("Sergeev")
          .birthday(LocalDate.of(1990, 1, 1))
          .email("sergey@mail.com")
          .password("333")
          .role(USER)
          .build();


}
