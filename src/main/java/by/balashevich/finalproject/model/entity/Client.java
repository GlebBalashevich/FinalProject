package by.balashevich.finalproject.model.entity;

public class Client extends User {
    private String firstName;
    private String secondName;
    private String driverLicense;
    private String email;
    private int phoneNumber;
    private ClientStatus status;

    public Client(String login, String firstName, String secondName,
                  String driverLicense, String email, int phoneNumber, ClientStatus status) {
        super(login);
        this.firstName = firstName;
        this.secondName = secondName;
        this.driverLicense = driverLicense;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public Client(long userId, String login, String firstName, String secondName,
                  String driverLicense, String email, int phoneNumber, ClientStatus status) {
        super(userId, login);
        this.firstName = firstName;
        this.secondName = secondName;
        this.driverLicense = driverLicense;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
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
                && email.equals(client.email)
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
        result += 37 * result + email.hashCode();
        result += 37 * result + phoneNumber;
        result += 37 * result + status.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return String.format("Client %s, first name %s, second name %s, driver license %s, email %s, " +
                        "phone number %d, status %s", super.toString(), firstName, secondName, driverLicense,
                email, phoneNumber, status.name());
    }
}
