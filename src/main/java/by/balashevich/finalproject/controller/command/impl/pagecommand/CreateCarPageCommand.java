package by.balashevich.finalproject.controller.command.impl.pagecommand;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;

import javax.servlet.http.HttpServletRequest;

public class CreateCarPageCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PageName.CREATE_CAR.getPath());
    }
}
