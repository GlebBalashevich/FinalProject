package by.balashevich.finalproject.controller.command.impl.pagecommand;

public enum PageName {
    INDEX("/index.jsp"),
    LOGIN("/jsp/login.jsp"),
    REGISTER("/jsp/register.jsp"),
    ERROR("/jsp/error.jsp"),
    HOME("/jsp/home.jsp"),
    CLIENT_CARS("/jsp/cars.jsp"),
    CAR_CARD("/jsp/car_card.jsp"),
    NOTIFICATION("/jsp/notification.jsp"),
    USERS("/jsp/admin/users.jsp"),
    ORDERS("/jsp/admin/orders.jsp"),
    ADMIN_CARS("/jsp/admin/cars.jsp"),
    CREATE_CAR("/jsp/admin/create_car.jsp");

    private String path;

    PageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
