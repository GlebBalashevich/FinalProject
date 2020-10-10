package by.balashevich.finalproject.builder;

import by.balashevich.finalproject.model.entity.Car;

import static by.balashevich.finalproject.util.ParameterKey.*;

import java.util.Map;

public class CarBuilder {
    private CarBuilder() {
    }

    public static Car buildCar(Map<String, Object> carParameters) {
        Car buildingCar = new Car();

        if (carParameters.containsKey(CAR_ID)){
            buildingCar.setCarId((long) carParameters.get(CAR_ID));
        }
        buildingCar.setModel((String) carParameters.get(MODEL));
        buildingCar.setType((Car.Type) carParameters.get(CAR_TYPE));
        buildingCar.setNumberSeats((int) carParameters.get(NUMBER_SEATS));
        buildingCar.setRentCost((int) carParameters.get(RENT_COST));
        buildingCar.setFuelType((Car.FuelType) carParameters.get(FUEL_TYPE));
        buildingCar.setFuelConsumption((int) carParameters.get(FUEL_CONSUMPTION));
        buildingCar.setAvailable((boolean) carParameters.get(CAR_AVAILABLE));

        return buildingCar;
    }
}
