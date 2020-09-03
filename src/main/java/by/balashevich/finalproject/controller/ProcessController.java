package by.balashevich.finalproject.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/process_controller")
public class ProcessController extends HttpServlet {
    private static final String COMMAND_PARAMETER = "command";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandProvider commandProvider = new CommandProvider();
        ActionCommand command = commandProvider.defineCommand(request.getParameter(COMMAND_PARAMETER));
        SessionRequestContent sessionRequestContent = new SessionRequestContent(request);

        String page = command.execute(sessionRequestContent);
        sessionRequestContent.restoreRequest(request);
        request.getRequestDispatcher(page).forward(request, response);
    }
}
