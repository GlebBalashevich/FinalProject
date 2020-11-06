package by.balashevich.finalproject.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The type Password encryption.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class PasswordEncryption {
    private static final Logger logger = LogManager.getLogger();
    private static final String HASH_METHOD = "SHA-256";
    private static final String ELEMENT_VIEW = "%02x";

    /**
     * Encrypt password string.
     *
     * @param password the password
     * @return the string
     */
    public static String encryptPassword(String password) {
        StringBuilder encryptedPassword = new StringBuilder();
        MessageDigest md;

        try {
            md = MessageDigest.getInstance(HASH_METHOD);
            byte[] digestBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            for (byte hexElement : digestBytes) {
                encryptedPassword.append(String.format(ELEMENT_VIEW, hexElement));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.ERROR, "An error occurred while encrypting the password", e);
        }

        return encryptedPassword.toString();
    }

    private PasswordEncryption() {
    }
}
