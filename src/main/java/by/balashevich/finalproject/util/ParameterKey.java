package by.balashevich.finalproject.util;

public class ParameterKey {
    public static final String USER_ID = "user_id";                     //table users
    public static final String PASSWORD = "password";                   //table users
    public static final String CONFIRM_PASSWORD = "confirm_password";   //table users
    public static final String ROLE = "user_role";                      //table users
    public static final String FIRST_NAME = "first_name";               //table users
    public static final String SECOND_NAME = "second_name";             //table users
    public static final String DRIVER_LICENSE = "driver_license";       //table users
    public static final String EMAIL = "email";                         //table users
    public static final String PHONE_NUMBER = "phone_number";           //table users
    public static final String STATUS = "status";                       //table users

    public static final String CAR_ID = "car_id";                       //table cars
    public static final String MODEL = "model";                         //table cars
    public static final String CAR_TYPE = "type";                       //table cars
    public static final String NUMBER_SEATS = "number_seats";           //table cars
    public static final String RENT_COST = "rent_cost";                 //table cars
    public static final String FUEL_TYPE = "fuel_type";                 //table cars
    public static final String FUEL_CONSUMPTION = "fuel_consumption";   //table cars
    public static final String CAR_AVAILABLE = "is_available";       //table cars

    private ParameterKey(){}
}
