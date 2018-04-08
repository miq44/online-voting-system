package com.soliman.online_voting_system.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soliman.online_voting_system.entity.User;
import com.soliman.online_voting_system.model.NewUser;
import com.soliman.online_voting_system.service.UserService;

@Controller
public class ProfileManagementController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/manage-profile")
	public String showManageProfile(Model model) {
		if (!model.containsAttribute("newUser")) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userName = authentication.getName();
			User userObj = userService.getUserByUserId(userName);
			NewUser newUser = new NewUser(userName, userObj.getEmailId(), userObj.getFirstName(), userObj.getLastName(),
					"");
			model.addAttribute("newUser", newUser);
		} 

		return "my-profile";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@Valid @ModelAttribute("newUser") NewUser newUser, BindingResult bindingResult,
			RedirectAttributes attr, HttpSession session) {

		if (bindingResult.hasErrors()) {
			attr.addFlashAttribute("org.springframework.validation.BindingResult.newUser", bindingResult);
			attr.addFlashAttribute("newUser", newUser);
			return "redirect:/manage-profile?error";
			// return "redirect:/manage-profile?error";
		} else {
			User user = new User(newUser.getUserName(), newUser.getEmailId(), newUser.getFirstName(),
					newUser.getLastName(), passwordEncoder.encode(newUser.getPassword()), 1);
			userService.updateUser(user);
			return "redirect:/manage-profile?success";
		}
	}
}
