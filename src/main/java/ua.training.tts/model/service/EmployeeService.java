package ua.training.tts.model.service;

import ua.training.tts.constant.ExceptionMessages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.constant.model.service.Service;
import ua.training.tts.model.dao.EmployeeDao;
import ua.training.tts.model.dao.factory.DaoFactory;
import ua.training.tts.model.dao.factory.JDBCDaoFactoryImpl;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.exception.BadRegistrationDataException;
import ua.training.tts.model.exception.NotUniqueLoginException;
import ua.training.tts.util.PasswordHashing;
import ua.training.tts.model.util.builder.EmployeeBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Pattern;

public class EmployeeService {

    private ResourceBundle regexpBundle;
    private DaoFactory daoFactory = new JDBCDaoFactoryImpl();

    public Employee buildEmployee(HttpServletRequest request){
        String hashedPassword = PasswordHashing.hashPassword(request.getParameter(TableParameters.EMPLOYEE_PASSWORD));
        Employee employee = new EmployeeBuilder().setLogin(request.getParameter(TableParameters.EMPLOYEE_LOGIN))
                                                 .setPassword(hashedPassword)
                                                 .setName(request.getParameter(TableParameters.EMPLOYEE_NAME))
                                                 .setSurname(request.getParameter(TableParameters.EMPLOYEE_SURNAME))
                                                 .setPatronymic(request.getParameter(TableParameters.EMPLOYEE_PATRONYMIC))
                                                 .setEmail(request.getParameter(TableParameters.EMPLOYEE_EMAIL))
                                                 .setMobilePhone(request.getParameter(TableParameters.EMPLOYEE_MOBILE_PHONE))
                                                 .setComment(request.getParameter(TableParameters.EMPLOYEE_COMMENT))
                                                 .buildEmployee();
            return employee;
    }

    public void onRecievingEmployeeRegistrationDataFromWeb(Employee employee, HttpServletRequest request)
                    throws NotUniqueLoginException, BadRegistrationDataException {
        if (checkDataFromWebForCorrectness(employee, request)) {
            try{
                sendReadyRegistrationDataToDB(employee);
            }
            catch(RuntimeException e){
                if (e.getMessage().contains(ExceptionMessages.UNIQUE)) {
                    throw new NotUniqueLoginException();
                }
                else {
                    throw new RuntimeException(ExceptionMessages.SQL_GENERAL_PROBLEM);
                }
            }
        }
        else {
            throw new BadRegistrationDataException();
        }
    }

    /**
     * Matches data from web with regexps. For some types of data that is not String,
     * performs other checks.
     * @param employee
     * @param request
     * @return boolean
     */
    public boolean checkDataFromWebForCorrectness(Employee employee, HttpServletRequest request) {
        List<String> fieldNames = employee.getFieldNames();
        List<String> fieldValues = employee.getFieldValues();
        boolean check = true;
        HttpSession session = request.getSession();
        String locale = session.getAttribute(ReqSesParameters.LANGUAGE) == null ?
                Service.ENGLISH : (String)session.getAttribute(ReqSesParameters.LANGUAGE);
        bundleInitialization(new Locale(locale));
        for (int field = 0; field < fieldValues.size(); field++){
            check &= matchInputWithRegexp(fieldValues.get(field), regexpBundle.getString(fieldNames.get(field)));
        }
        return check;
    }

    /**
     * Sends checked data to user to be put into DB.
     * @param employee
     */
    public boolean sendReadyRegistrationDataToDB(Employee employee) {
        EmployeeDao dao = daoFactory.createEmployeeDao();
        dao.create(employee);
        return true;
    }

    /**
     * Initialize Resource Bundles with respective locale.
     *
     * @param locale
     */
    public void bundleInitialization(Locale locale) {
        regexpBundle = ResourceBundle.getBundle(Service.REGEXP_BUNDLE_NAME, locale);
    }

    /**
     * Applies regexp to employeeRegistrationData input to check its validity.
     *
     * @param input
     * @param regexp
     * @return
     */
    public boolean matchInputWithRegexp(String input, String regexp) {
        return Pattern.matches(regexp, input);
    }

    public void setEmployeeEnteredDataBackToForm(HttpServletRequest request, Employee employee){
        request.setAttribute(TableParameters.EMPLOYEE_LOGIN, employee.getLogin());
        request.setAttribute(TableParameters.EMPLOYEE_NAME, employee.getName());
        request.setAttribute(TableParameters.EMPLOYEE_SURNAME, employee.getSurname());
        request.setAttribute(TableParameters.EMPLOYEE_PATRONYMIC, employee.getPatronymic() == null ? "" : employee.getPatronymic());
        request.setAttribute(TableParameters.EMPLOYEE_EMAIL, employee.getEmail());
        request.setAttribute(TableParameters.EMPLOYEE_MOBILE_PHONE, employee.getMobilePhone());
        request.setAttribute(TableParameters.EMPLOYEE_COMMENT, employee.getComment() == null ? "" : employee.getComment());
    }

    public boolean isEmployeeExist(String login, String password){
        EmployeeDao dao = daoFactory.createEmployeeDao();
        return dao.isEntryExist(login, password);
    }

    public Integer getEmployeeID(String login, String password) {
        EmployeeDao dao = daoFactory.createEmployeeDao();
        return dao.findIdByKeys(login, password);
    }

    public String getRoleByLoginPassword(String login, String password) {
        EmployeeDao dao = daoFactory.createEmployeeDao();
        return dao.findParamByKeys(TableParameters.EMPLOYEE_ACCOUNT_ROLE, login, password);
    }
}