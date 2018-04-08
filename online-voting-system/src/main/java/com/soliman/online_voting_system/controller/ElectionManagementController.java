package com.soliman.online_voting_system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.soliman.online_voting_system.model.CandidateDetails;
import com.soliman.online_voting_system.model.CandidateInfo;
import com.soliman.online_voting_system.model.PositionListModel;
import com.soliman.online_voting_system.model.PositionModel;
import com.soliman.online_voting_system.service.ElectionGeneralService;

@Controller
public class ElectionManagementController {

	@Autowired
	private ElectionGeneralService electionService;
	
	@GetMapping("/create-election")
	public String createElection() {
		return "election";
	}

	@PostMapping(value = "/saveElectionBasicInfo")
	public String saveElectionBasicInfo(@ModelAttribute("positionListModel") PositionListModel positionListModel,
			Model model, HttpSession session) {

		CandidateDetails candidateDetails = new CandidateDetails();
		List<CandidateInfo> candidateInfos = new ArrayList<>();
		for (PositionModel position : positionListModel.getPositions()) {
			Integer positionId = position.getPositionId();
			String positionName = position.getPositionName();
			for (int i = 0; i < position.getNoOfCandidate(); i++) {
				CandidateInfo candidate = new CandidateInfo(positionName, positionId, i + 1);
				candidateInfos.add(candidate);
			}
		}
		candidateDetails.setCandidates(candidateInfos);
		model.addAttribute("candidateDetails", candidateDetails);
		session.setAttribute("positionListModel", positionListModel);
		return "candidate_details";
	}
	@PostMapping("/saveCandidateDetails")
	public String saveCandidateDetails(@ModelAttribute("CandidateDetails") CandidateDetails candidateDetailsModel,
			HttpSession session) {

		PositionListModel positionListModel = (PositionListModel) session.getAttribute("positionListModel");

		electionService.saveELection(positionListModel, candidateDetailsModel);
		session.removeAttribute("positionListModel");
		return "redirect:/dashboard";
	}

	@GetMapping("/viewCandidates/{election_id}")
	public String viewCandidates(@PathVariable("election_id") Integer electionId, Model model) {
		// Map<String,>
		Map<String, List<CandidateInfo>> candidates = electionService.getCandidateMapByElectionId(electionId);
		model.addAttribute("map", candidates);
		return "view-candidates";
	}
}
