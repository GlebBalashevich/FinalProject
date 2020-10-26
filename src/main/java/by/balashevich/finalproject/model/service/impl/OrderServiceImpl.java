package by.balashevich.finalproject.model.service.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.dao.OrderDao;
import by.balashevich.finalproject.model.dao.impl.OrderDaoImpl;
import by.balashevich.finalproject.model.entity.Order;
import by.balashevich.finalproject.model.service.ClientNotificationService;
import by.balashevich.finalproject.model.service.OrderService;
import by.balashevich.finalproject.util.DateConverter;
import by.balashevich.finalproject.validator.CarValidator;
import by.balashevich.finalproject.validator.OrderValidator;
import by.balashevich.finalproject.validator.UserValidator;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    private static final int DURING_DAY = 1;

    @Override
    public boolean add(Map<String, String> orderParameters) throws ServiceProjectException {
        boolean isOrderAdded;
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

    @Override
    public boolean declineOrder(Order decliningOrder) throws ServiceProjectException {
        boolean isOrderDeclined;

        try {
            isOrderDeclined = orderDao.remove(decliningOrder);
        } catch (DaoProjectException e) {
            throw new ServiceProjectException("Error while declining order", e);
        }

        return isOrderDeclined;
    }

    @Override
    public int deleteExpiredOrders() throws ServiceProjectException {
        ClientNotificationService notificationService = new ClientNotificationService();
        List<Order> inspectingOrders;

        try{
            inspectingOrders = orderDao.findWaitingActionOrders();
            if (inspectingOrders != null && !inspectingOrders.isEmpty()){
                LocalDate today = LocalDate.now();
                for(Order inspectingOrder : inspectingOrders){
                    if (today.toEpochDay() >= inspectingOrder.getDateFrom().toEpochDay()){
                        if (orderDao.remove(inspectingOrder)){
                            notificationService.expiredOrderNotification(inspectingOrder);
                        }
                    }
                }
            }
        } catch (DaoProjectException e) {
            throw new ServiceProjectException("Error while deleting expired orders", e);
        }

        return 0;
    }

    @Override
    public boolean updateOrderStatus(Order updatingOrder, String statusData) throws ServiceProjectException {
        boolean isOrderStatusUpdated = false;

        try {
            if (OrderValidator.validateStatus(statusData)) {
                Order.Status status = Order.Status.valueOf(statusData);
                isOrderStatusUpdated = orderDao.updateOrderStatus(updatingOrder.getOrderId(), status);
                if (isOrderStatusUpdated) {
                    updatingOrder.setStatus(status);
                }
            }
        } catch (DaoProjectException e) {
            throw new ServiceProjectException("Error while updating order status", e);
        }

        return isOrderStatusUpdated;
    }

    @Override
    public List<Order> findOrdersByParameters(Map<String, String> orderParameters) throws ServiceProjectException {
        List<Order> targetOrders;
        Map<String, Object> orderParametersChecked = new HashMap<>();

        String statusData = orderParameters.get(ORDER_STATUS);
        String clientEmailData = orderParameters.get(EMAIL);
        String carModelData = orderParameters.get(MODEL);

        try {
            if (OrderValidator.validateStatus(statusData)) {
                orderParametersChecked.put(ORDER_STATUS, Order.Status.valueOf(statusData.toUpperCase()));
            }
            if (UserValidator.validateEmail(clientEmailData)) {
                orderParametersChecked.put(EMAIL, clientEmailData);
            }
            if (CarValidator.validateModel(carModelData)) {
                orderParametersChecked.put(MODEL, carModelData);
            }
            if (!orderParametersChecked.isEmpty()) {
                targetOrders = orderDao.findOrdersByParameters(orderParametersChecked);
            } else {
                targetOrders = orderDao.findAll();
            }
        } catch (DaoProjectException e) {
            throw new ServiceProjectException(e);
        }

        return targetOrders;
    }

    @Override
    public int calculateOrderAmount(int costPerDay, LocalDate dateFrom, LocalDate dateTo) {
        Period period = Period.between(dateFrom, dateTo);
        int rentDays = period.getDays() + DURING_DAY;
        int rentCost = costPerDay * rentDays;

        return rentCost;
    }
}
