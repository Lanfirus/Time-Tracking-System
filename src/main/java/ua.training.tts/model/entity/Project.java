package ua.training.tts.model.entity;

import ua.training.tts.constant.model.Entity;

import java.util.Map;

public class Project {

    private Map<String, String> projectData;

    public Project(Map<String, String> projectData){
        this.projectData = projectData;
    }

    public String getID(){
        return projectData.get(Entity.PROJECT_ID);
    }

    public String getName(){
        return projectData.get(Entity.PROJECT_NAME);
    }

    public String getDeadline(){
        return projectData.get(Entity.PROJECT_DEADLINE);
    }

    public String getCostCenter(){
        return projectData.get(Entity.PROJECT_COSTCENTER);
    }

    public String getTeamSize(){
        return projectData.get(Entity.PROJECT_TEAM_SIZE);
    }

    public String getTaskQty(){
        return projectData.get(Entity.PROJECT_TASK_QTY);
    }

    public Map<String, String> getProjectData(){
        return projectData;
    }
}
