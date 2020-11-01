package by.balashevich.finalproject.model.entity;

import java.util.Arrays;

public class Car extends Entity {
    public enum Type {
        SUV ("Suv"),
        SEDAN ("Sedan"),
        MINIVAN ("Minivan");

        private String title;

        Type (String title){
            this.title = title;
        }

        public String getTitle(){
            return title;
        }

        public static Type getType(int index) {
            return Arrays.stream(Type.values()).filter(v -> v.ordinal() == index).findFirst().get();
        }
    }

    public enum FuelType {
        DIESEL ("Diesel"),
        PETROL ("Petrol");

        private String title;

        FuelType (String title){
            this.title = title;
        }

        public String getTitle(){
            return title;
        }

        public static FuelType getFuelType(int index) {
            return Arrays.stream(FuelType.values()).filter(f -> f.ordinal() == index).findFirst().get();
        }
    }

    private long carId;
    private String model;
    private Type type;
    private int numberSeats;
    private int rentCost;
    private FuelType fuelType;
    private int fuelConsumption;
    private CarView carView;
    private boolean available;

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(int numberSeats) {
        this.numberSeats = numberSeats;
    }

    public int getRentCost() {
        return rentCost;
    }

    public void setRentCost(int rentCost) {
        this.rentCost = rentCost;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public CarView getCarView() {
        return carView;
    }

    public void setCarView(CarView carView) {
        this.carView = carView;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Car car = (Car) obj;

        return carId == car.carId
                && (model != null && model.equals(car.model))
                && type == car.type
                && numberSeats == car.numberSeats
                && rentCost == car.rentCost
                && fuelType == car.fuelType
                && fuelConsumption == car.fuelConsumption
                && (carView == car.carView) || (carView != null && carView.equals(car.carView))
                && available == car.available;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 37 * result + Long.hashCode(carId);
        result += 37 * result + (model == null ? 0 : model.hashCode());
        result += 37 * result + (type == null ? 0 : type.hashCode());
        result += 37 * result + numberSeats;
        result += 37 * result + rentCost;
        result += 37 * result + (fuelType == null ? 0 : fuelType.hashCode());
        result += 37 * result + fuelConsumption;
        result += 37 * result + (carView == null ? 0 : carView.hashCode());
        result += 37 * result + (available ? 1 : 0);

        return result;
    }

    @Override
    public String toString() {
        return String.format("Car %d: model %s, type %s, numberSeats %d, rentCost %d, " +
                        "fuelType %s, fuelConsumption %d, carView %s, is available %s",
                carId, model, type.name(), numberSeats, rentCost, fuelType.name(), fuelConsumption, carView, available);
    }
}
