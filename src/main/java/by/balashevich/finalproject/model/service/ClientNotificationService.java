package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Order;

/**
 * The interface Client notification service.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public interface ClientNotificationService {

    /**
     * Register mail notification.
     *
     * @param clientEmail     the client email
     * @param clientFirstName the client first name
     * @param link            the link
     * @throws ServiceProjectException the service project exception
     */
    void registerMailNotification(String clientEmail, String clientFirstName, String link) throws ServiceProjectException;

    /**
     * Create order notification.
     *
     * @param clientEmail the client email
     * @throws ServiceProjectException the service project exception
     */
    void createOrderNotification(String clientEmail) throws ServiceProjectException;

    /**
     * Decline order notification.
     *
     * @param decliningOrder the declining order
     * @throws ServiceProjectException the service project exception
     */
    void declineOrderNotification(Order decliningOrder) throws ServiceProjectException;

    /**
     * Expired order notification.
     *
     * @param expiredOrder the expired order
     * @throws ServiceProjectException the service project exception
     */
    void expiredOrderNotification(Order expiredOrder) throws ServiceProjectException;

    /**
     * Complete order notification.
     *
     * @param completedOrder the completed order
     * @throws ServiceProjectException the service project exception
     */
    void completeOrderNotification(Order completedOrder) throws ServiceProjectException;
}
