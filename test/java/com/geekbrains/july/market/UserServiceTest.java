package com.geekbrains.july.market;


import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.repositories.UsersRepository;
import com.geekbrains.july.market.services.UsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UsersService userService;

    @MockBean
    private UsersRepository userRepository;

    @Test
    public void findOneUserTest() {
        User userFromDB = new User();
        userFromDB.setFirstName("John");
        userFromDB.setEmail("john@mail.ru");

        Mockito.doReturn(Optional.of(userFromDB))
                .when(userRepository)
                .findByUsername("John");

        User userJohn = userService.findByUsername("John").get();
        Assertions.assertNotNull(userJohn);
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("John"));
//        Mockito.verify(userRepository, Mockito.times(1)).findOneByPhone(ArgumentMatchers.any(String.class));
    }

    public void findUserByPhoneTest() {
        User userFromDB = new User();
        userFromDB.setFirstName("1");
        userFromDB.setPhone("89999999999");

        Mockito.doReturn(Optional.of(userFromDB))
                .when(userRepository)
                .findOneByPhone("89999999999");

        User user = userService.findByPhone("89999999999").get();
        Assertions.assertNotNull(user);
        Mockito.verify(userRepository, Mockito.times(1)).findOneByPhone(ArgumentMatchers.eq("89999999999"));
    }
}
