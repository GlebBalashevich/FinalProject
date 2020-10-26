package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderService {
    boolean add(Map<String, String> orderParameters) throws ServiceProjectException;

    boolean declineOrder(Order decliningOrder) throws ServiceProjectException;

    int deleteExpiredOrders() throws ServiceProjectException;

    boolean updateOrderStatus(Order updatingOrder, String statusData) throws ServiceProjectException;

    int calculateOrderAmount(int costPerDay, LocalDate dateFrom, LocalDate dateTo);

    List<Order> findOrdersByParameters(Map<String, String> orderParameters) throws ServiceProjectException;
}
