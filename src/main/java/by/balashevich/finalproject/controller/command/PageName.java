package by.balashevich.finalproject.controller.command;

public enum PageName {
    INDEX("/index.jsp"),
    LOGIN("/jsp/login.jsp"),
    REGISTER("/jsp/register.jsp"),
    HOME("/jsp/home.jsp"),
    NOTIFICATION("/jsp/notification.jsp"),
    CLIENT_CARS("/jsp/cars.jsp"),                                       //client page
    CLIENT_PAYMENT("/jsp/payment.jsp"),                                 //client page
    CLIENT_ORDERS("/jsp/orders.jsp"),                                   //client page
    CAR_CARD("/jsp/car_card.jsp"),                                      //client page
    ADMIN_USERS("/jsp/admin/users.jsp"),                                //admin page
    ADMIN_ORDERS("/jsp/admin/orders.jsp"),                              //admin page
    ADMIN_CARS("/jsp/admin/cars.jsp"),                                  //admin page
    CREATE_CAR("/jsp/admin/create_car.jsp"),                            //admin page
    ERROR_404("/jsp/error/error404.jsp"),                               //error page
    ERROR_500("/jsp/error/error500.jsp");                               //error page

    private String path;

    PageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
