package ua.training.tts.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.training.tts.constant.TestConstants;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.model.dao.connectionpool.ConnectionPool;
import ua.training.tts.model.dao.impl.EmployeeDaoMySQLImpl;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.util.RequestBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EmployeeDaoMySQLImpl.class, ConnectionPool.class})
@SuppressWarnings("unchecked")
public class EmployeeDaoMySQLImplTest extends Assert{
/*


    private Employee employee = mock(Employee.class);
    private Connection connection = mock(Connection.class);
    private PreparedStatement statement = mock(PreparedStatement.class);
    private RequestBuilder builder = mock(RequestBuilder.class, RETURNS_SELF);
    private EmployeeDaoMySQLImpl dao = PowerMockito.spy(new EmployeeDaoMySQLImpl(builder));
    private List<String> list = new ArrayList<>();

    @Test
    public void create() throws Exception{
        PowerMockito.mockStatic(ConnectionPool.class);
        PowerMockito.doNothing().when(dao, TestConstants.EMPLOYEE_DAO_IMPL_SET_VALUE_TO_PREPARED_STATEMENT,
                statement, null);
        given(employee.getFieldNames()).willReturn(list);
        given(employee.getFieldValues()).willReturn(null);
//        given(builder.insertIntoTable(TableParameters.EMPLOYEE_TABLE_NAME)).willReturn(builder);
//        given(builder.insertValueNames(anyList())).willReturn(builder);
        given(builder.build()).willReturn(null);
        given(ConnectionPool.getConnection()).willReturn(connection);
        given(connection.prepareStatement(null)).willReturn(statement);
        doNothing().when(statement).close();
        doNothing().when(connection).close();
        given(statement.toString()).willReturn(null);
        given(statement.executeUpdate()).willReturn(TestConstants.ROW_COUNT);

        dao.create(employee);
        verify(employee, times(1)).getFieldNames();
        verify(employee, times(1)).getFieldValues();
        verify(builder, times(1)).insertIntoTable(TableParameters.EMPLOYEE_TABLE_NAME);
        verify(builder, times(1)).insertValueNames(any(List.class));
        verify(builder, times(1)).build();
        verify(connection, times(1)).prepareStatement(null);
        verify(statement, times(1)).close();
        verify(connection, times(1)).close();
        verifyPrivate(dao, times(1))
                .invoke(TestConstants.EMPLOYEE_DAO_IMPL_SET_VALUE_TO_PREPARED_STATEMENT, statement, null);
        verify(statement, times(1)).executeUpdate();
    }

    @Test(expected = RuntimeException.class)
    public void createStatementExcecuteThrowException() throws Exception{
        PowerMockito.mockStatic(ConnectionPool.class);
        PowerMockito.doNothing().when(dao, TestConstants.EMPLOYEE_DAO_IMPL_SET_VALUE_TO_PREPARED_STATEMENT,
                statement, null);
        given(employee.getFieldNames()).willReturn(list);
        given(employee.getFieldValues()).willReturn(null);
        given(builder.build()).willReturn(null);
        given(ConnectionPool.getConnection()).willReturn(connection);
        given(connection.prepareStatement(null)).willReturn(statement);
        doNothing().when(statement).close();
        doNothing().when(connection).close();
        given(statement.toString()).willReturn(null);
        given(statement.executeUpdate()).willThrow(new SQLException());

        dao.create(employee);
        verify(employee, times(1)).getFieldNames();
        verify(employee, times(1)).getFieldValues();
        verify(builder, times(1)).insertIntoTable(TableParameters.EMPLOYEE_TABLE_NAME);
        verify(builder, times(1)).insertValueNames(any(List.class));
        verify(builder, times(1)).build();
        verify(connection, times(1)).prepareStatement(null);
        verify(statement, times(1)).close();
        verify(connection, times(1)).close();
        verifyPrivate(dao, times(1))
                .invoke(TestConstants.EMPLOYEE_DAO_IMPL_SET_VALUE_TO_PREPARED_STATEMENT, statement, null);
        verify(statement, times(1)).executeUpdate();
    }*/
}
