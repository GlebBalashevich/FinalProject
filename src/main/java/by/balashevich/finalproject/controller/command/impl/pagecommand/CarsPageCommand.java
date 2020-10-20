package by.balashevich.finalproject.controller.command.impl.pagecommand;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.model.entity.Car;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CarsPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute(AttributeKey.USER);
        String page = switch (user.getRole()){
            case CLIENT -> PageName.CLIENT_CARS.getPath();
            case ADMIN ->  PageName.ADMIN_CARS.getPath();
        };
        Car.Type[] types = Car.Type.values();
        request.setAttribute(AttributeKey.CAR_TYPE, types);

        return page;
    }
}
