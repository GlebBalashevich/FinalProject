package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserOfficePageCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeKey.USER);
        String page = switch (user.getRole()){
            case ADMIN:
                yield PageName.ADMIN_OFFICE.getPath();
            case CLIENT:
                yield PageName.CLIENT_OFFICE.getPath();
            case MANAGER:
                yield PageName.MANAGER_OFFICE.getPath();
        };

        return page;
    }
}
