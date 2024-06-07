package fr.sncf.d2d.exo.taskList.repositories;

import org.springframework.stereotype.Repository;

import fr.sncf.d2d.exo.taskList.models.Task;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
