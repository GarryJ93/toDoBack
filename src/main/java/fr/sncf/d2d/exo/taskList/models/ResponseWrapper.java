package fr.sncf.d2d.exo.taskList.models;

public class ResponseWrapper {
    private Task task;
    private String message;

    public ResponseWrapper(Task task) {
        this.task = task;
    }

    public ResponseWrapper(String message) {
        this.message = message;
    }

    public Task getTask() {
        return task;
    }

    public String getMessage() {
        return message;
    }
}
