package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.model.entity.Car;

import javax.servlet.http.HttpServletRequest;

public class CarsPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        Car.Type[] types = Car.Type.values();
        request.setAttribute(AttributeKey.CAR_TYPE, types);
        return PageName.CARS.getPath();
    }
}
