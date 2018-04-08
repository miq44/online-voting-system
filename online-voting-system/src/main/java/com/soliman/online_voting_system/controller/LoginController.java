package com.soliman.online_voting_system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.soliman.online_voting_system.entity.Authority;
import com.soliman.online_voting_system.entity.User;
import com.soliman.online_voting_system.model.NewUser;
import com.soliman.online_voting_system.service.ApplicationUtilityService;
import com.soliman.online_voting_system.service.UserService;


@Controller
public class LoginController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationUtilityService appService;

	@InitBinder
	public void initBinder (WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/login")
	public String getLoginPage() {
		if (appService.isUserLoggedIn()) {
			/* The user is logged in :) */
			return "redirect:/dashboard";
		} else {
			return "login-page";
		}
	}

	@GetMapping("/access-denied")
	public String getAccessDeniedPage() {
		return "error-page";

	}

	@GetMapping("/register")
	public String getRegister(Model model) {
		if(appService.isUserLoggedIn()) {
			return "redirect:/";
		}else {
			model.addAttribute("newUser",new NewUser());
			return "register";
		}
		
	}
	
	
	@PostMapping("/registerUser")
	public String processRegistration(
			@Valid @ModelAttribute("newUser") NewUser newUser ,
			BindingResult bindingResult) {
		
		
		if(bindingResult.hasErrors()) {
			
			return "register";
		}else {
			String userName = newUser.getUserName();
			String emailId = newUser.getEmailId();
			if(!userService.isUniqueUserName(userName)) {
				bindingResult.rejectValue("userName","newUser.userName", "This username already exists !");
				return "register";
			}
			
			if(!userService.isUniqueEmail(emailId)) {
				bindingResult.rejectValue("emailId","newUser.emailId", "This email id already exists !");
				return "register";
			}
			
			User user = new User(newUser.getUserName(),
								 newUser.getEmailId(),
								 newUser.getFirstName(), 
								 newUser.getLastName(),
								 passwordEncoder.encode(newUser.getPassword()),
								 1);
			Authority authority = new Authority();
			authority.setAuthority("ROLE_USER");
			user.addAuthority(authority);
			userService.saveUser(user);
			return "redirect:/login?registration_success";
		}
	}

	
}
