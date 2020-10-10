package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.balashevich.finalproject.controller.command.AttributeKey.*;

public class SwitchLocaleCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(LOCALE);
        session.setAttribute(LOCALE, locale);
        return (String) session.getAttribute(CURRENT_PAGE);
    }
}
