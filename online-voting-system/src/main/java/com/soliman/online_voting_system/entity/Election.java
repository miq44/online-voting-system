package com.soliman.online_voting_system.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="elections")
public class Election {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="election_name")
	private String electionName;
	
	@Column(name="num_of_position")
	private Integer numOfPosition;
	
	@Column(name="num_of_voter")
	private Integer numOfVoter;
	
	@Column(name="election_token")
	private String electionToken;
	
	public String getElectionToken() {
		return electionToken;
	}

	public void setElectionToken(String electionToken) {
		this.electionToken = electionToken;
	}

	public Integer getNumOfVoter() {
		return numOfVoter;
	}

	public void setNumOfVoter(Integer numOfVoter) {
		this.numOfVoter = numOfVoter;
	}

	@Column(name="starting_time")
	private Timestamp startingTime;
	
	@Column(name="ending_time")
	private Timestamp endingTime;
	
	@Column(name="election_status")
	private Integer electionStatus;
	
	
	public Integer getElectionStatus() {
		return electionStatus;
	}

	public void setElectionStatus(Integer electionStatus) {
		this.electionStatus = electionStatus;
	}

	@ManyToOne
	@JoinColumn(name="created_by")
	private User createdBy;
	
	@OneToMany(mappedBy="election", cascade=CascadeType.ALL)
	private List<Position> positions;
	
	@OneToMany(mappedBy="election" , cascade=CascadeType.ALL)
	private List<Voter> voters;
	
	@OneToMany(mappedBy="election" , cascade = CascadeType.ALL)
	private List<Vote> votes;
	

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public List<Voter> getVoters() {
		return voters;
	}
	
	public void addVoter(Voter voter) {
		if(voters==null) {
			voters = new ArrayList<>();
		}
		voters.add(voter);
		voter.setElection(this);
	}
	
	public void addVote(Vote vote) {
		if(votes == null) {
			votes = new ArrayList<>();
		}
		votes.add(vote);
		vote.setElection(this);
	}

	public void setVoters(List<Voter> voters) {
		this.voters = voters;
	}

	public void addPosition(Position tempPosition) {
		if(positions == null) {
			positions = new ArrayList<>();
		}
		
		positions.add(tempPosition);
		tempPosition.setElection(this);
	}
	
	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Timestamp getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(Timestamp startingTime) {
		this.startingTime = startingTime;
	}

	public Timestamp getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(Timestamp endingTime) {
		this.endingTime = endingTime;
	}

	public Election(String electionName, Integer numOfPosition, Timestamp startingTime, Timestamp endingTime) {
		super();
		this.electionName = electionName;
		this.numOfPosition = numOfPosition;
		this.startingTime = startingTime;
		this.endingTime = endingTime;
	}

	public Election(String electionName, Integer numOfPosition,User createdBy) {
		this.electionName = electionName;
		this.numOfPosition = numOfPosition;
		this.createdBy = createdBy;
	}

	public Election() {
	}
	
	
}
