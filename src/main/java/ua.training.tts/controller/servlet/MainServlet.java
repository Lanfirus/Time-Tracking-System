package ua.training.tts.controller.servlet;

import ua.training.tts.constant.controller.Servlet;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.controller.command.Command;
import ua.training.tts.controller.command.Login;
import ua.training.tts.controller.command.Logout;
import ua.training.tts.controller.command.Registration;
import ua.training.tts.controller.command.redirect.LoginForm;
import ua.training.tts.controller.command.redirect.MainPage;
import ua.training.tts.controller.command.redirect.RegistrationForm;
import ua.training.tts.controller.command.redirect.RegistrationSuccessfulPage;
import ua.training.tts.model.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Executes role of controller class switching user requests to respective command implementation.
 * In case of incorrect request forward user to the main (index) page.
 */
@WebServlet(Servlet.URL_PATTERN)
public class MainServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    
    public void init(){
        {
            commands.put(Servlet.MAIN, new MainPage());
            commands.put(Servlet.REGISTRATION_FORM, new RegistrationForm());
            commands.put(Servlet.REGISTRATION, new Registration(new EmployeeService()));
            commands.put(Servlet.REGISTRATION_SUCCESSFUL, new RegistrationSuccessfulPage());
            commands.put(Servlet.LOGIN_FORM, new LoginForm());
            commands.put(Servlet.LOGIN, new Login(new EmployeeService()));
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
     * Defines what Command class to use for each particular request based on request's URL.
     * Redirect user to main page in case of requesting non-existing resources.
     *
     * @param request               User's request from his browser.
     * @param response              Response to be send to user.
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI().replaceAll(Servlet.URI_REPLACE_PATTERN, Servlet.REPLACEMENT);
        Command command = commands.getOrDefault(path, x-> CommandParameters.REDIRECT + Servlet.SERVLET_MAIN);
        String page = command.execute(request);
        sendUserToPage(page, request, response);
    }

    /**
     * Defines how and where to send user according to link got from respective Command class.
     * User could be forwarded or redirected to respective page.
     *
     * @param page              Page where user will be send afterwards. Consist of additional word "redirect" in case
     *                          of redirection that is removed in method to get correct page link.
     * @param request           User's request from his browser
     * @param response          Response to be send to user.
     * @throws IOException
     * @throws ServletException
     */
    private void sendUserToPage(String page, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        if (page.contains(Servlet.REDIRECT)){
            response.sendRedirect(page.replace(Servlet.REDIRECT, Servlet.REPLACEMENT));
        }
        else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}