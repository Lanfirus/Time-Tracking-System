package ua.training.tts.model.entity;

import ua.training.tts.constant.model.Entity;

import java.util.Map;

public class Task {

    private Map<String, String> taskData;

    public Task(Map<String, String> taskData){
        this.taskData = taskData;
    }

    public String getID(){
        return taskData.get(Entity.TASK_ID);
    }

    public String getName(){
        return taskData.get(Entity.TASK_NAME);
    }

    public String getProjectID(){
        return taskData.get(Entity.TASK_PROJECT_ID);
    }

    public String getStatus(){
        return taskData.get(Entity.TASK_STATUS);
    }

    public String getDeadline(){
        return taskData.get(Entity.TASK_DEADLINE);
    }

    public String getSpentTime(){
        return taskData.get(Entity.TASK_SPENT_TIME);
    }

    public Map<String, String> getTaskData(){
        return taskData;
    }
}
