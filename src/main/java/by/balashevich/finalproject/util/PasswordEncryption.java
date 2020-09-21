package by.balashevich.finalproject.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
    private static final String HASH_METHOD = "SHA-256";
    private static final String ELEMENT_VIEW = "%02x";

    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        StringBuilder encryptedPassword = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance(HASH_METHOD);
        byte[] digestBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        for (byte hexElement : digestBytes) {
            encryptedPassword.append(String.format(ELEMENT_VIEW, hexElement));
        }

        return encryptedPassword.toString();
    }

    private PasswordEncryption() {
    }
}
