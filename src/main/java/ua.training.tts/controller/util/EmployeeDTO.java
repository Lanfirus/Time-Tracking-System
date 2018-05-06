package ua.training.tts.controller.util;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class EmployeeDTO implements HttpSessionBindingListener {

    private static Set<String> logins = ConcurrentHashMap.newKeySet();

    private String login;
    private boolean alreadyLoggedIn;

    public EmployeeDTO(String login) {
        this.login = login;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        if (logins.contains(login)) {
            alreadyLoggedIn = true;
        } else {
            logins.add(login);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        logins.remove(login);
    }
}
