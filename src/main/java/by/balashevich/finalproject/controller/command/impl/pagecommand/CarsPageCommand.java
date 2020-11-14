package by.balashevich.finalproject.controller.command.impl.pagecommand;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The Cars page command.
 * <p>
 * The command responsible for forwarding user to the cars page
 * if the user is an administrator, forwarding occurs to admin version of cars page
 * if the user is an client or guest, forwarding occurs to client version of cars page
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class CarsPageCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User.Role role = (User.Role) session.getAttribute(AttributeKey.USER_ROLE);
        Router router = new Router(switch (role) {
            case CLIENT, GUEST -> PageName.CLIENT_CARS.getPath();
            case ADMIN -> PageName.ADMIN_CARS.getPath();
        });

        return router;
    }
}
