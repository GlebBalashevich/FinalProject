package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.util.ParameterKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ClientsPaginationCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int pageNumber = (int) session.getAttribute(AttributeKey.CLIENTS_PAGE_NUMBER);
        String paginationDirection = request.getParameter(ParameterKey.PAGINATION_DIRECTION);

        if (paginationDirection.equals(ParameterKey.NEXT_PAGE)){
            session.setAttribute(AttributeKey.CLIENTS_PAGE_NUMBER, ++pageNumber);
        }
        if (paginationDirection.equals(ParameterKey.PREVIOUS_PAGE)){
            session.setAttribute(AttributeKey.CLIENTS_PAGE_NUMBER, --pageNumber);
        }

        return new Router((String) session.getAttribute(AttributeKey.CURRENT_PAGE));
    }
}
