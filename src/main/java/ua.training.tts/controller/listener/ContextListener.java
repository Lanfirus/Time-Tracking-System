package ua.training.tts.controller.listener;

import ua.training.tts.constant.controller.Listener;
import ua.training.tts.controller.util.EmployeeDTO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class ContextListener implements ServletContextListener{

    private Map<EmployeeDTO, HttpSession> logins;

    /**
     * Initializes map to store relationships between user's logins and their sessions to prevent multi login to the
     * system.
     *
     * @param servletContextEvent   ServletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logins = new ConcurrentHashMap<>();
        servletContextEvent.getServletContext().setAttribute(Listener.LOGINS, logins);
    }
}
