package ua.training.tts.controller.command;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.TestConstants;
import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.controller.listener.EmployeeDTO;
import ua.training.tts.controller.listener.EmployeeDTOTest;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Login.class)
public class LoginTest extends Assert{

    private EmployeeService service = mock(EmployeeService.class);
    private Login login = PowerMockito.spy(new Login(service));
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpSession session = mock(HttpSession.class);
    private EmployeeDTO dto = new EmployeeDTO(TestConstants.ID, TestConstants.LOGIN, TestConstants.PASSWORD,
            Employee.AccountRole.EMPLOYEE.name().toLowerCase());

    @Before
    public void init(){
        given(request.getParameter(ReqSesParameters.LOGIN)).willReturn(TestConstants.LOGIN);
        given(request.getParameter(ReqSesParameters.PASSWORD)).willReturn(TestConstants.PASSWORD);
        given(request.getSession()).willReturn(session);
    }

    @Test
    public void executeDtoExist(){
        given(session.getAttribute(ReqSesParameters.DTO)).willReturn(dto);
        String page = login.execute(request);
        assertEquals(CommandParameters.REDIRECT + CommandParameters.MAIN, page);
    }

    @Test
    public void executeDtoNotExistUserExist() throws Exception{
        given(session.getAttribute(ReqSesParameters.DTO)).willReturn(null);
        given(service.isEmployeeExist(TestConstants.LOGIN, TestConstants.PASSWORD)).willReturn(TestConstants.TRUE);
        final EmployeeDTOTest mockedDto = mock(EmployeeDTOTest.class);
        whenNew(EmployeeDTOTest.class).withAnyArguments().thenReturn(mockedDto);
        String page = login.execute(request);
        assertEquals(CommandParameters.REDIRECT + CommandParameters.MAIN, page);
        verify(session, times(1)).setAttribute(ReqSesParameters.DTO, mockedDto);

        //ToDo Resolve issue with this test part not working properly
//        verifyPrivate(login, times(1)).invoke(TestConstants.LOGIN_CHECK_DOUBLE_LOGIN_METHOD);
    }

    @Test
    public void executeDtoNotExistUserNotExist() throws Exception{
        given(session.getAttribute(ReqSesParameters.DTO)).willReturn(null);
        given(service.isEmployeeExist(TestConstants.LOGIN, TestConstants.PASSWORD)).willReturn(TestConstants.FALSE);
        final EmployeeDTOTest mockedDto = mock(EmployeeDTOTest.class);
        whenNew(EmployeeDTOTest.class).withAnyArguments().thenReturn(mockedDto);
        String page = login.execute(request);
        assertEquals(CommandParameters.REDIRECT + CommandParameters.MAIN, page);
        verify(session, times(1)).setAttribute(ReqSesParameters.DTO, mockedDto);
    }
}

