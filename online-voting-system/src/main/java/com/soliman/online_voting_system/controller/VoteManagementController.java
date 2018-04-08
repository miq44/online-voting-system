package com.soliman.online_voting_system.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.soliman.online_voting_system.model.CandidateInfo;
import com.soliman.online_voting_system.model.SingleVote;
import com.soliman.online_voting_system.model.VoteModel;
import com.soliman.online_voting_system.service.ApplicationUtilityService;
import com.soliman.online_voting_system.service.ElectionGeneralService;
import com.soliman.online_voting_system.service.ValidationService;
import com.soliman.online_voting_system.service.VotingService;

@Controller
public class VoteManagementController {

	@Autowired
	private VotingService votingService;
	@Autowired
	private ElectionGeneralService electionService;
	@Autowired
	private ValidationService validationService;
	
	@Autowired
	private ApplicationUtilityService applicationService;
	@GetMapping("/my-vote/{token}")
	public String myVote(@PathVariable("token") String token, Model model) {
		Integer electionId = electionService.getElectionIdByToken(token);
		if (electionId == 0) {
			return "my-vote-finish";
		} else {
			Map<String, List<CandidateInfo>> candidates = electionService.getCandidateMapByElectionId(electionId);
			VoteModel voteModel = new VoteModel();
			voteModel.setVoterToken(token);
			List<SingleVote> votes = new ArrayList<>();
			for (String key : candidates.keySet()) {
				SingleVote singleVote = new SingleVote();
				singleVote.setPositionName(key);
				votes.add(singleVote);
			}
			voteModel.setVotes(votes);
			model.addAttribute("map", candidates);
			model.addAttribute("votesModel", voteModel);
			model.addAttribute("token",token);
			return "my-vote";
		}

	}

	@PostMapping("/saveVote")
	public String saveVote(@ModelAttribute("votesModel") VoteModel voteModel, Model model) {
		boolean isValidVote = validationService.isValidVote(voteModel);
		System.out.println(voteModel);
		if (isValidVote) {

			model.addAttribute("status", "Thanks for your vote !");
			votingService.saveVotes(voteModel);
		} else {
			model.addAttribute("status", "Something went wrong ! Please try later. ");
		}
		return "my-vote-finish";
	}

	@GetMapping("/my-vote-result/{token}")
	public String getElectionResult(@PathVariable("token") String token, Model model) {
		Integer electionId = electionService.getElectionIdByElectionToken(token);
		String electionName = electionService.getElectionNameById(electionId);
		String resultJson = electionService.getElectionResultByElectionId(electionId);
		model.addAttribute("data", resultJson);
		model.addAttribute("organizationName",electionName);
		if(applicationService.isUserLoggedIn()) {
			return "my-vote-result";
		}else {
			return "my-vote-result-member";
		}
		
	}
	
}
