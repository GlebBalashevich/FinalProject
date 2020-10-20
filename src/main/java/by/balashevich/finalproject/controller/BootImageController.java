package by.balashevich.finalproject.controller;

import by.balashevich.finalproject.controller.command.impl.pagecommand.PageName;
import by.balashevich.finalproject.util.ParameterKey;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/boot_image_controller/*")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class BootImageController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final String UPLOAD_LOCATION = "upload.location";
    private static final String FILE_PARAMETER = "file";
    private static final String EMPTY = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String applicationFileDirectory = request.getServletContext().getInitParameter(UPLOAD_LOCATION);
        HttpSession session = request.getSession();
        String imageType = request.getParameter(ParameterKey.IMAGE_TYPE);
        String fileName = request.getPart(FILE_PARAMETER).getSubmittedFileName();
        Path path = Paths.get(applicationFileDirectory);
        Path fullPath = Paths.get(path + File.separator + fileName);

        if (Files.notExists(path)) {
            Files.createDirectory(path);
        }

        if (Files.notExists(fullPath)) {
            List<Part> parts = request.getParts().stream().filter(part -> FILE_PARAMETER.equals(part.getName()))
                    .collect(Collectors.toList());
            parts.forEach(part -> {
                try {
                    part.write(fullPath.toString());
                    session.setAttribute(imageType, fileName);
                } catch (IOException e) {
                    logger.log(Level.ERROR, "Error while loading File", e);
                    session.setAttribute(imageType, EMPTY);
                }
            });
        } else{
            session.setAttribute(imageType, EMPTY);
        }

        request.getRequestDispatcher(PageName.CREATE_CAR.getPath()).forward(request, response);
    }
}
