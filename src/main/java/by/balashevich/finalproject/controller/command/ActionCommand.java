package by.balashevich.finalproject.controller.command;

import by.balashevich.finalproject.controller.Router;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Action command.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public interface ActionCommand {
    /**
     * Execute router.
     *
     * @param request the request
     * @return the router
     */
    Router execute(HttpServletRequest request);
}
