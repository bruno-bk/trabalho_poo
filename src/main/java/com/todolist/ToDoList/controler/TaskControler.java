package com.todolist.ToDoList.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.ToDoList.model.Task;
import com.todolist.ToDoList.repository.TaskRepository;

@RestController
@RequestMapping("tasks")
public class TaskControler {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping
	public List<Task> getTasks() {
		return taskRepository.findByDeletedFalse();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null && existingTask.isDeleted() == false) {
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.isCompleted());
            Task updated = taskRepository.save(existingTask);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
    	Task existingTask = taskRepository.findById(id).orElse(null);
    	if (existingTask != null) {
    		existingTask.setDeleted(true);
    		taskRepository.save(existingTask);
    		return ResponseEntity.ok(existingTask);
    	}
    	return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/bin")
    public List<Task> getDeletedTasks() {
        return taskRepository.findByDeletedTrue();
    }
    
    @PutMapping("/recover/{id}")
    public ResponseEntity<Task> recoverDeletedTasks(@PathVariable Long id) {
    	Task existingTask = taskRepository.findById(id).orElse(null);
    	if (existingTask != null) {
    		existingTask.setDeleted(false);
    		taskRepository.save(existingTask);
    		return ResponseEntity.ok(existingTask);
    	}
    	return ResponseEntity.notFound().build();
    }
}
