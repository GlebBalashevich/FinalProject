package by.balashevich.finalproject.model.entity;

import java.time.LocalDate;
import java.util.Arrays;

public class Order extends Entity {
    public enum Status {
        PENDING("Pending"),
        AWAITING_PAYMENT("Awaiting Payment"),
        ACTIVE("Active"),
        COMPLETED("Completed");

        private String title;

        Status(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public static Status getStatus(int index) {
            return Arrays.stream(Status.values()).filter(o -> o.ordinal() == index).findFirst().get();
        }
    }

    private long orderId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int amount;
    private Status status;
    private Client client;
    private Car car;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        return orderId == order.orderId
                && (dateFrom == order.dateFrom) || (dateFrom != null && dateFrom.equals(order.dateFrom))
                && (dateTo == order.dateTo) || (dateTo != null && dateTo.equals(order.dateTo))
                && amount == order.amount
                && status == order.status
                && (client == order.client) || (client != null && client.equals(order.client))
                && (car == order.car) || (car != null && car.equals(order.car));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 37 * result + Long.hashCode(orderId);
        result += 37 * result + (dateFrom == null ? 0 : dateFrom.hashCode());
        result += 37 * result + (dateTo == null ? 0 : dateTo.hashCode());
        result += 37 * result + amount;
        result += 37 * result + (status == null ? 0 : status.hashCode());
        result += 37 * result + (client == null ? 0 : client.hashCode());
        result += 37 * result + (car == null ? 0 : car.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return String.format("Order %d: dateFrom %s, dateTo %s, amount %d, status %s, " +
                        "client %s, car %s",
                orderId, dateFrom, dateTo, amount, status.name(), client, car);
    }
}
