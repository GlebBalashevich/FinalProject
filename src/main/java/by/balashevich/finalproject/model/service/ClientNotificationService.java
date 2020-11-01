package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Order;
import by.balashevich.finalproject.util.MailSender;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public interface ClientNotificationService {

    void registerMailNotification(String clientEmail, String clientFirstName, String link) throws ServiceProjectException;

    void createOrderNotification(String clientEmail) throws ServiceProjectException;

    void declineOrderNotification(Order decliningOrder) throws ServiceProjectException;

    void expiredOrderNotification(Order expiredOrder) throws ServiceProjectException;

    void completeOrderNotification(Order completedOrder) throws ServiceProjectException;
}
