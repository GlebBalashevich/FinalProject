package by.balashevich.finalproject.controller.command.impl.pagecommand;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * The Users page command.
 * <p>
 * Forwarding admin to the users page.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class UsersPageCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PageName.ADMIN_USERS.getPath());
    }
}
