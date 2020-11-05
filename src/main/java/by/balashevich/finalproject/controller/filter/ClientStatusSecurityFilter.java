package by.balashevich.finalproject.controller.filter;

import by.balashevich.finalproject.controller.command.*;
import by.balashevich.finalproject.controller.command.impl.EmptyCommand;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;
import by.balashevich.finalproject.util.ParameterKey;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebFilter(urlPatterns = "/CarBook")
public class ClientStatusSecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        User.Role role = (User.Role) session.getAttribute(AttributeKey.USER_ROLE);
        String commandName = request.getParameter(ParameterKey.COMMAND);
        Set<CommandType> commandTypeSet = null;

        if (role != null) {
            if (role == User.Role.CLIENT) {
                Client client = (Client) session.getAttribute(AttributeKey.USER);
                ActionCommand command = CommandProvider.defineCommand(commandName);
                if (command.getClass() != EmptyCommand.class) {
                    commandTypeSet = switch (client.getStatus()) {
                        case PENDING -> CommandClientStatusAccess.PENDING.getAccessCommands();
                        case ACTIVE -> CommandClientStatusAccess.ACTIVE.getAccessCommands();
                        case BLOCKED -> CommandClientStatusAccess.BLOCKED.getAccessCommands();
                    };
                }
                if (commandTypeSet != null && !commandTypeSet.contains(CommandType.valueOf(commandName.toUpperCase()))) {
                    if (client.getStatus() == Client.Status.PENDING) {
                        request.setAttribute(AttributeKey.ACTIVATE_ACCOUNT, true);
                    }
                    if (client.getStatus() == Client.Status.BLOCKED) {
                        request.setAttribute(AttributeKey.ACCOUNT_BLOCKED, true);
                    }
                    request.getRequestDispatcher(PageName.NOTIFICATION.getPath()).forward(request, response);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
