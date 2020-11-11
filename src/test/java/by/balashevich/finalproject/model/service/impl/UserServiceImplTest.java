package by.balashevich.finalproject.model.service.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.dao.impl.UserDaoImpl;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;
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

import static by.balashevich.finalproject.util.ParameterKey.*;
import static org.testng.Assert.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*"})
@PrepareForTest({UserDaoImpl.class})
public class UserServiceImplTest {
    UserDaoImpl userDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(UserDaoImpl.class);
        userDao = Mockito.mock(UserDaoImpl.class);
        Mockito.when(UserDaoImpl.getInstance()).thenReturn(userDao);
    }

    @DataProvider(name = "userData")
    public Object[][] createUserData() {
        return new Object[][]{
                {new HashMap<>(Map.of(EMAIL, "user@bestcompany.by", PASSWORD, "useR1234",
                        CONFIRM_PASSWORD, "useR1234", FIRST_NAME, "Ivan", SECOND_NAME, "IVANOV",
                        DRIVER_LICENSE, "7AB123456", PHONE_NUMBER, "375292020327")), true},
                {new HashMap<>(), false}
        };
    }

    @Test(dataProvider = "userData")
    public void addTest(Map<String, String> clientParameters, boolean expected) {
        try {
            Mockito.when(userDao.add(Mockito.any())).thenReturn(true);

            UserServiceImpl userService = new UserServiceImpl();
            boolean actual = userService.add(clientParameters);
            assertEquals(actual, expected);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void addExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(userDao.add(Mockito.any())).thenThrow(daoProjectException);

        UserServiceImpl userService = new UserServiceImpl();
        userService.add(new HashMap<>(Map.of(EMAIL, "user@bestcompany.by", PASSWORD, "useR1234",
                CONFIRM_PASSWORD, "useR1234", FIRST_NAME, "Ivan", SECOND_NAME, "IVANOV",
                DRIVER_LICENSE, "7AB123456", PHONE_NUMBER, "375292020327")));
    }

    @DataProvider(name = "activateData")
    public Object[][] createActivateData() {
        return new Object[][]{
                {"user@bestcompany.by", "PENDING", true},
                {"user@bestcompany.by", "ACTIVE", false}
        };
    }

    @Test(dataProvider = "activateData")
    public void activateClientTest(String email, String status, boolean expected) {
        try {
            Mockito.when(userDao.findStatusByEmail(Mockito.any())).thenReturn(Client.Status.valueOf(status));
            Mockito.when(userDao.updateClientStatus(Mockito.anyString(), Mockito.any())).thenReturn(true);

            UserServiceImpl userService = new UserServiceImpl();
            boolean actual = userService.activateClient(email);
            assertEquals(actual, expected);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @DataProvider(name = "statusData")
    public Object[][] createStatusData() {
        return new Object[][]{
                {"UNKNOWN", false},
                {"PENDING", true}
        };
    }

    @Test(dataProvider = "statusData")
    public void updateClientStatusTest(String statusData, boolean expected) {
        try {
            Mockito.when(userDao.updateClientStatus(Mockito.any(), Mockito.any())).thenReturn(true);
            UserServiceImpl userService = new UserServiceImpl();
            Client client = new Client();
            client.setEmail("user@bestcompany.by");
            boolean actual = userService.updateClientStatus(client, statusData);
            assertEquals(actual, expected);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void updateClientStatusExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(userDao.updateClientStatus(Mockito.anyString(), Mockito.any())).thenThrow(daoProjectException);
        UserServiceImpl userService = new UserServiceImpl();
        Client client = new Client();
        client.setEmail("user@bestcompany.by");
        userService.updateClientStatus(client, "PENDING");
    }

    @DataProvider(name = "emailData")
    public Object[][] createEmailData() {
        User user = new User();
        user.setRole(User.Role.CLIENT);
        return new Object[][]{
                {"user@bestcompany.by", Optional.of(user)},
                {"incorrectemail", Optional.empty()}
        };
    }

    @Test(dataProvider = "emailData")
    public void findUserByEmailTest(String email, Optional<User> expected) {
        try {
            Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(expected);
            UserServiceImpl userService = new UserServiceImpl();
            Optional<User> actual = userService.findUserByEmail(email);
            assertEquals(actual, expected);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void findUserByEmailExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(userDao.findByEmail(Mockito.anyString())).thenThrow(daoProjectException);
        UserServiceImpl userService = new UserServiceImpl();
        userService.findUserByEmail("email@email.by");
    }

    @DataProvider(name = "clientStatusData")
    public Object[][] createClientStatusData() {
        Client client1 = new Client();
        client1.setRole(User.Role.CLIENT);
        client1.setStatus(Client.Status.ACTIVE);
        Client client2 = new Client();
        client2.setRole(User.Role.CLIENT);
        client2.setStatus(Client.Status.PENDING);
        return new Object[][]{
                {"ACTIVE", new ArrayList<>(List.of(client1, client2))},
                {"Unknow", new ArrayList<>()}
        };
    }

    @Test(dataProvider = "clientStatusData")
    public void findClientsByStatusTest(String statusData, List<Client> expected) {
        try {
            Mockito.when(userDao.findClientsByStatus(Mockito.any())).thenReturn(expected);
            Mockito.when(userDao.findAllClients()).thenReturn(expected);
            UserServiceImpl userService = new UserServiceImpl();
            List<Client> actual = userService.findClientsByStatus(statusData);
            assertEquals(actual, expected);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void findClientsByStatusExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(userDao.findClientsByStatus(Mockito.any())).thenThrow(daoProjectException);
        Mockito.when(userDao.findAllClients()).thenThrow(daoProjectException);
        UserServiceImpl userService = new UserServiceImpl();
        userService.findClientsByStatus("statusData");
    }

    @DataProvider(name = "authorizeData")
    public Object[][] createAuthorizeData() {
        return new Object[][]{
                {"user@mail.ru", "User1234", "bd5cf8347e036cabe6cd37323186a02ef6c3589d19daaee31eeb2ae3b1507ebe", true},
                {"user@mail.ru", "User1234", "User1234", false},
                {"user@mail", "User1234", "bd5cf8347e036cabe6cd37323186a02ef6c3589d19daaee31eeb2ae3b1507ebe", false},
                {"user@mail.ru", "", "bd5cf8347e036cabe6cd37323186a02ef6c3589d19daaee31eeb2ae3b1507ebe", false},
        };
    }

    @Test(dataProvider = "authorizeData")
    public void authorizeUserTest(String email, String password, String encPassword, boolean expected) {
        try {
            Mockito.when(userDao.findPasswordByEmail(Mockito.any())).thenReturn(encPassword);
            UserServiceImpl userService = new UserServiceImpl();
            boolean actual = userService.authorizeUser(email, password);
            assertEquals(actual, expected);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void authorizeUserExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(userDao.findPasswordByEmail(Mockito.any())).thenThrow(daoProjectException);
        UserServiceImpl userService = new UserServiceImpl();
        userService.authorizeUser("user@mail.ru", "User1234");
    }

    @DataProvider(name = "existUserData")
    public Object[][] createExistUserData() {
        return new Object[][]{
                {"user@mail.ru", true},
                {"unknown", false}
        };
    }

    @Test(dataProvider = "existUserData")
    public void existUserTest(String email, boolean expected) {
        try {
            Mockito.when(userDao.existEmail(Mockito.anyString())).thenReturn(email);
            UserServiceImpl userService = new UserServiceImpl();
            boolean actual = userService.existUser(email);
            assertEquals(actual, expected);
        } catch (DaoProjectException | ServiceProjectException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = ServiceProjectException.class)
    public void existUserExceptionTest() throws DaoProjectException, ServiceProjectException {
        DaoProjectException daoProjectException = new DaoProjectException();
        Mockito.when(userDao.existEmail(Mockito.anyString())).thenThrow(daoProjectException);
        UserServiceImpl userService = new UserServiceImpl();
        userService.existUser("user@mail.ru");
    }
}