package ua.training.tts.model.service;

import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.RegExp;
import ua.training.tts.constant.model.Entity;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.constant.model.service.Service;
import ua.training.tts.model.dao.EmployeeDao;
import ua.training.tts.model.dao.TaskDao;
import ua.training.tts.model.dao.factory.DaoFactory;
import ua.training.tts.model.dao.factory.JDBCDaoFactoryImpl;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.exception.BadTaskDataException;
import ua.training.tts.model.util.builder.TaskBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Pattern;

public class TaskService {

    private ResourceBundle regexpBundle;
    private DaoFactory daoFactory;

    public TaskService() {
        this.daoFactory = new JDBCDaoFactoryImpl();
    }
//ToDo check correctness

    public Task buildNewTaskForEditByAdmin(HttpServletRequest request){
        Task task = new TaskBuilder().setId(Integer.parseInt(request.getParameter(TableParameters.TASK_ID)))
                                     .setProjectId(Integer.parseInt(request.getParameter(TableParameters.TASK_PROJECT_ID)))
                                     .setEmployeeId(Integer.parseInt(request.getParameter(TableParameters.TASK_EMPLOYEE_ID)))
                                     .setName(request.getParameter(TableParameters.TASK_NAME))
                                     .setStatus(request.getParameter(TableParameters.TASK_STATUS))
                                     .setDeadline(request.getParameter(TableParameters.TASK_DEADLINE))
                                     .setSpentTime(Integer.parseInt(request.getParameter(TableParameters.TASK_SPENT_TIME)))
                                     .setApproved(request.getParameter(TableParameters.TASK_APPROVED))
                                     .buildTask();
        return task;
    }

    public Task buildNewTaskForAdmin(HttpServletRequest request){
        Task task = new TaskBuilder().setProjectId(Integer.parseInt(request.getParameter(TableParameters.TASK_PROJECT_ID)))
                                     .setEmployeeId(Integer.parseInt(request.getParameter(TableParameters.TASK_EMPLOYEE_ID)))
                                     .setName(request.getParameter(TableParameters.TASK_NAME))
                                     .setStatus(request.getParameter(TableParameters.TASK_STATUS))
                                     .setDeadline(request.getParameter(TableParameters.TASK_DEADLINE))
                                     .setSpentTime(Integer.parseInt(request.getParameter(TableParameters.TASK_SPENT_TIME)))
                                     .setApproved(request.getParameter(TableParameters.TASK_APPROVED))
                                     .buildTask();
            return task;
    }

    public Task buildTaskForUpdateByEmployee(HttpServletRequest request){
        Task task = new TaskBuilder().setId(Integer.parseInt(request.getParameter(TableParameters.TASK_ID)))
                                     .setStatus(request.getParameter(TableParameters.TASK_STATUS))
                                     .setSpentTime(Integer.parseInt(request.getParameter(TableParameters.TASK_SPENT_TIME)))
                                     .buildTaskForUpdate();
        return task;
    }

    public Task buildNewTaskByEmployee(HttpServletRequest request, Integer id){
        Task task = new TaskBuilder().setProjectId(Integer.parseInt(request.getParameter(TableParameters.TASK_PROJECT_ID)))
                                     .setEmployeeId(id)
                                     .setName(request.getParameter(TableParameters.TASK_NAME))
                                     .setStatus(Task.Status.ASSIGNED.name().toLowerCase())
                                     .setDeadline(request.getParameter(TableParameters.TASK_DEADLINE))
                                     .setSpentTime(Integer.parseInt(request.getParameter(TableParameters.TASK_SPENT_TIME)))
                                     .setApproved(Task.ApprovalState.NEW_REQUEST.name().toLowerCase())
                                     .buildTask();
        return task;
    }

    public void tryToPutTaskDataFromWebIntoDB(Task task, HttpServletRequest request) throws BadTaskDataException{
        if (checkDataFromWebForCorrectness(task, request)) {
                sendReadyRegistrationDataToDB(task);
        }
        else {
            throw new BadTaskDataException();
        }
    }

    public void tryToPutUpdateTaskDataFromWebIntoDB(Task task, HttpServletRequest request) throws BadTaskDataException{
        if (checkDataFromWebForCorrectness(task, request)) {
            sendReadyUpdateDataToDB(task);
        }
        else {
            throw new BadTaskDataException();
        }
    }

    public void tryToPutUpdateDataFromWebIntoDBEmployee(Task task, HttpServletRequest request)
                throws BadTaskDataException {
        if (checkDataFromWebForCorrectness(task, request)) {
                sendReadyUpdateDataToDBEmployee(task);
        }
        else {
            throw new BadTaskDataException();
        }
    }

