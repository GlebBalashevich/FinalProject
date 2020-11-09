package by.balashevich.finalproject.validator;

import by.balashevich.finalproject.util.ParameterKey;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class CarValidatorTest {

    @Test
    public void validateCarParametersTest() {
        Map<String, String> carParameters = new HashMap<>();
        carParameters.put(ParameterKey.MODEL, "Volvo XC90");
        carParameters.put(ParameterKey.CAR_TYPE, "suv");
        carParameters.put(ParameterKey.NUMBER_SEATS, "7");
        carParameters.put(ParameterKey.RENT_COST, "180");
        carParameters.put(ParameterKey.CAR_AVAILABLE, "true");
        carParameters.put(ParameterKey.FUEL_TYPE, "diesel");
        carParameters.put(ParameterKey.FUEL_CONSUMPTION, "8");
        boolean actual = CarValidator.validateCarParameters(carParameters);
        assertTrue(actual);
    }

    @DataProvider(name = "modelData")
    public Object[][] createModelData() {
        return new Object[][]{
                {"Volvo XC90", true},
                {"A", false},
                {"Волга", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "modelData")
    public void validateModelTest(String model, boolean expected) {
        boolean actual = CarValidator.validateModel(model);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "typeData")
    public Object[][] createTypeData() {
        return new Object[][]{
                {"SUV", true},
                {"Sedan", true},
                {"minivan", true},
                {null, false},
                {"", false},
                {"X-wing", false},
        };
    }

    @Test(dataProvider = "typeData")
    public void validateTypeTest(String type, boolean expected) {
        boolean actual = CarValidator.validateType(type);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "numberSeatsData")
    public Object[][] createNumberSeatsData() {
        return new Object[][]{
                {"1", true},
                {"50", true},
                {"51", false},
                {"0", false},
                {"111", false},
                {null, false},
                {"", false},
                {"two", false},
        };
    }

    @Test(dataProvider = "numberSeatsData")
    public void validateNumberSeatsTest(String numberSeats, boolean expected) {
        boolean actual = CarValidator.validateNumberSeats(numberSeats);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "rentCostData")
    public Object[][] createRentCostData() {
        return new Object[][]{
                {"50", true},
                {"10", true},
                {"200", true},
                {"510", false},
                {"5", false},
                {"0", false},
                {"One hundred", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "rentCostData")
    public void validateRentCostTest(String rentCost, boolean expected) {
        boolean actual = CarValidator.validateRentCost(rentCost);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "fuelTypeData")
    public Object[][] createFuelTypeData() {
        return new Object[][]{
                {"diesel", true},
                {"PETROL", true},
                {"0", false},
                {"1", false},
                {"nuclear", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "fuelTypeData")
    public void validateFuelTypeTest(String fuelType, boolean expected) {
        boolean actual = CarValidator.validateFuelType(fuelType);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "fuelConsumptionData")
    public Object[][] createFuelConsumptionData() {
        return new Object[][]{
                {"1", true},
                {"50", true},
                {"51", false},
                {"0", false},
                {"111", false},
                {null, false},
                {"", false},
                {"three", false},
        };
    }

    @Test(dataProvider = "fuelConsumptionData")
    public void validateFuelConsumptionTest(String fuelConsumption, boolean expected) {
        boolean actual = CarValidator.validateFuelConsumption(fuelConsumption);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "priceRangeData")
    public Object[][] createPriceRangeData() {
        return new Object[][]{
                {"10;200", true},
                {"1;20", false},
                {"abc;20", false},
                {"1;1", false},
                {"4444;4444", false},
                {null, false},
                {"", false},
                {"twenty;thirty", false},
        };
    }

    @Test(dataProvider = "priceRangeData")
    public void validatePriceRangeDataTest(String priceRange, boolean expected) {
        boolean actual = CarValidator.validatePriceRangeData(priceRange);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "isAvailableData")
    public Object[][] createIsAvailableData() {
        return new Object[][]{
                {"true", true},
                {"false", true},
                {"0", false},
                {"1", false},
                {"folse", false},
                {"try", false},
                {"", false},
                {null, false},
        };
    }

    @Test(dataProvider = "isAvailableData")
    public void validateAvailable(String availableData, boolean expected) {
        boolean actual = CarValidator.validateAvailable(availableData);
        assertEquals(actual, expected);
    }
}