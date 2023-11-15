package com.todolist.ToDoList.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return taskRepository.findAll();
	}
}
