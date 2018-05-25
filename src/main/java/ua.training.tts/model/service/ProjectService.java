package ua.training.tts.model.service;

import ua.training.tts.constant.ExceptionMessages;
import ua.training.tts.constant.ReqSesParameters;
import ua.training.tts.constant.RegExp;
import ua.training.tts.constant.model.Entity;
import ua.training.tts.constant.model.dao.TableParameters;
import ua.training.tts.constant.model.service.Service;
import ua.training.tts.model.dao.EmployeeDao;
import ua.training.tts.model.dao.ProjectDao;
import ua.training.tts.model.dao.TaskDao;
import ua.training.tts.model.dao.factory.DaoFactory;
import ua.training.tts.model.dao.factory.JDBCDaoFactoryImpl;
import ua.training.tts.model.entity.Employee;
import ua.training.tts.model.entity.Project;
import ua.training.tts.model.entity.Task;
import ua.training.tts.model.exception.BadProjectDataException;
import ua.training.tts.model.exception.BadTaskDataException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ProjectService {

    private ResourceBundle regexpBundle;
    private DaoFactory daoFactory;

    public ProjectService() {
        this.daoFactory = new JDBCDaoFactoryImpl();
    }

    public Project buildProject(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter(TableParameters.PROJECT_ID));
        String name = request.getParameter(TableParameters.PROJECT_NAME);
        LocalDate deadline = LocalDate.parse(request.getParameter(TableParameters.PROJECT_DEADLINE));
        Project.Status status = Project.Status.valueOf(request.getParameter(TableParameters.PROJECT_STATUS).toUpperCase());

        return new Project(id, name, deadline, status);
    }

    public Project buildNewProject(HttpServletRequest request){
        String name = request.getParameter(TableParameters.PROJECT_NAME);
        LocalDate deadline = LocalDate.parse(request.getParameter(TableParameters.PROJECT_DEADLINE));
        Project.Status status = Project.Status.valueOf(request.getParameter(TableParameters.PROJECT_STATUS).toUpperCase());

        return new Project(name, deadline, status);
    }

    public void tryToPutProjectDataIntoDB(Project project, HttpServletRequest request) {
        if (checkDataFromWebForCorrectness(project, request)) {
                sendReadyRegistrationDataToDB(project);
        }
        else {
            throw new RuntimeException(ExceptionMessages.BAD_NEW_PROJECT_DATA);
        }
    }

    public void tryToPutUpdateDataFromEmployeeIntoDB(Project project, HttpServletRequest request) {
        if (checkDataFromWebForCorrectness(project, request)) {
                sendReadyUpdateDataToDB(project);
        }
        else {
            throw new RuntimeException(ExceptionMessages.BAD_UPDATE_PROJECT_DATA);
        }
    }

    /**
     * Matches data from web with regexps. For some types of data that is not String,
     * performs other checks.
     * @param
     * @param request
     * @return boolean
     */
    private boolean checkDataFromWebForCorrectness(Project project, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = session.getAttribute(ReqSesParameters.LANGUAGE) == null ?
                Service.ENGLISH : (String)session.getAttribute(ReqSesParameters.LANGUAGE);
        bundleInitialization(new Locale(locale));

        boolean check = true;
        check &= (project.getId() == null || matchInputWithRegexp(String.valueOf(project.getId()), regexpBundle.getString(RegExp.PROJECT_ID)));
        check &= matchInputWithRegexp(project.getName(), regexpBundle.getString(RegExp.PROJECT_NAME));
        check &= matchInputWithRegexp(String.valueOf(project.getDeadline()), regexpBundle.getString(RegExp.PROJECT_DEADLINE));
        check &= matchInputWithRegexp(project.getStatus().name(), regexpBundle.getString(RegExp.PROJECT_STATUS));
        return check;
    }

    /**
     * Sends checked data to user to be put into DB.
     * @param
     */
    private void sendReadyRegistrationDataToDB(Project project) {
        ProjectDao dao = daoFactory.createProjectDao();
        dao.create(project);
    }

    /**
     * Sends checked data to user to be put into DB.
     * @param
     */
    public void sendReadyUpdateDataToDB(Project project) {
        ProjectDao dao = daoFactory.createProjectDao();
        dao.update(project);
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

    public void setProjectEnteredDataBackToForm(HttpServletRequest request, Project project){
        request.setAttribute(TableParameters.PROJECT_ID, project.getId());
        request.setAttribute(TableParameters.PROJECT_NAME, project.getName());
        request.setAttribute(TableParameters.PROJECT_DEADLINE, project.getDeadline());
        request.setAttribute(TableParameters.PROJECT_STATUS, project.getStatus());
    }

    public Project findById(Integer id){
        ProjectDao dao = daoFactory.createProjectDao();
        return dao.findById(id);
    }

    public List<Project> findAll(){
        ProjectDao dao = daoFactory.createProjectDao();
        return dao.findAll();
    }

    public List<Project> findAllActive(){
        ProjectDao dao = daoFactory.createProjectDao();
        return dao.findAllActive();
    }

    public List<Project> findAllArchived(){
        ProjectDao dao = daoFactory.createProjectDao();
        return dao.findAllArchived();
    }

    public List<Project> findAllById(Integer id){
        ProjectDao dao = daoFactory.createProjectDao();
        return dao.findAllByEmployeeId(id);
    }

    public List<Project> findAllByStatus(String status){
        ProjectDao dao = daoFactory.createProjectDao();
        return dao.findAllByStatus(status);
    }

    public void deleteById(Integer id){
        ProjectDao dao = daoFactory.createProjectDao();
        dao.delete(id);
    }

    public List<String> getFieldNames() {
        return Arrays.asList(Entity.TASK_ID, Entity.TASK_NAME, Entity.TASK_PROJECT_ID, Entity.TASK_STATUS,
                Entity.TASK_DEADLINE, Entity.TASK_SPENT_TIME);
    }
}