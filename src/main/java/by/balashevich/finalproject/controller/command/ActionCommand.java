package by.balashevich.finalproject.controller.command;

import by.balashevich.finalproject.controller.SessionRequestContent;

public interface ActionCommand {
    String execute(SessionRequestContent requestContent);
}
