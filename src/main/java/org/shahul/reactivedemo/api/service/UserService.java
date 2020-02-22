package org.shahul.reactivedemo.api.service;

import org.shahul.reactivedemo.api.model.UserResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	WebClient client;
	@Autowired
	RestTemplate restTemplate;
	
	public Mono<UserResponseModel> getUserDetails(String id){
		System.out.println("ENTERS NON-BLOCKING METHOD");
		Mono<UserResponseModel> monoUser = this.client.get().uri("/home/{id}", id)
				.retrieve()
				.bodyToMono(UserResponseModel.class);

		monoUser.log().subscribe(user->{
			//will print username once the response is received
			System.out.println(user.getName()); 
		});
		System.out.println("NON-BLOCKING METHOD COMPLETED");
		
		//returns the response
		return monoUser;
	}
	
	public UserResponseModel getUserDetailsBlocking(String id){
		System.out.println("ENTERS BLOCKING METHOD");
		UserResponseModel userModel = restTemplate.getForObject("http://localhost:8081/app/home/"+id, UserResponseModel.class);
		System.out.println(userModel.getName());
		System.out.println("ENTERS BLOCKING METHOD");
		return userModel;
	}
}
