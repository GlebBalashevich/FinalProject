package by.balashevich.finalproject.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The type Upload image controller.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
@WebServlet(urlPatterns = "/image/*")
public class UploadImageController extends HttpServlet {
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String UPLOAD_DIRECTORY = "upload.location";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    /**
     * Process request.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = request.getPathInfo().substring(1);
        String directory = getServletContext().getInitParameter(UPLOAD_DIRECTORY);
        Path path = Paths.get(directory, filename);
        response.setHeader(CONTENT_TYPE, getServletContext().getMimeType(filename));
        response.setHeader(CONTENT_LENGTH, String.valueOf(Files.size(path)));
        Files.copy(path, response.getOutputStream());
    }
}
