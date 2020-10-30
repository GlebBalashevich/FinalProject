package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SwitchLocaleCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(AttributeKey.LOCALE);
        session.setAttribute(AttributeKey.LOCALE, locale);
        return new Router((String) session.getAttribute(AttributeKey.CURRENT_PAGE));
    }
}
