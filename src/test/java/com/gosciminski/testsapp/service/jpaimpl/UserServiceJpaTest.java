package com.gosciminski.testsapp.service.jpaimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.gosciminski.testsapp.conf.PdfUserDetails;
import com.gosciminski.testsapp.conf.WebMvcConfiguration;
import com.gosciminski.testsapp.dto.create.UserCreatorDto;
import com.gosciminski.testsapp.model.User;
import com.gosciminski.testsapp.repisitory.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
public class UserServiceJpaTest {

    @Mock
    UserRepository userRepositoryMock;

    @Mock
    WebMvcConfiguration webMvcConfigMock;

    @InjectMocks
    UserServiceJpa userServiceJpa;

    @InjectMocks
    @Spy
    UserServiceJpa userServiceJpaSpy;


    @Test
    public void findUserByName_shouldFind(){

        User user = new User();

        when(userRepositoryMock.findByName(any(String.class))).thenReturn(user);

        User result = userServiceJpa.findUserByName("xx");

        assertNotNull(result);
        assertEquals(user, result);

        verify(userRepositoryMock, atLeastOnce()).findByName(any(String.class));
    }

    @Test
    public void saveUser_shouldThrowIllegalArgumentException(){
        User user = new User();
        user.setName("xx");

        UserCreatorDto userCreatorDto = new UserCreatorDto();
        userCreatorDto.setName("xx");

        doReturn(user).when(userServiceJpaSpy).findUserByName(any(String.class));

        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            userServiceJpaSpy.saveUser(userCreatorDto);
        });
    }

    @Test
    public void saveUser_shouldSave(){

        UserCreatorDto userCreatorDto = new UserCreatorDto();
        userCreatorDto.setName("xx");
        userCreatorDto.setEmail("a@a");
        userCreatorDto.setPassword("password");

        doReturn(null).when(userServiceJpaSpy).findUserByName(any(String.class));
        when(userRepositoryMock.save(any(User.class))).thenReturn(new User());
        when(webMvcConfigMock.passwordEncoder()).thenReturn(new BCryptPasswordEncoder());

        User result = userServiceJpaSpy.saveUser(userCreatorDto);

        assertNotNull(result);

        verify(userServiceJpaSpy, atLeastOnce()).findUserByName(any(String.class));
        verify(userRepositoryMock, atLeastOnce()).save(any(User.class));
    }
}