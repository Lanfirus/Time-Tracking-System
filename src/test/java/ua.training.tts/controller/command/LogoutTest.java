package ua.training.tts.controller.command;

import org.junit.Assert;
import org.junit.Test;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.TestConstants;
import ua.training.tts.constant.controller.command.CommandParameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


public class LogoutTest extends Assert{

    private Logout logout = new Logout();
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpSession session = mock(HttpSession.class);

    @Test
    public void excecute() {
        when(request.getSession()).thenReturn(session);
        given(session.getAttribute(ReqSesParameters.LOGIN)).willReturn(TestConstants.LOGIN);
        String page = logout.execute(request);
        verify(session, times(1)).invalidate();
        verify(session, times(1)).getAttribute(ReqSesParameters.LOGIN);
        assertEquals(CommandParameters.REDIRECT + CommandParameters.MAIN, page);
    }

    @Test
    public void excecuteIncorrectNullLogin() {
        when(request.getSession()).thenReturn(session);
        given(session.getAttribute(ReqSesParameters.LOGIN)).willReturn(TestConstants.NULL);
        String page = logout.execute(request);
        verify(session, times(1)).invalidate();
        verify(session, times(1)).getAttribute(ReqSesParameters.LOGIN);
        assertEquals(CommandParameters.REDIRECT + CommandParameters.MAIN, page);
    }
}




