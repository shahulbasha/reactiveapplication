package org.shahul.reactivedemo.api.controller;

import org.shahul.reactivedemo.api.model.UserResponseModel;
import org.shahul.reactivedemo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	UserService service;

	@GetMapping("/user/{id}")
	public Mono<UserResponseModel> getUserDetails(@PathVariable String id){
		return service.getUserDetails(id);
	}
	
	@GetMapping("/users/{id}")
	public UserResponseModel getUserDetailsBlocking(@PathVariable String id){
		return service.getUserDetailsBlocking(id);
	}
}
