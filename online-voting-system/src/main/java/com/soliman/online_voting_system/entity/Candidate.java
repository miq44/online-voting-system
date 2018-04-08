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
@Table(name="candidates")
public class Candidate {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="candidate_name")
	private String candidateName;
	
	@Column(name="candidate_email_id")
	private String candidateEmailId;
	
	@Column(name="candidate_photo")
	private String candidatePhotoLocation;
	
	@ManyToOne  
	@JoinColumn(name="position_id")
	private Position position;
	
	@OneToMany(mappedBy="candidate", cascade=CascadeType.ALL)
	private List<Vote> votes;

	public void addVote(Vote vote) {
		if(votes==null) {
			votes = new ArrayList<>();
		}
		votes.add(vote);
		vote.setCandidate(this);
	}
	
	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateEmailId() {
		return candidateEmailId;
	}

	public void setCandidateEmailId(String candidateEmailId) {
		this.candidateEmailId = candidateEmailId;
	}

	public String getCandidatePhotoLocation() {
		return candidatePhotoLocation;
	}

	public void setCandidatePhotoLocation(String candidatePhotoLocation) {
		this.candidatePhotoLocation = candidatePhotoLocation;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Candidate(String candidateName, String candidateEmailId, String candidatePhotoLocation) {
		super();
		this.candidateName = candidateName;
		this.candidateEmailId = candidateEmailId;
		this.candidatePhotoLocation = candidatePhotoLocation;
	}

	public Candidate() {
		super();
	}
	
	
}
