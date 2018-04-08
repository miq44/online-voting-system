package com.soliman.online_voting_system.model;

import java.util.ArrayList;
import java.util.List;

public class CandidateDetails {

	
	private List<CandidateInfo> candidates;

	public List<CandidateInfo> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<CandidateInfo> candidates) {
		this.candidates = candidates;
	}

	@Override
	public String toString() {
		return "CandidateDetails [candidates=" + candidates + "]";
	}
	
	public List<CandidateInfo> getCandidatesByPositionId(int positionId) {
		List<CandidateInfo> filteredCandidates = new ArrayList<>();
		for(CandidateInfo candidate:candidates) {
			if(candidate.getPositionId()==positionId) {
				filteredCandidates.add(candidate);
			}
		}
		return filteredCandidates;
	}
		
}
