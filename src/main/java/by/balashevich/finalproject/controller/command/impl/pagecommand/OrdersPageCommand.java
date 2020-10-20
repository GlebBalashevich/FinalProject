package by.balashevich.finalproject.controller.command.impl.pagecommand;

import by.balashevich.finalproject.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class OrdersPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PageName.ORDERS.getPath();
    }
}
