package com.soliman.online_voting_system.model;

public class SingleVote {
	private Integer positionId;
	private String positionName;
	private Integer candidateId;
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Integer getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}
	@Override
	public String toString() {
		return "SingleVote [positionId=" + positionId + ", positionName=" + positionName + ", candidateId="
				+ candidateId + "]";
	}
	
	
}
