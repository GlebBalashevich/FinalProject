package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * The interface Order service.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public interface OrderService {
    /**
     * Add boolean.
     *
     * @param orderParameters the order parameters
     * @return the boolean
     * @throws ServiceProjectException the service project exception
     */
    boolean add(Map<String, String> orderParameters) throws ServiceProjectException;

    /**
     * Decline order boolean.
     *
     * @param decliningOrder the declining order
     * @return the boolean
     * @throws ServiceProjectException the service project exception
     */
    boolean declineOrder(Order decliningOrder) throws ServiceProjectException;

    /**
     * Manage orders int.
     *
     * @return the int
     * @throws ServiceProjectException the service project exception
     */
    int manageOrders() throws ServiceProjectException;

    /**
     * Update order status boolean.
     *
     * @param updatingOrder the updating order
     * @param statusData    the status data
     * @return the boolean
     * @throws ServiceProjectException the service project exception
     */
    boolean updateOrderStatus(Order updatingOrder, String statusData) throws ServiceProjectException;

    /**
     * Order payment boolean.
     *
     * @param payableOrder      the payable order
     * @param paymentParameters the payment parameters
     * @return the boolean
     * @throws ServiceProjectException the service project exception
     */
    boolean orderPayment(Order payableOrder, Map<String, String> paymentParameters) throws ServiceProjectException;

    /**
     * Calculate order amount int.
     *
     * @param costPerDay the cost per day
     * @param dateFrom   the date from
     * @param dateTo     the date to
     * @return the int
     */
    int calculateOrderAmount(int costPerDay, LocalDate dateFrom, LocalDate dateTo);

    /**
     * Find orders by parameters list.
     *
     * @param orderParameters the order parameters
     * @return the list
     * @throws ServiceProjectException the service project exception
     */
    List<Order> findOrdersByParameters(Map<String, String> orderParameters) throws ServiceProjectException;

    /**
     * Find client orders list.
     *
     * @param clientId the client id
     * @return the list
     * @throws ServiceProjectException the service project exception
     */
    List<Order> findClientOrders(long clientId) throws ServiceProjectException;
}
