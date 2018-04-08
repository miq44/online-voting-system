package com.soliman.online_voting_system.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.soliman.online_voting_system.validation.FieldMatch;


@FieldMatch(first="password",second="confirmPassword", message="The password fields must match.")
public class NewUser {

	@NotNull(message="Username is required")
	@Size(min=6, message="Length must be more than 6 character")
	private String userName;
	
	@NotNull(message="Email is required")
	@Email(message="Invalid Email Id")
	@Size(min=1, message="Email is required")
	private String emailId;
	
	@NotNull(message="First Name is required")
	@Size(min=1, message="First Name is required")
	private String firstName;
	
	@NotNull(message="Last Name is required")
	@Size(min=1, message="Last Name is required")
	private String lastName;
	
	@NotNull(message="Password is required")
	@Size(min=6, message="Length must be more than 6 character")
	private String password;
	
	@NotNull(message="Is required")
	@Size(min=6, message="Length must be more than 6 character")
	private String confirmPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "NewUser [userName=" + userName + ", emailId=" + emailId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}

	public NewUser(
			@NotNull(message = "Username is required") @Size(min = 6, message = "Length must be more than 6 character") String userName,
			@NotNull(message = "Email is required") @Email(message = "Invalid Email Id") String emailId,
			@NotNull(message = "First Name is required") String firstName,
			@NotNull(message = "Last Name is required") String lastName,
			@NotNull(message = "Password is required") @Size(min = 6, message = "Length must be more than 6 character") String password) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public NewUser() {
		super();
	}
	
	
}
