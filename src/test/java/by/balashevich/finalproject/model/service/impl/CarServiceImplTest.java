package by.balashevich.finalproject.model.service.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.dao.impl.CarDaoImpl;
import by.balashevich.finalproject.model.entity.Car;
import by.balashevich.finalproject.util.ParameterKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*"})
@PrepareForTest({CarDaoImpl.class})
public class CarServiceImplTest {
    CarDaoImpl carDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(CarDaoImpl.class);
        carDao = Mockito.mock(CarDaoImpl.class);
        Mockito.when(CarDaoImpl.getInstance()).thenReturn(carDao);
    }

    @Test
    public void addCarTest() {
        try {
            Mockito.when(carDao.add(Mockito.any())).thenReturn(true);

            CarServiceImpl carService = new CarServiceImpl();
            Map<String, String> carParameters = new HashMap<>();
            carParameters.put(ParameterKey.MODEL, "Volvo XC90");
            carParameters.put(ParameterKey.CAR_TYPE, "suv");
            carParameters.put(ParameterKey.NUMBER_SEATS, "7");
            carParameters.put(ParameterKey.RENT_COST, "180");
            carParameters.put(ParameterKey.CAR_AVAILABLE, "true");
            carParameters.put(ParameterKey.FUEL_TYPE, "diesel");
            carParameters.put(ParameterKey.FUEL_CONSUMPTION, "8");
            boolean actual = carService.addCar(carParameters);
            assertTrue(actual);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void addCarExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(carDao.add(Mockito.any())).thenThrow(daoProjectException);

        CarServiceImpl carService = new CarServiceImpl();
        Map<String, String> carParameters = new HashMap<>();
        carParameters.put(ParameterKey.MODEL, "Volvo XC90");
        carParameters.put(ParameterKey.CAR_TYPE, "suv");
        carParameters.put(ParameterKey.NUMBER_SEATS, "7");
        carParameters.put(ParameterKey.RENT_COST, "180");
        carParameters.put(ParameterKey.CAR_AVAILABLE, "true");
        carParameters.put(ParameterKey.FUEL_TYPE, "diesel");
        carParameters.put(ParameterKey.FUEL_CONSUMPTION, "8");
        carService.addCar(carParameters);
    }

    @Test
    public void updateCarTest() {
        try {
            Mockito.when(carDao.update(Mockito.any())).thenReturn(true);

            CarServiceImpl carService = new CarServiceImpl();
            Map<String, String> carParameters = new HashMap<>();
            carParameters.put(ParameterKey.RENT_COST, "180");
            carParameters.put(ParameterKey.CAR_AVAILABLE, "true");
            Car car = new Car();
            boolean actual = carService.updateCar(car, carParameters);
            assertTrue(actual);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void updateCarExceptionTest() throws ServiceProjectException, DaoProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(carDao.update(Mockito.any())).thenThrow(daoProjectException);

        CarServiceImpl carService = new CarServiceImpl();
        Map<String, String> carParameters = new HashMap<>();
        Car car = new Car();
        carService.updateCar(car, carParameters);
    }

    @Test
    public void findCarByIdTest() {
        try {
            Optional<Car> expected = Optional.of(new Car());
            Mockito.when(carDao.findById(Mockito.anyLong())).thenReturn(expected);

            CarServiceImpl carService = new CarServiceImpl();
            Optional<Car> actual = carService.findCarById(1L);
            assertEquals(actual, expected);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void findCarByIdExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(carDao.findById(Mockito.anyLong())).thenThrow(daoProjectException);

        CarServiceImpl carService = new CarServiceImpl();
        carService.findCarById(1L);
    }

    @DataProvider(name = "orderCarsData")
    public Object[][] createOrderCarsData() {
        Map<String, String> parametersCycle1 = new HashMap<>();
        parametersCycle1.put(ParameterKey.DATE_FROM, "2045-12-31");
        parametersCycle1.put(ParameterKey.DATE_TO, "2046-01-05");
        Map<String, String> parametersCycle2 = new HashMap<>();
        parametersCycle2.put(ParameterKey.DATE_FROM, "20-12-31");
        parametersCycle2.put(ParameterKey.DATE_TO, "2046-01-ab");
        Map<String, String> parametersCycle3 = new HashMap<>();
        parametersCycle3.put(ParameterKey.DATE_FROM, "2046-01-05");
        parametersCycle3.put(ParameterKey.DATE_TO, "2045-12-31");
        return new Object[][]{
                {parametersCycle1, new ArrayList<>()},
                {parametersCycle2, null},
                {parametersCycle3, null},
        };
    }

    @Test(dataProvider = "orderCarsData")
    public void findAvailableOrderCarsTest(Map<String, String> orderCarsParameters, List<Car> expected) {
        try {
            Mockito.when(carDao.findAvailableOrderCars(Mockito.any())).thenReturn(new ArrayList<>());

            CarServiceImpl carService = new CarServiceImpl();
            List<Car> actual = carService.findAvailableOrderCars(orderCarsParameters);
            assertEquals(actual, expected);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void findAvailableOrderCarsExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(carDao.findAvailableOrderCars(Mockito.any())).thenThrow(daoProjectException);

        CarServiceImpl carService = new CarServiceImpl();
        Map<String, String> orderCarsParameters = new HashMap<>();
        orderCarsParameters.put(ParameterKey.DATE_FROM, "2045-12-31");
        orderCarsParameters.put(ParameterKey.DATE_TO, "2046-01-05");
        carService.findAvailableOrderCars(orderCarsParameters);
    }

    @Test
    public void findCarsByParametersTest() {
        try {
            List<Car> expected = new ArrayList<>();
            Mockito.when(carDao.findCarsByParameters(Mockito.any())).thenReturn(expected);

            CarServiceImpl carService = new CarServiceImpl();
            Map<String, String> carParameters = new HashMap<>();
            carParameters.put(ParameterKey.CAR_TYPE, "suv");
            carParameters.put(ParameterKey.CAR_AVAILABLE, "true");
            List<Car> actual = carService.findCarsByParameters(carParameters);
            assertEquals(actual, expected);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void findCarsByParametersExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(carDao.findCarsByParameters(Mockito.any())).thenThrow(daoProjectException);

        CarServiceImpl carService = new CarServiceImpl();
        Map<String, String> carParameters = new HashMap<>();
        carParameters.put(ParameterKey.CAR_TYPE, "suv");
        carParameters.put(ParameterKey.CAR_AVAILABLE, "true");
        carService.findCarsByParameters(carParameters);
    }
}