package com.soliman.online_voting_system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soliman.online_voting_system.model.ElectionModel;
import com.soliman.online_voting_system.service.ElectionGeneralService;

@Controller
public class DashboardController {
	
	@Autowired
	private ElectionGeneralService electionService;
	
	@GetMapping("/")
	public String showHome() {
		return "redirect:/dashboard";
	}

	@GetMapping("/dashboard")
	public String getDashboard(Model model) {

		List<ElectionModel> elections;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		elections = electionService.getMyELections(userName);
		model.addAttribute("electionModels", elections);
		return "dashboard";
	}

	@PostMapping("/beginElection")
	public String beginElection(@RequestParam("electionId") Integer electionId, HttpServletRequest req) {
		String baseUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
				+ req.getContextPath();

		electionService.beginElection(electionId, baseUrl);
		return "redirect:/dashboard";
	}

	@PostMapping("/endElection")
	public String endElection(@RequestParam("electionId") Integer electionId, HttpServletRequest req) {
		String baseUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
				+ req.getContextPath();
		electionService.endElection(electionId, baseUrl);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/app-tour")
	public String getAppTour() {
		return "app-tour";
	}
}
