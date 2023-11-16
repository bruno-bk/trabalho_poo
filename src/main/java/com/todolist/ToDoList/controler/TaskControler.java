package com.todolist.ToDoList.controler;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
	public ResponseEntity<String> getTasks() {
		List<Task> tasks = taskRepository.findByDeletedFalse();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		for(Task task : tasks)
		{
			JSONObject item = new JSONObject();
			try {
				item.put("id", task.getId());
				item.put("description", task.getDescription());
				item.put("completed", task.isCompleted());
				item.put("deleted", task.isDeleted());
				item.put("creation_date", task.getCreation_date());
			} catch (JSONException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing tasks: " + e.getMessage());
			}
			array.put(item);
		}
		try {
			json.put("dados", array);
		} catch (JSONException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error: " + e.getMessage());
		}
		return ResponseEntity.ok(json.toString());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
		try {
			Task updated = taskRepository.save(task);
			return ResponseEntity.ok(updated);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}	
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null && existingTask.isDeleted() == false) {
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.isCompleted());
            try {
    			Task updated = taskRepository.save(existingTask);
    			return ResponseEntity.ok(updated);
    		}
    		catch(Exception e) {
    			return ResponseEntity.badRequest().build();
    		}	
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
    public ResponseEntity<String> getDeletedTasks() {
        List<Task> tasks = taskRepository.findByDeletedTrue();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		for(Task task : tasks)
		{
			JSONObject item = new JSONObject();
			try {
				item.put("id", task.getId());
				item.put("description", task.getDescription());
				item.put("completed", task.isCompleted());
				item.put("deleted", task.isDeleted());
				item.put("creation_date", task.getCreation_date());
			} catch (JSONException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing tasks: " + e.getMessage());
			}
			array.put(item);
		}
		try {
			json.put("dados", array);
		} catch (JSONException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected error: " + e.getMessage());
		}
		return ResponseEntity.ok(json.toString());
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
