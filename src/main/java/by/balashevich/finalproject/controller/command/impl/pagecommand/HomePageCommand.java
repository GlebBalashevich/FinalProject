package by.balashevich.finalproject.controller.command.impl.pagecommand;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HomePageCommand implements ActionCommand {
    private static final String DEFAULT_LOCALE = "en";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute(AttributeKey.LOCALE, DEFAULT_LOCALE);
        }
        return PageName.HOME.getPath();
    }
}
