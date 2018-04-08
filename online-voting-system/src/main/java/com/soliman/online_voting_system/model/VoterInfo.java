package com.soliman.online_voting_system.model;

public class VoterInfo {

	private String voterList;
	private Integer electionId;
	private Integer voterId;
	private Integer votingStatus;
	private Integer electionStatus;
	

	public Integer getElectionStatus() {
		return electionStatus;
	}

	public void setElectionStatus(Integer electionStatus) {
		this.electionStatus = electionStatus;
	}

	public Integer getVotingStatus() {
		return votingStatus;
	}

	public void setVotingStatus(Integer votingStatus) {
		this.votingStatus = votingStatus;
	}

	public Integer getVoterId() {
		return voterId;
	}

	public void setVoterId(Integer voterId) {
		this.voterId = voterId;
	}

	public Integer getElectionId() {
		return electionId;
	}

	public void setElectionId(Integer electionId) {
		this.electionId = electionId;
	}

	public String getVoterList() {
		return voterList;
	}

	public void setVoterList(String voterList) {
		this.voterList = voterList;
	}

	@Override
	public String toString() {
		return "VoterInfo [voterList=" + voterList + ", electionId=" + electionId + "]";
	}
	
}
