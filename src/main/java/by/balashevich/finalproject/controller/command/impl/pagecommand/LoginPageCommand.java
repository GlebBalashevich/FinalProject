package by.balashevich.finalproject.controller.command.impl.pagecommand;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * The Login page command.
 * <p>
 * Forwarding a user to the login page.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class LoginPageCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PageName.LOGIN.getPath());
    }
}
