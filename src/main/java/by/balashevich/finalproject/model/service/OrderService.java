package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;

import java.time.LocalDate;
import java.util.Map;

public interface OrderService {
    boolean add(Map<String, String> orderParameters) throws ServiceProjectException;

    int calculateOrderAmount(int costPerDay, LocalDate dateFrom, LocalDate dateTo);
}
