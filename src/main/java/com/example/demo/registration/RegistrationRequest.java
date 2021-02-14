package com.example.demo.registration;

import org.springframework.validation.annotation.Validated;



import lombok.*;



@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
@Validated
public class RegistrationRequest {
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	private String firstName;
	private String lastName;
	private  String email;
	private String password;
//	public RegistrationRequest(  String firstName, String lastName, String email, String password) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.password = password;
//	}
	
	
	
}
