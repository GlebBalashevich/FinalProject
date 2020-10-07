package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.SessionRequestContent;
import by.balashevich.finalproject.controller.command.ActionCommand;

import javax.servlet.http.HttpSession;

import static by.balashevich.finalproject.controller.command.AttributeKey.*;

public class SwitchLocaleCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent requestContent) {
        HttpSession session = requestContent.getSession();
        String locale = requestContent.getParameter(LOCALE);
        session.setAttribute(LOCALE, locale);
        return (String) session.getAttribute(CURRENT_PAGE);
    }
}
