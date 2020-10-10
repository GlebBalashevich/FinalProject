package by.balashevich.finalproject.model.entity;

import java.util.Arrays;

public class Client extends User {

    public enum Status {
        PENDING,
        ACTIVE,
        BLOCKED;

        public static Status getClientStatus(int index){
            return Arrays.stream(Status.values()).filter(s -> s.ordinal() == index).findFirst().get();
        }
    }

    private String firstName;
    private String secondName;
    private String driverLicense;
    private long phoneNumber;
    private Status status;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Client client = (Client) obj;

        return super.equals(obj)
                && firstName.equals(client.firstName)
                && secondName.equals(client.secondName)
                && driverLicense.equals(client.driverLicense)
                && phoneNumber == client.phoneNumber
                && status == client.status;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += super.hashCode();
        result += 37 * result + firstName.hashCode();
        result += 37 * result + secondName.hashCode();
        result += 37 * result + driverLicense.hashCode();
        result += 37 * result + phoneNumber;
        result += 37 * result + status.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return String.format("Client %s, first name %s, second name %s, driver license %s, " +
                        "phone number %d, status %s", super.toString(), firstName, secondName, driverLicense,
                phoneNumber, status.name());
    }
}
