package com.soliman.online_voting_system.model;

public class CandidateInfo {

	private String positinName;
	private Integer positionId;
	private Integer candidateId;
	private String candidateName;
	private String candidateEmail;
	private String candidatePhoto;

	public String getPositinName() {
		return positinName;
	}

	public void setPositinName(String positinName) {
		this.positinName = positinName;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public Integer getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getCandidatePhoto() {
		return candidatePhoto;
	}

	public void setCandidatePhoto(String candidatePhoto) {
		this.candidatePhoto = candidatePhoto;
	}

	public CandidateInfo(String positinName, Integer positionId, Integer candidateId) {
		super();
		this.positinName = positinName;
		this.positionId = positionId;
		this.candidateId = candidateId;
	}

	public CandidateInfo() {
		super();
	}

	@Override
	public String toString() {
		return "CandidateInfo [positinName=" + positinName + ", positionId=" + positionId + ", candidateId="
				+ candidateId + ", candidateName=" + candidateName + ", candidateEmail=" + candidateEmail
				+ ", candidatePhoto=" + candidatePhoto + "]";
	}

	
	
	
	
}
