package by.balashevich.finalproject.controller.command;

import by.balashevich.finalproject.controller.command.impl.*;

public enum CommandType {
    LOG_IN_USER(new LoginCommand()),
    MOVE_LOGIN_PAGE(new MoveLoginPage()),
    MOVE_REGISTER_PAGE(new MoveRegisterPage()),
    MOVE_HOME_PAGE(new MoveHomePage()),
    REGISTER_CLIENT(new RegisterClientCommand());

    private ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
