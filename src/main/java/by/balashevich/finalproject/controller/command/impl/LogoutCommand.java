package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The type Logout command.
 * <p>
 * Processes a request to terminate a session with a user. The session is
 * no longer valid, the user is forwarding to the home page.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class LogoutCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return new Router(PageName.INDEX.getPath());
    }
}
