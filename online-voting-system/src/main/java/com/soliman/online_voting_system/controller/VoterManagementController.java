package com.soliman.online_voting_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soliman.online_voting_system.model.VoterInfo;
import com.soliman.online_voting_system.service.ValidationService;
import com.soliman.online_voting_system.service.VoterService;

@Controller
public class VoterManagementController {

	@Autowired
	private VoterService voterService;
	
	@Autowired
	private ValidationService validationService;
	
	@GetMapping("/addVoter/{election_id}")
	public String addVoter(@PathVariable("election_id") Integer electionId, Model model) {
		
		VoterInfo voterInfo = new VoterInfo();
		voterInfo.setElectionId(electionId);
		model.addAttribute("voters", voterInfo);
		return "add-voter";
	}

	@PostMapping("/saveVoters")
	public String saveVoters(@ModelAttribute("voters") VoterInfo voterInfo) {
		String mailList = voterInfo.getVoterList();
		Integer electionId = voterInfo.getElectionId();
		String[] mails = mailList.split("\\s+");
		Map<String, List<String>> emailMap = validationService.validateEmailList(mails);
		List<String> validEmailList = emailMap.get("valid");
		if (validEmailList != null) {
			voterService.saveVoters(validEmailList, electionId);
		}
		return "redirect:/dashboard";
	}

	@GetMapping("/manageVoter/{election_id}")
	public String manageVoter(@PathVariable("election_id") Integer electionId, Model model) {
		String electionName = voterService.getElectionNameByElectionId(electionId);
		model.addAttribute("electionName", electionName);
		model.addAttribute("electionId", electionId);
		List<VoterInfo> voters = voterService.getVotersByElectionId(electionId);
		System.out.println(voters);
		model.addAttribute("voters", voters);
		return "manage-voter";
	}

	@PostMapping("/editVoter")
	public String editVoter(@RequestParam("voterEmailId") String voterEmailId, @RequestParam("voterId") Integer voterId,
			@RequestParam("electionId") Integer electionId, Model model) {
		boolean isSuccess = voterService.updateVoter(electionId, voterId, voterEmailId);
		if (isSuccess) {
			model.addAttribute("success", "Voter's Email Id - " + voterEmailId + " updated successfully !");
		} else {
			model.addAttribute("error", "Something went wrong ! Either " + voterEmailId
					+ " is not valid or already existed for this election");
		}

		return "redirect:/manageVoter/" + electionId;
	}

	@PostMapping("/deleteVoter")
	public String deleteVoter(@RequestParam("voterId") Integer voterId, @RequestParam("electionId") Integer electionId,
			@RequestParam("voterEmailId") String voterEmailId, Model model) {

		voterService.deleteVoter(electionId, voterId);
		model.addAttribute("success", "Voter's Email Id - " + voterEmailId + " deleted successfully !");

		return "redirect:/manageVoter/" + electionId;
	}
	
}
