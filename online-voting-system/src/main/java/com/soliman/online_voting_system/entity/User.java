package com.soliman.online_voting_system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="username",unique=true)
	private String username;
	
	public User() {
	}

	@Column(name="email_id",unique=true)
	private String emailId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="password")
	private String password;

	@Column(name="enabled")
	private Integer enabled;
	
	@OneToMany(mappedBy="createdBy", cascade= {CascadeType.ALL})
	private List<Election> elections;
	
	@OneToMany(mappedBy="user" , cascade = {CascadeType.ALL})
	private List<Authority> authorities;
	
	
	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public void addElection(Election tempElection) {
		if(elections == null) {
			elections = new ArrayList<>();
		}
		
		elections.add(tempElection);
		tempElection.setCreatedBy(this);
	}
	
	public void addAuthority(Authority thisAuthority) {
		if(authorities == null) {
			authorities = new ArrayList<>();
		}
		
		authorities.add(thisAuthority);
		thisAuthority.setUser(this);
	}
	
	public List<Election> getElections() {
		return elections;
	}

	public void setElections(List<Election> elections) {
		this.elections = elections;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public User(String username, String emailId, String firstName, String lastName, String password, Integer enabled) {
		super();
		this.username = username;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", emailId=" + emailId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + ", enabled=" + enabled + ", elections=" + elections
				+ ", authorities=" + authorities + "]";
	}

	
	
	
	
}