    /**
     * Matches data from web with regexps. For some types of data that is not String,
     * performs other checks.
     * @param
     * @param request
     * @return boolean
     */
    private boolean checkDataFromWebForCorrectness(Task task, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = session.getAttribute(ReqSesParameters.LANGUAGE) == null ?
                Service.ENGLISH : (String)session.getAttribute(ReqSesParameters.LANGUAGE);
        bundleInitialization(new Locale(locale));

        boolean check = true;
        check &= (task.getId() == null || matchInputWithRegexp(String.valueOf(task.getId()),
                regexpBundle.getString(RegExp.TASK_ID)));
        check &= (task.getProjectId() == null ||  matchInputWithRegexp(String.valueOf(task.getProjectId()),
                regexpBundle.getString(RegExp.TASK_PROJECT_ID)));
        check &= (task.getEmployeeId() == null || matchInputWithRegexp(String.valueOf(task.getEmployeeId()),
                regexpBundle.getString(RegExp.TASK_EMPLOYEE_ID)));
        check &= (task.getName() == null || matchInputWithRegexp(task.getName(),
                regexpBundle.getString(RegExp.TASK_NAME)));
        check &= matchInputWithRegexp(task.getStatus().name(), regexpBundle.getString(RegExp.TASK_STATUS));
        check &= (task.getDeadline() == null || matchInputWithRegexp(String.valueOf(task.getDeadline()),
                regexpBundle.getString(RegExp.TASK_DEADLINE)));
        check &= (task.getSpentTime() == null || matchInputWithRegexp(String.valueOf(task.getSpentTime()),
                regexpBundle.getString(RegExp.TASK_SPENT_TIME)));
        check &= (task.getApprovalState() == null || matchInputWithRegexp(String.valueOf(task.getApprovalState()),
                regexpBundle.getString(RegExp.TASK_APPROVED)));
        return check;
    }

    /**
     * Sends checked data to user to be put into DB.
     * @param
     */
    private void sendReadyRegistrationDataToDB(Task task) {
        TaskDao dao = daoFactory.createTaskDao();
        dao.create(task);
    }

    /**
     * Sends checked data to user to be put into DB.
     * @param
     */
    public void sendReadyUpdateDataToDB(Task task) {
        TaskDao dao = daoFactory.createTaskDao();
        dao.update(task);
    }

    /**
     * Sends checked data to user to be put into DB.
     * @param
     */
    public void sendReadyUpdateDataToDBEmployee(Task task) {
        TaskDao dao = daoFactory.createTaskDao();
        dao.updateTaskEmployee(task);
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

    public void setTaskEnteredDataBackToForm(HttpServletRequest request, Task task){
        request.setAttribute(TableParameters.TASK_ID, task.getId());
        request.setAttribute(TableParameters.TASK_PROJECT_ID, task.getProjectId());
        request.setAttribute(TableParameters.TASK_EMPLOYEE_ID, task.getEmployeeId());
        request.setAttribute(TableParameters.TASK_NAME, task.getName());
        request.setAttribute(TableParameters.TASK_STATUS, task.getStatus());
        request.setAttribute(TableParameters.TASK_DEADLINE, task.getDeadline());
        request.setAttribute(TableParameters.TASK_SPENT_TIME, task.getSpentTime());
        request.setAttribute(TableParameters.TASK_APPROVED, task.getApprovalState());
    }

    public boolean isEmployeeExist(String login, String password){
        EmployeeDao dao = daoFactory.createEmployeeDao();
        return dao.isEntryExist(login, password);
    }

    public Integer getEmployeeID(String login, String password) {
        EmployeeDao dao = daoFactory.createEmployeeDao();
        return dao.findIdByKeys(login, password);
    }

    public String getStatusByTaskId(Integer id) {
        TaskDao dao = daoFactory.createTaskDao();
        return dao.findById(id).getStatus().name().toLowerCase();
    }

    public Employee findByLogin(String login){
        EmployeeDao dao = daoFactory.createEmployeeDao();
        return dao.findByLogin(login);
    }

    public Task findById(Integer id){
        TaskDao dao = daoFactory.createTaskDao();
        return dao.findById(id);
    }

    public List<Task> findAll(){
        TaskDao dao = daoFactory.createTaskDao();
        return dao.findAll();
    }

    public List<Task> findAllById(Integer id){
        TaskDao dao = daoFactory.createTaskDao();
        return dao.findAllByEmployeeId(id);
    }

    public void setRoleById(Integer id, String role){
        EmployeeDao dao = daoFactory.createEmployeeDao();
        dao.setRoleById(id, role);
    }

    public void deleteById(Integer id){
        TaskDao dao = daoFactory.createTaskDao();
        dao.delete(id);
    }

    public List<String> getFieldNames() {
        return Arrays.asList(Entity.TASK_ID, Entity.TASK_NAME, Entity.TASK_PROJECT_ID, Entity.TASK_STATUS,
                Entity.TASK_DEADLINE, Entity.TASK_SPENT_TIME);
    }
}