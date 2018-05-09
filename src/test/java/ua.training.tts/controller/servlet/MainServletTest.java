package ua.training.tts.controller.servlet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.training.tts.constant.Pages;
import ua.training.tts.constant.TestConstants;
import ua.training.tts.constant.controller.Servlet;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.controller.command.Login;
import ua.training.tts.controller.command.Logout;
import ua.training.tts.controller.command.Registration;
import ua.training.tts.controller.command.redirect.LoginForm;
import ua.training.tts.controller.command.redirect.MainPage;
import ua.training.tts.controller.command.redirect.RegistrationForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MainServlet.class)
public class MainServletTest extends Assert {

    private MainServlet servlet = PowerMockito.spy(new MainServlet());
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private RequestDispatcher dispatcher = mock(RequestDispatcher.class);

    @Test
    public void processRequestMainPage() throws Exception {
        final MainPage page = mock(MainPage.class);
        whenNew(MainPage.class).withNoArguments().thenReturn(page);
        given(request.getRequestURI()).willReturn(Servlet.MAIN);
        given(page.execute(request)).willReturn(Pages.INDEX_PAGE);
        given(request.getRequestDispatcher(Pages.INDEX_PAGE)).willReturn(dispatcher);
        doNothing().when(dispatcher).forward(request, response);
        servlet.init();
        servlet.doGet(request, response);
        verifyPrivate(servlet, times(1))
                .invoke(TestConstants.MAIN_SERVLET_SEND_USER_TO_PAGE, Pages.INDEX_PAGE, request, response);
    }

    @Test
    public void processRequestRegistrationForm() throws Exception {
        final RegistrationForm page = mock(RegistrationForm.class);
        whenNew(RegistrationForm.class).withNoArguments().thenReturn(page);
        given(request.getRequestURI()).willReturn(Servlet.REGISTRATION_FORM);
        given(page.execute(request)).willReturn(Pages.REGISTRATION_PAGE);
        given(request.getRequestDispatcher(Pages.REGISTRATION_PAGE)).willReturn(dispatcher);
        doNothing().when(dispatcher).forward(request, response);
        servlet.init();
        servlet.doGet(request, response);
        verifyPrivate(servlet, times(1))
                .invoke(TestConstants.MAIN_SERVLET_SEND_USER_TO_PAGE, Pages.REGISTRATION_PAGE, request, response);
    }

    @Test
    public void processRequestRegistration() throws Exception {
        final Registration page = mock(Registration.class);
        whenNew(Registration.class).withAnyArguments().thenReturn(page);
        given(request.getRequestURI()).willReturn(Servlet.REGISTRATION);
        given(page.execute(request)).willReturn(Pages.REGISTRATION_PAGE);
        given(request.getRequestDispatcher(Pages.REGISTRATION_PAGE)).willReturn(dispatcher);
        doNothing().when(dispatcher).forward(request, response);
        servlet.init();
        servlet.doGet(request, response);
        verifyPrivate(servlet, times(1))
                .invoke(TestConstants.MAIN_SERVLET_SEND_USER_TO_PAGE, Pages.REGISTRATION_PAGE, request, response);
    }

    @Test
    public void processRequestRegistrationSuccessful() throws Exception {
        final Registration page = mock(Registration.class);
        whenNew(Registration.class).withAnyArguments().thenReturn(page);
        given(request.getRequestURI()).willReturn(Servlet.REGISTRATION_SUCCESSFUL);
        given(page.execute(request)).willReturn(Pages.REGISTRATION_SUCCESSFUL_PAGE);
        given(request.getRequestDispatcher(Pages.REGISTRATION_SUCCESSFUL_PAGE)).willReturn(dispatcher);
        doNothing().when(dispatcher).forward(request, response);
        servlet.init();
        servlet.doGet(request, response);
        verifyPrivate(servlet, times(1))
                .invoke(TestConstants.MAIN_SERVLET_SEND_USER_TO_PAGE, Pages.REGISTRATION_SUCCESSFUL_PAGE, request, response);
    }

    @Test
    public void processRequestLoginForm() throws Exception {
        final LoginForm page = mock(LoginForm.class);
        whenNew(LoginForm.class).withAnyArguments().thenReturn(page);
        given(request.getRequestURI()).willReturn(Servlet.LOGIN_FORM);
        given(page.execute(request)).willReturn(Pages.LOGIN_PAGE);
        given(request.getRequestDispatcher(Pages.LOGIN_PAGE)).willReturn(dispatcher);
        doNothing().when(dispatcher).forward(request, response);
        servlet.init();
        servlet.doGet(request, response);
        verifyPrivate(servlet, times(1))
                .invoke(TestConstants.MAIN_SERVLET_SEND_USER_TO_PAGE, Pages.LOGIN_PAGE, request, response);
    }

    @Test
    public void processRequestLogin() throws Exception {
        final Login page = mock(Login.class);
        whenNew(Login.class).withAnyArguments().thenReturn(page);
        given(request.getRequestURI()).willReturn(Servlet.LOGIN);
        given(page.execute(request)).willReturn(CommandParameters.REDIRECT + CommandParameters.MAIN);
        given(request.getRequestDispatcher(CommandParameters.REDIRECT + CommandParameters.MAIN))
                .willReturn(dispatcher);
        doNothing().when(dispatcher).forward(request, response);
        servlet.init();
        servlet.doGet(request, response);
        verifyPrivate(servlet, times(1)).invoke(TestConstants.MAIN_SERVLET_SEND_USER_TO_PAGE,
                CommandParameters.REDIRECT + CommandParameters.MAIN, request, response);
    }

    @Test
    public void processRequestLogout() throws Exception {
        final Logout page = mock(Logout.class);
        whenNew(Logout.class).withAnyArguments().thenReturn(page);
        given(request.getRequestURI()).willReturn(Servlet.LOGOUT);
        given(page.execute(request)).willReturn(CommandParameters.REDIRECT + CommandParameters.MAIN);
        given(request.getRequestDispatcher(CommandParameters.REDIRECT + CommandParameters.MAIN))
                .willReturn(dispatcher);
        doNothing().when(dispatcher).forward(request, response);
        servlet.init();
        servlet.doGet(request, response);
        verifyPrivate(servlet, times(1)).invoke(TestConstants.MAIN_SERVLET_SEND_USER_TO_PAGE,
                CommandParameters.REDIRECT + CommandParameters.MAIN, request, response);
    }

    @Test
    public void processRequestIncorrectRequest() throws Exception {
        final MainPage page = mock(MainPage.class);
        whenNew(MainPage.class).withNoArguments().thenReturn(page);
        given(request.getRequestURI()).willReturn(TestConstants.INCORRECT_REQUEST);
        given(page.execute(request)).willReturn(Pages.INDEX_PAGE);
        given(request.getRequestDispatcher(Pages.INDEX_PAGE)).willReturn(dispatcher);
        doNothing().when(dispatcher).forward(request, response);
        servlet.init();
        servlet.doGet(request, response);
        verifyPrivate(servlet, times(1)).invoke(TestConstants.MAIN_SERVLET_SEND_USER_TO_PAGE,
                CommandParameters.REDIRECT + Servlet.SERVLET_MAIN, request, response);
    }
}