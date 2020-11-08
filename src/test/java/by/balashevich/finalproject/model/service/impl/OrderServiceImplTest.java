package by.balashevich.finalproject.model.service.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.dao.impl.OrderDaoImpl;
import by.balashevich.finalproject.model.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easymock.EasyMock;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;
import static by.balashevich.finalproject.util.ParameterKey.AMOUNT;
import static org.testng.Assert.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*"})
@PrepareForTest({LogManager.class, OrderDaoImpl.class, ClientNotificationServiceImpl.class})
public class OrderServiceImplTest {
    OrderDaoImpl orderDao;
    Logger logger;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMock.mockStatic(OrderDaoImpl.class);
        PowerMock.mockStatic(LogManager.class);
        logger = EasyMock.mock(Logger.class);
        orderDao = EasyMock.mock(OrderDaoImpl.class);
        EasyMock.expect(LogManager.getLogger(EasyMock.anyObject(Class.class))).andReturn(logger).anyTimes();
        EasyMock.expect(OrderDaoImpl.getInstance()).andReturn(orderDao).anyTimes();
    }

    @Test
    public void addTest() {
        try {
            EasyMock.expect(orderDao.add(EasyMock.anyObject())).andReturn(true);
            PowerMock.replayAll();
            EasyMock.replay(orderDao);

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
        EasyMock.expect(orderDao.add(EasyMock.anyObject())).andThrow(daoProjectException);
        PowerMock.replayAll();
        EasyMock.replay(orderDao);

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
            EasyMock.expect(orderDao.remove(EasyMock.anyObject())).andReturn(true);
            PowerMock.replayAll();
            EasyMock.replay(orderDao);

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
        EasyMock.expect(orderDao.remove(EasyMock.anyObject())).andThrow(daoProjectException);
        PowerMock.replayAll();
        EasyMock.replay(orderDao);

        OrderServiceImpl orderService = new OrderServiceImpl();
        Order order = new Order();
        orderService.declineOrder(order);
    }

    @Test
    public void manageOrdersTest() {
        try {
            Order order1 = new Order();
            Order order2 = new Order();
            Order order3 = new Order();
            Order order4 = new Order();
            Order order5 = new Order();
            order1.setStatus(Order.Status.PENDING);
            order1.setDateFrom(LocalDate.now());
            order2.setStatus(Order.Status.AWAITING_PAYMENT);
            order2.setDateFrom(LocalDate.parse("1974-12-10"));
            order3.setStatus(Order.Status.ACTIVE);
            order3.setDateTo(LocalDate.parse("1974-12-10"));
            order4.setStatus(Order.Status.PENDING);
            order4.setDateFrom(LocalDate.parse("2045-12-31"));
            order5.setStatus(Order.Status.COMPLETED);
            List<Order> inspectingOrders = new ArrayList<>(List.of(order1, order2, order3, order4, order5));

            ClientNotificationServiceImpl notificationService = PowerMock.createMock(ClientNotificationServiceImpl.class);
            PowerMock.expectNew(ClientNotificationServiceImpl.class).andReturn(notificationService);
            notificationService.completeOrderNotification(EasyMock.anyObject());
            EasyMock.expectLastCall().anyTimes();
            notificationService.expiredOrderNotification(EasyMock.anyObject());
            EasyMock.expectLastCall().anyTimes();
            EasyMock.expect(orderDao.findWaitingActionOrders()).andReturn(inspectingOrders);
            EasyMock.expect(orderDao.remove(EasyMock.anyObject())).andReturn(true);
            EasyMock.expect(orderDao.updateOrderStatus(EasyMock.anyLong(), EasyMock.anyObject())).andReturn(true);
            PowerMock.replayAll();
            PowerMock.replay(notificationService);
            EasyMock.replay(orderDao);

            OrderServiceImpl orderService = new OrderServiceImpl();
            int actual = orderService.manageOrders();
            assertEquals(actual, 3);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void updateOrderStatusTest() {
    }

    @Test
    public void orderPaymentTest() {
    }

    @Test
    public void findOrdersByParametersTest() {
    }

    @Test
    public void findClientOrdersTest() {
    }

    @Test
    public void testCalculateOrderAmountTest() {
    }
}