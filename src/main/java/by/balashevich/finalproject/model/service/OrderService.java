package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderService<T extends Order> {
    boolean add(Map<String, String> orderParameters) throws ServiceProjectException;

    int calculateOrderAmount(int costPerDay, LocalDate dateFrom, LocalDate dateTo);

    List<T> findOrdersByParameters(Map<String, String> orderParameters) throws ServiceProjectException;
}
