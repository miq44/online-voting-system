package com.soliman.online_voting_system.model;


import java.util.List;


public class ElectionModel {

	private String electionName;

	private Integer numOfPosition;
	
	private Integer numOfVoter;
	
	private Integer electionStatus;
	
	private String electionToken;

	public String getElectionToken() {
		return electionToken;
	}

	public void setElectionToken(String electionToken) {
		this.electionToken = electionToken;
	}

	public Integer getElectionStatus() {
		return electionStatus;
	}

	public void setElectionStatus(Integer electionStatus) {
		this.electionStatus = electionStatus;
	}

	public Integer getNumOfVoter() {
		return numOfVoter;
	}

	public void setNumOfVoter(Integer numOfVoter) {
		this.numOfVoter = numOfVoter;
	}

	private String startingTime;

	private String endingTime;
	
	private Integer electionId;

	public Integer getElectionId() {
		return electionId;
	}

	public void setElectionId(Integer integer) {
		this.electionId = integer;
	}

	public String getElectionName() {
		return electionName;
	}

	public void setElectionName(String electionName) {
		this.electionName = electionName;
	}

	public Integer getNumOfPosition() {
		return numOfPosition;
	}

	public void setNumOfPosition(Integer numOfPosition) {
		this.numOfPosition = numOfPosition;
	}

	public String getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}

	public String getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(String endingTime) {
		this.endingTime = endingTime;
	}

	@Override
	public String toString() {
		return "ElectionModel [electionName=" + electionName + ", numOfPosition=" + numOfPosition + ", startingTime="
				+ startingTime + ", endingTime=" + endingTime + "]";
	}

	
	
	
}
