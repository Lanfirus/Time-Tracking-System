package ua.training.tts.model.service;

import ua.training.tts.constant.controller.command.CommandParameters;
import ua.training.tts.constant.model.Entity;
import ua.training.tts.constant.model.dao.DAOParameters;
import ua.training.tts.constant.model.service.Service;
import ua.training.tts.model.DAO.DAOFactory;
import ua.training.tts.model.DAO.DAOImpl.EmployeeDAOImpl;
import ua.training.tts.model.DAO.connectionPool.DataSource;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.exception.NotUniqueLoginException;
import ua.training.tts.model.util.PasswordHashing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EmployeeService {

    private ResourceBundle regexpBundle;

    public Map<String, String> setUserData(HttpServletRequest request){
        Map<String, String> preparedUserData = new LinkedHashMap<>();
        String hashedPassword = PasswordHashing.hashPassword(request.getParameter(Entity.EMPLOYEE_PASSWORD));
        preparedUserData.put(Entity.EMPLOYEE_LOGIN, request.getParameter(Entity.EMPLOYEE_LOGIN));
        preparedUserData.put(Entity.EMPLOYEE_PASSWORD, hashedPassword);
        preparedUserData.put(Entity.EMPLOYEE_NAME, request.getParameter(Entity.EMPLOYEE_NAME));
        preparedUserData.put(Entity.EMPLOYEE_SURNAME, request.getParameter(Entity.EMPLOYEE_SURNAME));
        preparedUserData.put(Entity.EMPLOYEE_PATRONYMIC, request.getParameter(Entity.EMPLOYEE_PATRONYMIC));
        preparedUserData.put(Entity.EMPLOYEE_EMAIL, request.getParameter(Entity.EMPLOYEE_EMAIL));
        preparedUserData.put(Entity.EMPLOYEE_MOBILE_PHONE, request.getParameter(Entity.EMPLOYEE_MOBILE_PHONE));
        preparedUserData.put(Entity.EMPLOYEE_COMMENT, request.getParameter(Entity.EMPLOYEE_COMMENT));

        if(request.getParameter(CommandParameters.UPDATE_FLAG) != null) {
            HttpSession session = request.getSession();
            preparedUserData.put(Entity.EMPLOYEE_OLD_LOGIN, (String) session.getAttribute(Entity.EMPLOYEE_LOGIN));
            preparedUserData.put(Entity.EMPLOYEE_OLD_PASSWORD, (String) session.getAttribute(Entity.EMPLOYEE_PASSWORD));
        }
            return preparedUserData;
    }

    public boolean onRecievingEmployeeRegistrationDataFromWeb(Employee employee, HttpServletRequest request)
                    throws NotUniqueLoginException {
        Map<String, String> employeeData = employee.getEmployeeData();
        if (checkDataFromWebForCorrectness(employeeData, request)) {
            try{
                sendReadyRegistrationDataToDB(employeeData);
            }
            catch(RuntimeException e){
                throw new NotUniqueLoginException();
            }
            return true;
        }
        else {
            throw new RuntimeException("Something happened during userRegistrationData registration data check");
        }
    }

/*    public boolean onRecievingUserUpdateDataFromWeb(Employee employee, HttpServletRequest request)
                    throws NotUniqueLoginException{
        Map<String, String> employeeData = employee.getEmployeeData();
        if (checkDataFromWebForCorrectness(employeeData)) {
            return sendReadyUpdateDataToDB(employeeData);
        }
        else {
            throw new RuntimeException("Something happened during userRegistrationData registration data check");
        }
    }*/

    /**
     * Matches data from web with regexps. For some types of data that is not String,
     * performs other checks.
     * @param data
     * @return
     */
    public boolean checkDataFromWebForCorrectness(Map<String, String> data, HttpServletRequest request) {
        boolean check = true;
        HttpSession session = request.getSession();
        String locale = session.getAttribute(Service.LANGUAGE) == null ?
                "en" : (String)session.getAttribute(Service.LANGUAGE);
        bundleInitialization(new Locale(locale));
        for (Map.Entry<String, String> field : data.entrySet()){
            check &= matchInputWithRegexp(field.getValue(), regexpBundle.getString(field.getKey()));
        }
        return check;
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

    /**
     * Sends checked data to user to be put into DB.
     * @param employeeData
     */
    public boolean sendReadyRegistrationDataToDB(Map<String, String> employeeData) throws NotUniqueLoginException {
        EmployeeDAOImpl dao = (EmployeeDAOImpl)DAOFactory.create(DAOParameters.EMPLOYEE_DAO);
        dao.create(employeeData);
        dao.closeConnection();
        return true;
    }

/*    public boolean sendReadyUpdateDataToDB(Map<String, String> userData) throws NotUniqueLoginException {
        UserDAO dao = new UserDAO();
        try {
            dao.update(userData);
        }
        catch(RuntimeException e){
            if (e.getMessage().contains(Constants.NOT_UNIQUE_LOGIN_EXCEPTION_FLAG)) {
                throw new NotUniqueLoginException();
            }
            else{
                throw new RuntimeException(Messages.UPDATE_FAILURE);
            }
        }
        return true;
    }*/

    public void setEmployeeEnteredDataBackToForm(HttpServletRequest request, Employee employee){
        request.setAttribute(Entity.EMPLOYEE_LOGIN, employee.getLogin());
        request.setAttribute(Entity.EMPLOYEE_NAME, employee.getName());
        request.setAttribute(Entity.EMPLOYEE_SURNAME, employee.getSurname());
        request.setAttribute(Entity.EMPLOYEE_PATRONYMIC, employee.getPatronymic() == null ? "" : employee.getPatronymic());
        request.setAttribute(Entity.EMPLOYEE_EMAIL, employee.getEmail());
        request.setAttribute(Entity.EMPLOYEE_MOBILE_PHONE, employee.getMobilePhone());
        request.setAttribute(Entity.EMPLOYEE_COMMENT, employee.getComment() == null ? "" : employee.getComment());
    }

    public boolean isEmployeeExist(String login, String password) {
//        EmployeeDAOImpl dao = (EmployeeDAOImpl)DAOFactory.create(DAOParameters.EMPLOYEE_DAO);
        Connection connection = DataSource.getConnection();
        EmployeeDAOImpl dao = new EmployeeDAOImpl(connection);
        return dao.findParamByLoginPassword(Entity.EMPLOYEE_ID, login, password) != null;
    }

    public String getRoleByLoginPassword(String login, String password) {
        EmployeeDAOImpl dao = (EmployeeDAOImpl)DAOFactory.create(DAOParameters.EMPLOYEE_DAO);
        return dao.findParamByLoginPassword(Entity.EMPLOYEE_ACCOUNT_ROLE, login, password);
    }
}