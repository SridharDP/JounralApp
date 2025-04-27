//package com.dp.demospring.service;
//
//import com.dp.demospring.repository.UserRepository;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.util.Assert;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest
//public class UserServiceTests {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    @Disabled
//    public void finByUserNameTest()
//    {
//        assertNotNull(userRepository.findByUser("Sham"));
//    }
//    @ParameterizedTest
//    @ValueSource(strings={
//       "ram",
//       "Sham",
//       "Sony"
//    })
//    public void findUsesTest(String name)
//    {
//        assertNotNull(userRepository.findByUser(name),"failed for"+ name);
//    }
//}
