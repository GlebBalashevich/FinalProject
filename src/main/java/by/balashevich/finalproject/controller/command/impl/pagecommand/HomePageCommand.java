package by.balashevich.finalproject.controller.command.impl.pagecommand;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The Home page command.
 * <p>
 * Forwarding a user to the home page.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class HomePageCommand implements ActionCommand {
    private static final String DEFAULT_LOCALE = "en";

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute(AttributeKey.LOCALE, DEFAULT_LOCALE);
            session.setAttribute(AttributeKey.USER_ROLE, User.Role.GUEST);
        }

        return new Router(PageName.HOME.getPath());
    }
}
