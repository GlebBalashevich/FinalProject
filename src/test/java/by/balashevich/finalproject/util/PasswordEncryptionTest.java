package by.balashevich.finalproject.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PasswordEncryptionTest {

    @Test
    public void encryptPasswordTest() {
        String expected = "60fe74406e7f353ed979f350f2fbb6a2e8690a5fa7d1b0c32983d1d8b3f95f67";
        String actual = PasswordEncryption.encryptPassword("Admin1234");
        assertEquals(expected, actual);
    }
}