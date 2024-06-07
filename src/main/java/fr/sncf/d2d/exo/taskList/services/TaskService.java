package fr.sncf.d2d.exo.taskList.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sncf.d2d.exo.taskList.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import fr.sncf.d2d.exo.taskList.models.ResponseWrapper;
import fr.sncf.d2d.exo.taskList.models.Task;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> allTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> taskById(Long id) {
        return taskRepository.findById(id);
    }

    public void addTask(String title) {
        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setDone(false);

        taskRepository.save(newTask);
    }

    public ResponseWrapper deleteTask(Long id) {
        try {
            Optional<Task> taskOptional = taskRepository.findById(id);

            if (taskOptional.isPresent()) {
                taskRepository.deleteById(id);
                return new ResponseWrapper(taskOptional.get()) ;
            } else {
                return new ResponseWrapper("La tâche avec l'ID spécifié n'existe pas");
            }
        } catch (Exception e) {
            return new ResponseWrapper("Une erreur s'est produite lors de la suppression de la tâche : " + e.getMessage()) ;
        }
    }

    public void updateState(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task taskToUpdate = taskOptional.get();
            boolean updatedDoneState = !taskToUpdate.getDone();
            taskToUpdate.setDone(updatedDoneState);
            taskRepository.save(taskToUpdate);
        } else {
            throw new EntityNotFoundException("Tâche non trouvée avec l'ID : " + id);
        }
    }

    public Task updateTitle(Long id, String title) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task taskToUpdate = taskOptional.get();
            taskToUpdate.setTitle(title);
            taskRepository.save(taskToUpdate);
            return taskToUpdate;
        } else {
            throw new EntityNotFoundException("Tâche non trouvée avec l'ID : " + id);
        }
    }
}



 

