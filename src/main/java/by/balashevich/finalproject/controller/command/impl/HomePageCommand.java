package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.SessionRequestContent;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;

import javax.servlet.http.HttpSession;

public class HomePageCommand implements ActionCommand {
    private static final String LOCALE = "locale";
    private static final String DEFAULT_LOCALE = "en-US";

    @Override
    public String execute(SessionRequestContent requestContent) {
        HttpSession session = requestContent.getSession();
        if (session.isNew()){
            session.setAttribute(LOCALE, DEFAULT_LOCALE);
        }
        return PageName.HOME.getPath();
    }
}
