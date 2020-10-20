package by.balashevich.finalproject.controller.command.impl.pagecommand;

import by.balashevich.finalproject.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class UsersPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PageName.USERS.getPath();
    }
}
