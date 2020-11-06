package by.balashevich.finalproject.model.dao;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * The interface Order dao.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public interface OrderDao extends BaseDao<Order> {

    /**
     * Update order status boolean.
     *
     * @param orderId the order id
     * @param status  the status
     * @return the boolean
     * @throws DaoProjectException the dao project exception
     */
    boolean updateOrderStatus(long orderId, Order.Status status) throws DaoProjectException;

    /**
     * Find waiting action orders list.
     *
     * @return the list
     * @throws DaoProjectException the dao project exception
     */
    List<Order> findWaitingActionOrders() throws DaoProjectException;

    /**
     * Find orders by parameters list.
     *
     * @param orderParameters the order parameters
     * @return the list
     * @throws DaoProjectException the dao project exception
     */
    List<Order> findOrdersByParameters(Map<String, Object> orderParameters) throws DaoProjectException;

    /**
     * Find client orders list.
     *
     * @param clientId the client id
     * @return the list
     * @throws DaoProjectException the dao project exception
     */
    List<Order> findClientOrders(long clientId) throws DaoProjectException;
}
