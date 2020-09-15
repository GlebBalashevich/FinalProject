package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.SessionRequestContent;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;

public class MoveLoginPage implements ActionCommand {
    @Override
    public String execute(SessionRequestContent requestContent) {
        return PageName.LOGIN.getPath();
    }
}
