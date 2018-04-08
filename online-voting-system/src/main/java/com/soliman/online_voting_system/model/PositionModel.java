package com.soliman.online_voting_system.model;

import java.util.List;

public class PositionModel {

	private Integer positionId;
	private String positionName;
    private Integer noOfCandidate;

    
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getNoOfCandidate() {
		return noOfCandidate;
	}

	public void setNoOfCandidate(Integer noOfCandidate) {
		this.noOfCandidate = noOfCandidate;
	}

	

	@Override
	public String toString() {
		return "PositionModel [positionId=" + positionId + ", positionName=" + positionName + ", noOfCandidate="
				+ noOfCandidate + "]";
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	
}
