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
    private static final String REGISTRATION_MAIL_SUBJECT = "mail.registration.subject";
    private static final String REGISTRATION_TEXT_APPEAL = "mail.registration.appeal";
    private static final String REGISTRATION_TEXT_GRATITUDE = "mail.registration.gratitude";
    private static final String REGISTRATION_TEXT_FINISH = "mail.registration.finish_reg";
    private static final String REGISTRATION_LINK_SUFFIX = "?command=activate_client&email=";
    private static final String CREATE_ORDER_MAIL_SUBJECT = "mail.create_order.subject";
    private static final String CREATE_ORDER_MAIL_TEXT = "mail.create_order.text";
    private static final String TEXT_SEPARATOR = "\n";

    public void registerMailNotification(String clientEmail, String clientLocale,
                                         String clientFirstName, String link) throws ServiceProjectException {
        Locale locale = new Locale(clientLocale);
        ResourceBundle bundle = ResourceBundle.getBundle(MESSAGE_FILENAME, locale);
        String mailSubject = bundle.getString(REGISTRATION_MAIL_SUBJECT);
        StringJoiner mailText = new StringJoiner(TEXT_SEPARATOR);
        mailText.add(bundle.getString(REGISTRATION_TEXT_APPEAL) + clientFirstName);
        mailText.add(bundle.getString(REGISTRATION_TEXT_GRATITUDE));
        mailText.add(bundle.getString(REGISTRATION_TEXT_FINISH));
        mailText.add(link + REGISTRATION_LINK_SUFFIX + clientEmail);

        try {
            MailSender.sendMail(clientEmail, mailSubject, mailText.toString());
        } catch (MessagingException | IOException e) {
            throw new ServiceProjectException("An error occurred while sending the registration email", e);
        }
    }

    public void createOrderNotification(String clientEmail, String clientLocale) throws ServiceProjectException {
        Locale locale = new Locale(clientLocale);
        ResourceBundle bundle = ResourceBundle.getBundle(MESSAGE_FILENAME, locale);
        String mailSubject = bundle.getString(CREATE_ORDER_MAIL_SUBJECT);
        String mailText = bundle.getString(CREATE_ORDER_MAIL_TEXT);

        try {
            MailSender.sendMail(clientEmail, mailSubject, mailText);
        } catch (MessagingException | IOException e) {
            throw new ServiceProjectException("An error occurred while sending the registration email", e);
        }
    }

}
