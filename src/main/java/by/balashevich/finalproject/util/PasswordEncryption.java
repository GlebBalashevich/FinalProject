package by.balashevich.finalproject.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {

    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        StringBuilder encryptedPassword = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digestBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        for (byte hexElement : digestBytes) {
            encryptedPassword.append(String.format("%02x", hexElement));
        }

        return encryptedPassword.toString();
    }

    private PasswordEncryption() {
    }
}
