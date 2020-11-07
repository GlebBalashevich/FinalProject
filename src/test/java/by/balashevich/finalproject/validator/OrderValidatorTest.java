package by.balashevich.finalproject.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrderValidatorTest {

    @DataProvider(name = "dateData")
    public Object[][] createDateData(){
        return new Object[][]{
                {"2020-12-31", true},
                {"2020-02-29", true},
                {"2020-01-00", false},
                {"20-12-31", false},
                {"", false},
                {null, false},
                {"2020.09.08", false},
                {"2020/09/08", false},
        };
    }

    @Test (dataProvider = "dateData")
    public void validateDateTest(String date, boolean expected) {
        boolean actual = OrderValidator.validateDate(date);

        assertEquals(actual, expected);
    }

    @DataProvider(name = "statusData")
    public Object[][] createStatusData(){
        return new Object[][]{
                {"pending", true},
                {"AWAITING_PAYMENT", true},
                {"0", false},
                {"anyword", false},
                {"", false},
                {null, false},
        };
    }

    @Test (dataProvider = "statusData")
    public void validateStatusTest(String status, boolean expected) {
        boolean actual = OrderValidator.validateStatus(status);

        assertEquals(actual, expected);
    }
}