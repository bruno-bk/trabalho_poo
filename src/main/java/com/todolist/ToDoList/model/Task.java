package com.todolist.ToDoList.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private boolean completed;
	
	@Column(nullable = false)
	private boolean deleted;
	
	@Column(updatable = false)
	private LocalDateTime creation_date;
	
	@PrePersist
	protected void onCreate() {
		this.creation_date = LocalDateTime.now();
	}

}
