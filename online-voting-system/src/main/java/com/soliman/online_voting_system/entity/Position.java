package com.soliman.online_voting_system.entity;

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
@Table(name="positions")
public class Position {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="position_name")
	private String positionName;
	
	@Column(name="no_of_candidate")
	private Integer noOfCandidate;
	

	
	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	@ManyToOne
	@JoinColumn(name="election_id")
	private Election election;
	
	@OneToMany(mappedBy="position" , cascade= {CascadeType.ALL})
	private List<Candidate> candidates;
	
	@OneToMany(mappedBy="position", cascade = CascadeType.ALL)
	private List<Vote> votes;
	
	public void addVote(Vote vote) {
		if(votes==null) {
			votes = new ArrayList<>();
		}
		votes.add(vote);
		vote.setPosition(this);
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public int getId() {
		return id;
	}

	public void addCandidate(Candidate candidate) {
		if(candidates==null) {
			candidates = new ArrayList<>();
		}
		
		candidates.add(candidate);
		candidate.setPosition(this);
	}
	
	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Position(String positionName, Integer noOfCandidate) {
		super();
		this.positionName = positionName;
		this.noOfCandidate = noOfCandidate;
	}

	public Position() {
		super();
	}
	
	
	
}
