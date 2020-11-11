package by.balashevich.finalproject.model.service.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.dao.impl.OrderDaoImpl;
import by.balashevich.finalproject.model.entity.Order;
import by.balashevich.finalproject.util.ParameterKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;
import static by.balashevich.finalproject.util.ParameterKey.AMOUNT;
import static org.testng.Assert.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*"})
@PrepareForTest({OrderDaoImpl.class})
public class OrderServiceImplTest {
    OrderDaoImpl orderDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(OrderDaoImpl.class);
        orderDao = Mockito.mock(OrderDaoImpl.class);
        Mockito.when(OrderDaoImpl.getInstance()).thenReturn(orderDao);
    }

    @Test
    public void addTest() {
        try {
            Mockito.when(orderDao.add(Mockito.any())).thenReturn(true);

            OrderServiceImpl orderService = new OrderServiceImpl();
            Map<String, String> orderParameters = new HashMap<>();
            orderParameters.put(DATE_FROM, "2045-12-31");
            orderParameters.put(DATE_TO, "2046-01-06");
            orderParameters.put(CAR_ID, "1");
            orderParameters.put(USER_ID, "1");
            orderParameters.put(AMOUNT, "250");
            boolean actual = orderService.add(orderParameters);
            assertTrue(actual);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void addExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(orderDao.add(Mockito.any())).thenThrow(daoProjectException);

        OrderServiceImpl orderService = new OrderServiceImpl();
        Map<String, String> orderParameters = new HashMap<>();
        orderParameters.put(DATE_FROM, "2045-12-31");
        orderParameters.put(DATE_TO, "2046-01-06");
        orderParameters.put(CAR_ID, "1");
        orderParameters.put(USER_ID, "1");
        orderParameters.put(AMOUNT, "250");
        orderService.add(orderParameters);
    }

    @Test
    public void declineOrderTest() {
        try {
            Mockito.when(orderDao.remove(Mockito.any())).thenReturn(true);

            OrderServiceImpl orderService = new OrderServiceImpl();
            Order order = new Order();
            boolean actual = orderService.declineOrder(order);
            assertTrue(actual);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void declineOrderExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(orderDao.remove(Mockito.any())).thenThrow(daoProjectException);

        OrderServiceImpl orderService = new OrderServiceImpl();
        Order order = new Order();
        orderService.declineOrder(order);
    }

    @DataProvider(name = "orderStatusData")
    public Object[][] createOrderStatusData() {
        return new Object[][]{
                {"AWAITING_PAYMENT", true},
                {"wrong", false},
        };
    }

    @Test(dataProvider = "orderStatusData")
    public void updateOrderStatusTest(String statusData, boolean expected) {
        try {
            Mockito.when(orderDao.updateOrderStatus(Mockito.anyLong(), Mockito.any())).thenReturn(true);

            OrderServiceImpl orderService = new OrderServiceImpl();
            Order order = new Order();
            boolean actual = orderService.updateOrderStatus(order, statusData);
            assertEquals(actual, expected);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void updateOrderStatusExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(orderDao.updateOrderStatus(Mockito.anyLong(), Mockito.any())).thenThrow(daoProjectException);

        OrderServiceImpl orderService = new OrderServiceImpl();
        Order order = new Order();
        orderService.updateOrderStatus(order, "AWAITING_PAYMENT");
    }

    @Test
    public void orderPaymentTest() {
        try {
            Mockito.when(orderDao.updateOrderStatus(Mockito.anyLong(), Mockito.any())).thenReturn(true);

            Order order = new Order();
            Map<String, String> paymentParameters = new HashMap<>();
            paymentParameters.put(ParameterKey.CARD_HOLDER, "Ivanov Ivan");
            paymentParameters.put(ParameterKey.CARD_NUMBER, "1111222233334444");
            paymentParameters.put(ParameterKey.CARD_EXPIRATION_MONTH, "12");
            paymentParameters.put(ParameterKey.CARD_EXPIRATION_YEAR, "40");
            paymentParameters.put(ParameterKey.CARD_CVV_CODE, "123");
            OrderServiceImpl orderService = new OrderServiceImpl();
            boolean actual = orderService.orderPayment(order, paymentParameters);
            assertTrue(actual);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void orderPaymentExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(orderDao.updateOrderStatus(Mockito.anyLong(), Mockito.any())).thenThrow(daoProjectException);

        Order order = new Order();
        Map<String, String> paymentParameters = new HashMap<>();
        paymentParameters.put(ParameterKey.CARD_HOLDER, "Ivanov Ivan");
        paymentParameters.put(ParameterKey.CARD_NUMBER, "1111222233334444");
        paymentParameters.put(ParameterKey.CARD_EXPIRATION_MONTH, "12");
        paymentParameters.put(ParameterKey.CARD_EXPIRATION_YEAR, "40");
        paymentParameters.put(ParameterKey.CARD_CVV_CODE, "123");
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.orderPayment(order, paymentParameters);
    }

    @DataProvider(name = "orderParametersData")
    public Object[][] createOrderParametersData() {
        return new Object[][]{
                {new HashMap<>(Map.of(ORDER_STATUS, "AWAITING_PAYMENT"))},
                {new HashMap<>()},
        };
    }

    @Test(dataProvider = "orderParametersData")
    public void findOrdersByParametersTest(Map<String, String> orderParameters) {
        try {
            List<Order> expected = new ArrayList<>();
            Mockito.when(orderDao.findAll()).thenReturn(expected);
            Mockito.when(orderDao.findOrdersByParameters(Mockito.any())).thenReturn(expected);

            OrderServiceImpl orderService = new OrderServiceImpl();
            List<Order> actual = orderService.findOrdersByParameters(orderParameters);
            assertEquals(actual, expected);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class, dataProvider = "orderParametersData")
    public void findOrdersByParametersExceptionTest(Map<String, String> orderParameters) throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(orderDao.findAll()).thenThrow(daoProjectException);
        Mockito.when(orderDao.findOrdersByParameters(Mockito.any())).thenThrow(daoProjectException);

        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.findOrdersByParameters(orderParameters);
    }

    @Test
    public void findClientOrdersTest() {
        try {
            List<Order> expected = new ArrayList<>();
            Mockito.when(orderDao.findClientOrders(Mockito.anyLong())).thenReturn(expected);

            OrderServiceImpl orderService = new OrderServiceImpl();
            List<Order> actual = orderService.findClientOrders(1L);
            assertEquals(actual, expected);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void findClientOrdersExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(orderDao.findClientOrders(Mockito.anyLong())).thenThrow(daoProjectException);

        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.findClientOrders(1L);
    }

    @DataProvider(name = "orderAmountData")
    public Object[][] createOrderAmountData() {
        return new Object[][]{
                {120, "2020-02-28", "2020-03-01", 360},
                {120, "2020-10-31", "2020-10-31", 120},
                {120, "2020-01-10", "2020-01-05", -1},
                {120, null, "2020-01-05", -1},
        };
    }

    @Test(dataProvider = "orderAmountData")
    public void testCalculateOrderAmountTest(int rentCost, String dateFromData, String dateToData, int expected) {
        OrderServiceImpl orderService = new OrderServiceImpl();
        int actual = orderService.calculateOrderAmount(rentCost, dateFromData, dateToData);
        assertEquals(actual, expected);
    }
}