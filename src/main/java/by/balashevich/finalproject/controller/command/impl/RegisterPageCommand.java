package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.SessionRequestContent;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;

public class RegisterPageCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent requestContent) {
        return PageName.REGISTER.getPath();
    }
}
