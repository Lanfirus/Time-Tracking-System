package ua.training.tts.controller.servlet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.training.tts.constant.controller.Servlet;
import ua.training.tts.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainServletTest {

    private MainServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String uri;
    private String uri_cleaned;
    private Command command;
    private Map<String, Command> commands;

    @Before
    public void init(){
        servlet = mock(MainServlet.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        uri_cleaned = "main";
        commands = mock(Map.class);
    }

    @Test
    public void processRequest() {
//        String page = command.execute(request);
//        when(uri.replaceAll(Servlet.URI_REPLACE_PATTERN, Servlet.REPLACEMENT)).thenReturn(uri_cleaned);
    }
}