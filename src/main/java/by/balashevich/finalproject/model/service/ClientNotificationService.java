package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Order;
import by.balashevich.finalproject.util.MailSender;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class ClientNotificationService {
    private static final String MESSAGE_FILENAME = "/prop/notification_message";
    private static final String REGISTRATION_MAIL_SUBJECT = "mail.registration.subject";
    private static final String REGISTRATION_TEXT_APPEAL = "mail.registration.appeal";
    private static final String REGISTRATION_TEXT_GRATITUDE = "mail.registration.gratitude";
    private static final String REGISTRATION_TEXT_FINISH = "mail.registration.finish_reg";
    private static final String REGISTRATION_LINK_SUFFIX = "?command=activate_client&email=";
    private static final String CREATE_ORDER_MAIL_SUBJECT = "mail.create_order.subject";
    private static final String CREATE_ORDER_MAIL_TEXT = "mail.create_order.text";
    private static final String DECLINE_ORDER_MAIL_SUBJECT = "mail.decline_order.subject";
    private static final String DECLINE_ORDER_DATE = "mail.decline_order.text.date";
    private static final String DECLINE_ORDER_CAR = "mail.decline_order.text.car";
    private static final String DECLINE_ORDER_TEXT = "mail.decline_order.text.text";
    private static final String EXPIRED_ORDER_SUBJECT = "mail.expired_order.subject";
    private static final String EXPIRED_ORDER_DATE = "mail.expired_order.text.date";
    private static final String EXPIRED_ORDER_CAR = "mail.expired_order.text.car";
    private static final String EXPIRED_ORDER_TEXT = "mail.expired_order.text.text";
    private static final String EXPIRED_REASON_PAYMENT = "mail.expired_order.text.reason_payment";
    private static final String EXPIRED_REASON_NOT_CONFIRM = "mail.expired_order.text.reason_not_confirm";
    private static final String EXPIRED_REASON_DEFAULT = "mail.expired_order.text.reason_default";
    private static final String COMPLETE_ORDER_SUBJECT = "mail.complete_order.subject";
    private static final String COMPLETE_ORDER_CAR = "mail.complete_order.text.car";
    private static final String COMPLETE_ORDER_TEXT = "mail.complete_order.text.text";
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
            throw new ServiceProjectException("An error occurred while sending create order email", e);
        }
    }

    public void declineOrderNotification(Order decliningOrder) throws ServiceProjectException {
        ResourceBundle bundle = ResourceBundle.getBundle(MESSAGE_FILENAME);
        String mailSubject = bundle.getString(DECLINE_ORDER_MAIL_SUBJECT);
        StringJoiner mailText = new StringJoiner(TEXT_SEPARATOR);
        mailText.add(bundle.getString(DECLINE_ORDER_DATE) + decliningOrder.getDateFrom());
        mailText.add(bundle.getString(DECLINE_ORDER_CAR) + decliningOrder.getCar().getModel());
        mailText.add(bundle.getString(DECLINE_ORDER_TEXT));

        try {
            MailSender.sendMail(decliningOrder.getClient().getEmail(), mailSubject, mailText.toString());
        } catch (MessagingException | IOException e) {
            throw new ServiceProjectException("An error occurred while sending the decline order email", e);
        }
    }

    public void expiredOrderNotification(Order expiredOrder) throws ServiceProjectException {
        ResourceBundle bundle = ResourceBundle.getBundle(MESSAGE_FILENAME);
        String mailSubject = bundle.getString(EXPIRED_ORDER_SUBJECT);
        String reason = switch (expiredOrder.getStatus()){
            case PENDING -> EXPIRED_REASON_NOT_CONFIRM;
            case AWAITING_PAYMENT -> EXPIRED_REASON_PAYMENT;
            default -> EXPIRED_REASON_DEFAULT;
        };
        StringJoiner mailText = new StringJoiner(TEXT_SEPARATOR);
        mailText.add(bundle.getString(EXPIRED_ORDER_DATE) + expiredOrder.getDateFrom());
        mailText.add(bundle.getString(EXPIRED_ORDER_CAR) + expiredOrder.getCar().getModel());
        mailText.add(bundle.getString(EXPIRED_ORDER_TEXT));
        mailText.add(bundle.getString(reason));

        try {
            MailSender.sendMail(expiredOrder.getClient().getEmail(), mailSubject, mailText.toString());
        } catch (MessagingException | IOException e) {
            throw new ServiceProjectException("An error occurred while sending the decline order email", e);
        }
    }

    public void completeOrderNotification(Order completedOrder) throws ServiceProjectException {
        ResourceBundle bundle = ResourceBundle.getBundle(MESSAGE_FILENAME);
        String mailSubject = bundle.getString(COMPLETE_ORDER_SUBJECT);
        StringJoiner mailText = new StringJoiner(TEXT_SEPARATOR);
        mailText.add(bundle.getString(COMPLETE_ORDER_CAR) + completedOrder.getCar().getModel());
        mailText.add(bundle.getString(COMPLETE_ORDER_TEXT));

        try {
            MailSender.sendMail(completedOrder.getClient().getEmail(), mailSubject, mailText.toString());
        } catch (MessagingException | IOException e) {
            throw new ServiceProjectException("An error occurred while sending the decline order email", e);
        }
    }

}
