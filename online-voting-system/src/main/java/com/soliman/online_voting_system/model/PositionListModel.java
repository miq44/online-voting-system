package com.soliman.online_voting_system.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PositionListModel {

	private String electionName;

	private Integer numOfPosition;

	private String startingTime;

	private String endingTime;
	
	private List<PositionModel> positions;

	public PositionListModel(String electionName, Integer numOfPosition) {
		super();
		this.electionName = electionName;
		this.numOfPosition = numOfPosition;
		
		
		positions = new ArrayList<>();
		for(int i=1;i<=numOfPosition;i++) {
			PositionModel pos = new PositionModel();
			pos.setPositionId(i);
			positions.add(pos);
		}
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

	

	public List<PositionModel> getPositions() {
		return positions;
	}

	public void setPositions(List<PositionModel> positions) {
		this.positions = positions;
	}
	
	public PositionListModel() {
		
	}
	
	public PositionListModel(int numOfPosition) {
		positions = new ArrayList<>();
		for(int i=1;i<=numOfPosition;i++) {
			PositionModel pos = new PositionModel();
			positions.add(pos);
		}
	}

	@Override
	public String toString() {
		return "PositionListModel [electionName=" + electionName + ", numOfPosition=" + numOfPosition
				+ ", startingTime=" + startingTime + ", endingTime=" + endingTime + ", positions=" + positions + "]";
	}
	
	
	
}
