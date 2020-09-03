package by.balashevich.finalproject.controller;

import by.balashevich.finalproject.controller.impl.LoginCommand;

public enum CommandType {
    LOG_IN_USER(new LoginCommand());

    private ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
