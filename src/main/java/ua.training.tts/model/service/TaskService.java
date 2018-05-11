package ua.training.tts.model.service;

import ua.training.tts.constant.ExceptionMessages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.model.Entity;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.constant.model.service.Service;
import ua.training.tts.model.dao.EmployeeDao;
import ua.training.tts.model.dao.factory.DaoFactory;
import ua.training.tts.model.dao.factory.JDBCDaoFactoryImpl;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.exception.BadRegistrationDataException;
import ua.training.tts.model.exception.NotUniqueLoginException;
import ua.training.tts.model.util.builder.EmployeeBuilder;
import ua.training.tts.model.util.builder.TaskBuilder;
import ua.training.tts.util.PasswordHashing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;

public class TaskService {

    private ResourceBundle regexpBundle;
    private DaoFactory daoFactory;

    public TaskService() {
        this.daoFactory = new JDBCDaoFactoryImpl();
    }

    public Task buildTask(HttpServletRequest request, LocalDate deadline, LocalTime time){
        Task task = new TaskBuilder().setId(Integer.parseInt(request.getParameter(TableParameters.TASK_ID)))
                                     .setName(request.getParameter(TableParameters.TASK_NAME))
                                     .setProjectId(Integer.parseInt(request.getParameter(TableParameters.TASK_PROJECT_ID)))
                                     .setStatus(request.getParameter(TableParameters.TASK_STATUS))
                                     .setDeadline(deadline)
                                     .setSpentTime(time)
                                     .buildTask();
            return task;
    }


    public void tryToPutTaskDataFromWebIntoDB(Task task, HttpServletRequest request) {
        if (checkDataFromWebForCorrectness(task, request)) {
            try {
                sendReadyRegistrationDataToDB(employee);
            } catch (RuntimeException e) {
                if (e.getMessage().contains(ExceptionMessages.UNIQUE)) {
                    throw new NotUniqueLoginException();
                }
            }
        }
    }

    public void tryToPutUpdateDataFromWebIntoDB(Employee employee, HttpServletRequest request)
            throws NotUniqueLoginException, BadRegistrationDataException {
        if (checkDataFromWebForCorrectness(employee, request)) {
            try{
                sendReadyUpdateDataToDB(employee);
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
    private boolean checkDataFromWebForCorrectness(Task task, HttpServletRequest request) {
        List<String> fieldNames = getFieldNames();
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
    private void sendReadyRegistrationDataToDB(Employee employee) {
        EmployeeDao dao = daoFactory.createEmployeeDao();
        dao.create(employee);
    }

    /**
     * Sends checked data to user to be put into DB.
     * @param employee
     */
    public void sendReadyUpdateDataToDB(Employee employee) {
        EmployeeDao dao = daoFactory.createEmployeeDao();
        dao.update(employee);
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

    public void setFullEmployeeDataBackToForm(HttpServletRequest request, Employee employee){
        request.setAttribute(TableParameters.EMPLOYEE_ID, employee.getId());
        request.setAttribute(TableParameters.EMPLOYEE_LOGIN, employee.getLogin());
        request.setAttribute(TableParameters.EMPLOYEE_PASSWORD, employee.getPassword());
        request.setAttribute(TableParameters.EMPLOYEE_NAME, employee.getName());
        request.setAttribute(TableParameters.EMPLOYEE_SURNAME, employee.getSurname());
        request.setAttribute(TableParameters.EMPLOYEE_PATRONYMIC, employee.getPatronymic() == null ? "" : employee.getPatronymic());
        request.setAttribute(TableParameters.EMPLOYEE_EMAIL, employee.getEmail());
        request.setAttribute(TableParameters.EMPLOYEE_MOBILE_PHONE, employee.getMobilePhone());
        request.setAttribute(TableParameters.EMPLOYEE_COMMENT, employee.getComment() == null ? "" : employee.getComment());
        request.setAttribute(TableParameters.EMPLOYEE_ACCOUNT_ROLE, employee.getAccountRole());
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

    public Employee findByLogin(String login){
        EmployeeDao dao = daoFactory.createEmployeeDao();
        return dao.findByLogin(login);
    }

    public Employee findById(Integer id){
        EmployeeDao dao = daoFactory.createEmployeeDao();
        return dao.findById(id);
    }

    public List<Employee> findAll(){
        EmployeeDao dao = daoFactory.createEmployeeDao();
        return dao.findAll();
    }

    public void setRoleById(Integer id, String role){
        EmployeeDao dao = daoFactory.createEmployeeDao();
        dao.setRoleById(id, role);
    }

    public void deleteById(Integer id){
        EmployeeDao dao = daoFactory.createEmployeeDao();
        dao.delete(id);
    }

    public List<String> getFieldNames() {
        return Arrays.asList(Entity.TASK_ID, Entity.TASK_NAME, Entity.TASK_PROJECT_ID, Entity.TASK_STATUS,
                Entity.TASK_DEADLINE, Entity.TASK_SPENT_TIME);
    }

    public List<String> getFieldValues() {
        List<String> list = new ArrayList<>();
        list.add(getLogin());
        list.add(getPassword());
        list.add(getName());
        list.add(getSurname());
        list.add(getPatronymic());
        list.add(getEmail());
        list.add(getMobilePhone());
        list.add(getComment());
        return list;
    }
}