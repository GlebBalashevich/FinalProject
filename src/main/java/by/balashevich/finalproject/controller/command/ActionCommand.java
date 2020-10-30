package by.balashevich.finalproject.controller.command;

import by.balashevich.finalproject.controller.Router;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    Router execute(HttpServletRequest request);
}
