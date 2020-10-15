package by.balashevich.finalproject.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailSender {
    private static final String CONFIG_FILEPATH = "config/mail.properties";
    private static final String USER_NAME = "mail.user.name";
    private static final String USER_PASSWORD = "mail.user.password";

    private MailSender() {
    }

    public static void sendMail(String recipientAddress, String subject, String text) throws IOException, MessagingException {
        Properties properties = getProperties();
        Session session = getSession(properties);
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress((String) properties.get(USER_NAME)));
        message.setSubject(subject);
        message.setText(text);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientAddress));

        Transport.send(message);
    }

    private static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(CONFIG_FILEPATH);
        properties.load(stream);

        return properties;
    }

    private static Session getSession(Properties properties) {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication((String) properties.get(USER_NAME), (String) properties.get(USER_PASSWORD));
            }
        });

        return session;
    }

}
