package by.balashevich.finalproject.model.service.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.dao.impl.OrderDaoImpl;
import by.balashevich.finalproject.model.entity.Order;
import by.balashevich.finalproject.model.service.OrderService;
import by.balashevich.finalproject.util.DateConverter;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class OrderServiceImpl implements OrderService {
    private static final int DURING_DAY = 1;

    public boolean add(Map<String, String> orderParameters) throws ServiceProjectException {
        boolean isOrderAdded;
        OrderDaoImpl orderDao = new OrderDaoImpl();
        Map<String, Object> preparedOrderParameters = new HashMap<>();
        LocalDate dateFrom = LocalDate.parse(orderParameters.get(DATE_FROM));
        LocalDate dateTo = LocalDate.parse(orderParameters.get(DATE_TO));

        preparedOrderParameters.put(DATE_FROM, DateConverter.convertToLong(dateFrom));
        preparedOrderParameters.put(DATE_TO, DateConverter.convertToLong(dateTo));
        preparedOrderParameters.put(CAR_ID, Long.parseLong(orderParameters.get(CAR_ID)));
        preparedOrderParameters.put(USER_ID, Long.parseLong(orderParameters.get(USER_ID)));
        preparedOrderParameters.put(AMOUNT, Integer.parseInt(orderParameters.get(AMOUNT)));
        preparedOrderParameters.put(ORDER_STATUS, Order.Status.PENDING);

        try {
            isOrderAdded = orderDao.add(preparedOrderParameters);
        } catch (DaoProjectException e) {
            throw new ServiceProjectException("Error while creating new order", e);
        }

        return isOrderAdded;
    }

    public int calculateOrderAmount(int costPerDay, LocalDate dateFrom, LocalDate dateTo) {
        Period period = Period.between(dateFrom, dateTo);
        int rentDays = period.getDays() + DURING_DAY;
        int rentCost = costPerDay * rentDays;

        return rentCost;
    }
}
