package ua.training.tts.controller.command.redirect;

import org.junit.Test;
import ua.training.tts.constant.Pages;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LoginFormTest {

    private LoginForm form = mock(LoginForm.class);
    private HttpServletRequest request = mock(HttpServletRequest.class);

    @Test
    public void excecute() {
        when(form.execute(request)).thenReturn(Pages.LOGIN_PAGE);
    }
}
