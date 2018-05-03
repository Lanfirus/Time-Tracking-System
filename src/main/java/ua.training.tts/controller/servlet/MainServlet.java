package ua.training.tts.controller.servlet;

import ua.training.tts.controller.command.Command;
import ua.training.tts.constant.controller.Servlet;
import ua.training.tts.controller.command.Login;
import ua.training.tts.controller.command.Logout;
import ua.training.tts.controller.command.Registration;
import ua.training.tts.controller.command.pageNavigation.LoginForm;
import ua.training.tts.controller.command.pageNavigation.Main;
import ua.training.tts.controller.command.pageNavigation.RegistrationForm;
import ua.training.tts.controller.command.pageNavigation.RegistrationSuccessful;
import ua.training.tts.util.DBInitializator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Execute role of controller class switching user requests to respective command implementation.
 * In case of incorrect request forward user to the main (index) page.
 */
@WebServlet(Servlet.URL_PATTERN)
public class MainServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    /**
     * Initializes DB with time_tracking schema.
     * Creates full DB structure required for program to operate and fills it with some mock data.
     * To operate requires login and password to be set as 'admin' or to be changed to appropriate values in
     * constant/DBParameters file.
     * Be careful, as initialization drops any existing schema named time_tracking.
     */
    public void init(){
        DBInitializator.getInstance().initializeDB();
        {
            commands.put(Servlet.MAIN, new Main());
            commands.put(Servlet.REGISTRATION_FORM, new RegistrationForm());
            commands.put(Servlet.REGISTRATION, new Registration());
            commands.put(Servlet.REGISTRATION_SUCCESSFUL, new RegistrationSuccessful());
            commands.put(Servlet.LOGIN_FORM, new LoginForm());
            commands.put(Servlet.LOGIN, new Login());
            commands.put(Servlet.LOGOUT, new Logout());
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    /**
     * Processing method for both Get and Post requests.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI().replaceAll(Servlet.URI_REPLACE_PATTERN, Servlet.REPLACEMENT);
        Command command = commands.getOrDefault(path, x-> Servlet.INDEX_PAGE);
        String page = command.execute(request);
        pageProcessing(page, request, response);
    }

    private void pageProcessing(String page, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        if(page.contains(Servlet.REDIRECT)){
            response.sendRedirect(page.replace(Servlet.REDIRECT, Servlet.REPLACEMENT));
        }
        else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}