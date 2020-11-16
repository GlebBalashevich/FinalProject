package by.balashevich.finalproject.controller.command.impl.pagecommand;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * The Register page command.
 * <p>
 * Forwarding not authorized and not registered user to the register page.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class RegisterPageCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PageName.REGISTER.getPath());
    }
}
