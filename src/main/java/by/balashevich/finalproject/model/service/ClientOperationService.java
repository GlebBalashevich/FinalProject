package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.util.MailSender;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class ClientOperationService {
    private static final String MESSAGE_FILENAME = "/prop/notification_message";
    private static final String MAIL_SUBJECT = "mail.registration.subject";
    private static final String TEXT_APPEAL = "mail.registration.appeal";
    private static final String TEXT_GRATITUDE = "mail.registration.gratitude";
    private static final String TEXT_FINISH_REGISTRATION = "mail.registration.finish_reg";
    private static final String TEXT_SEPARATOR = "\n";
    private static final String LINK_ADDRESS = "http://localhost:8070/process_controller?" +
            "command=activate_client&email=";

    public void registerMailNotification(String clientEmail, String clientLocale, String clientFirstName) throws ServiceProjectException {
        Locale locale = new Locale(clientLocale);
        ResourceBundle bundle = ResourceBundle.getBundle(MESSAGE_FILENAME, locale);
        String mailSubject = bundle.getString(MAIL_SUBJECT);
        StringJoiner mailText = new StringJoiner(TEXT_SEPARATOR);
        mailText.add(bundle.getString(TEXT_APPEAL)+clientFirstName);
        mailText.add(bundle.getString(TEXT_GRATITUDE));
        mailText.add(bundle.getString(TEXT_FINISH_REGISTRATION));
        mailText.add(LINK_ADDRESS+clientEmail);

        try {
            MailSender.sendMail(clientEmail, mailSubject, mailText.toString());
        } catch (MessagingException | IOException e) {
            throw new ServiceProjectException("An error occurred while sending the registration email", e);
        }
    }
}
