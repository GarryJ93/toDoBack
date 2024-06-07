package fr.sncf.d2d.exo.taskList.controllers;

import fr.sncf.d2d.exo.taskList.models.ResponseWrapper;
import fr.sncf.d2d.exo.taskList.models.Task;
import fr.sncf.d2d.exo.taskList.models.TaskRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.sncf.d2d.exo.taskList.services.TaskService;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/api/task")
    public List<Task> getAllTasks() {
        List<Task> resultSet = taskService.allTasks();

        // StringBuilder result = new StringBuilder("<h2>Il y a " + resultSet.size() + " tâches dans votre liste.</h2>");

        // for (Task t : resultSet) {
        //     result.append("<p>").append(t.getId()).append(" ").append(t.getDone()).append(" ").append(t.getTitle()).append("</p>");
        // }

        return resultSet;
    }

    @PostMapping("/api/task")
    public String addNewTask(@RequestBody TaskRequest taskRequest) {
        taskService.addTask(taskRequest.getTitle());
        return "<h2>Tâche créée avec succès</h2>";

    }

    @DeleteMapping("/api/task/{id}")
    public ResponseWrapper deleteTask(@PathVariable Long id) {
       return taskService.deleteTask(id);
        
    }

    @PatchMapping("/api/task/{id}")
    public List<Task> updateDone(@PathVariable Long id) {
        taskService.updateState(id);
        List<Task> resultSet = getAllTasks();
       return resultSet;
    }

    @PatchMapping("/api/title/{id}")
    public List<Task> updateTitle(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        taskService.updateTitle(id, taskRequest.getTitle());
        List<Task> resultSet = getAllTasks();
        return resultSet;
    }
}

