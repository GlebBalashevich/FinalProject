package by.balashevich.finalproject.model.entity;

import java.util.Arrays;

public class Vehicle extends Entity{
    private long vehicleId;
    private String model;
    private VehicleType type;
    private boolean available;
    private int rentCost;

    public enum VehicleType{
        SUV,
        SEDAN,
        MINIVAN;

        public static VehicleType getVehicleType(int index){ //todo is it ok method??
            return Arrays.stream(VehicleType.values()).filter(r -> r.ordinal() == index).findFirst().get();
        }
    }

    public Vehicle(long vehicleId, String model, VehicleType type, boolean available, int rentCost){
        this.vehicleId = vehicleId;
        this.model = model;
        this.type = type;
        this.available = available;
        this.rentCost = rentCost;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getRentCost() {
        return rentCost;
    }

    public void setRentCost(int rentCost) {
        this.rentCost = rentCost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()){
            return false;
        }

        Vehicle vehicle = (Vehicle) obj;

        return vehicleId == vehicle.vehicleId
                && model.equals(vehicle.model)
                && type == vehicle.type
                && available == vehicle.available
                && rentCost == vehicle.rentCost;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 37 * result + Long.hashCode(vehicleId);
        result += 37 * result + model.hashCode();
        result += 37 * result + type.hashCode();
        result += 37 * result + (available ? 1 : 0);
        result += 37 * result + rentCost;

        return result;
    }

    @Override
    public String toString() {
        return String.format("Vehicle %d: model %s, type %s, is available %s, rent cost %d",
                vehicleId, model, type.name(), available, rentCost);
    }
}
