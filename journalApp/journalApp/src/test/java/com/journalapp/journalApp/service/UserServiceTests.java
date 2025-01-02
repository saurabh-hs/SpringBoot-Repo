package com.journalapp.journalApp.service;

import com.journalapp.journalApp.entity.User;
import com.journalapp.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testCreateUser(User user) {
        assertTrue(userService.saveNewUser(user));
    }

    @BeforeEach
    void setUp() {

    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "Saurabh",
            "krunali",
            "Rushikesh"
    })
    public void testFindByUserName(String name) {
        assertNotNull(userRepository.findByUserName("Saurabh"));
         assertNotNull(userRepository.findByUserName(name), "Failed for: "+name);
//        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "5, 6, 11",
            "12, 34, 45"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }
}
