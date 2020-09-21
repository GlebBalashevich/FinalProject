package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.SessionRequestContent;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;

import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent requestContent) {
        HttpSession session = requestContent.getSession();
        session.invalidate();
        return PageName.HOME.getPath();
    }
}
