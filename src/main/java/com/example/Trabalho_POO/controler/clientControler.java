package com.example.Trabalho_POO.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello_world")
public class clientControler {
	
	@GetMapping
	public String hello() {
		return "Hello World!";
	}
	
}
