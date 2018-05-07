package ua.training.tts.controller.command.redirect;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.training.tts.constant.Pages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.constant.model.Entity;
import ua.training.tts.controller.util.EmployeeDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


public class MainPageTest extends Assert{

    private MainPage form = new MainPage();
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpSession session = mock(HttpSession.class);
    private EmployeeDTO dto1;
    private EmployeeDTO dto2;
    private EmployeeDTO dto3;

    @Before
    public void init() {
        dto1 = new EmployeeDTO(CommandParameters.EMPTY, CommandParameters.EMPTY,
                Entity.ACCOUNT_ROLE_ADMIN);
        dto2 = new EmployeeDTO(CommandParameters.EMPTY, CommandParameters.EMPTY,
                Entity.ACCOUNT_ROLE_EMPLOYEE);
        dto3 = new EmployeeDTO(CommandParameters.EMPTY, CommandParameters.EMPTY,
                Entity.ACCOUNT_ROLE_UNKNOWN);
    }

    @Test
    public void excecute() {
        given(session.getAttribute(ReqSesParameters.DTO)).willReturn(dto1, dto2, dto3, null);
        when(request.getSession()).thenReturn(session);
        given(request.getSession().getAttribute(ReqSesParameters.DTO)).willReturn(dto1, dto2, dto3, null);
        String page1 = form.execute(request);
        String page2 = form.execute(request);
        String page3 = form.execute(request);
        verify(request).setAttribute(ReqSesParameters.BAD_LOGIN_PASSWORD, true);
        verify(request, times(5)).getSession();
        String page4 = form.execute(request);
        assertEquals(Pages.ADMIN_INDEX_PAGE, page1);
        assertEquals(Pages.EMPLOYEE_INDEX_PAGE, page2);
        assertEquals(Pages.INDEX_PAGE, page3);
        assertEquals(Pages.INDEX_PAGE, page4);
    }
}




