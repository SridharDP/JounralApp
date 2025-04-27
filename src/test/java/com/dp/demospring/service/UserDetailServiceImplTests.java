//package com.dp.demospring.service;
//
//import com.dp.demospring.entity.User;
//import com.dp.demospring.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class UserDetailServiceImplTests {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserDetailServiceImpl userDetailService;
//
//    private User testUser;
//
//    @BeforeEach
//    void setUp() {
//        // Initialize a test user
//        testUser = new User();
//        testUser.setUser("testuser");
//        testUser.setPassword("encodedPassword123");
//        testUser.setRoles(Arrays.asList("USER", "ADMIN"));
//    }
//
//    @Test
//    void loadUserByUsername_UserFound_ReturnsUserDetails() {
//        // Arrange
//        String username = "testuser";
//        when(userRepository.findByUser(username)).thenReturn(testUser);
//
//        // Act
//        UserDetails userDetails = userDetailService.loadUserByUsername(username);
//
//        // Assert
//        assertNotNull(userDetails);
//        assertEquals(username, userDetails.getUsername());
//        assertEquals(testUser.getPassword(), userDetails.getPassword());
//        assertEquals(2, userDetails.getAuthorities().size());
//        assertTrue(userDetails.getAuthorities().stream()
//                .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
//        assertTrue(userDetails.getAuthorities().stream()
//                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
//        verify(userRepository, times(1)).findByUser(username);
//        verifyNoMoreInteractions(userRepository);
//    }
//
//    @Test
//    void loadUserByUsername_UserNotFound_ThrowsUsernameNotFoundException() {
//        // Arrange
//        String username = "nonexistent";
//        when(userRepository.findByUser(username)).thenReturn(null);
//
//        // Act & Assert
//        UsernameNotFoundException exception = assertThrows(
//                UsernameNotFoundException.class,
//                () -> userDetailService.loadUserByUsername(username)
//        );
//        assertEquals("User not found " + username, exception.getMessage());
//        verify(userRepository, times(1)).findByUser(username);
//        verifyNoMoreInteractions(userRepository);
//    }
//}