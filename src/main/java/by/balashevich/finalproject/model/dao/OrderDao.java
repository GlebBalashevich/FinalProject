package by.balashevich.finalproject.model.dao;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao extends BaseDao<Order> {
    List<Order> findOrdersByParameters(Map<String, Object> orderParameters) throws DaoProjectException;
}
