package com.journalapp.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmailTest() {
        emailService.sendEmail("saurabhshinde31899@gmail.com",
                "Test Mail By Saurabh",
                "This is a test mail auto generated mail");
    }

}
