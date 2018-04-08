package com.soliman.online_voting_system.model;

import java.util.List;

public class VoteModel {

	private List<SingleVote> votes;
	private String voterToken;

	

	public String getVoterToken() {
		return voterToken;
	}

	public void setVoterToken(String voterToken) {
		this.voterToken = voterToken;
	}

	public List<SingleVote> getVotes() {
		return votes;
	}

	public void setVotes(List<SingleVote> votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "VoteModel [votes=" + votes + ", voterToken=" + voterToken + "]";
	}

	
	
	
	
	
}
