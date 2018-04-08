package com.soliman.online_voting_system.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soliman.online_voting_system.dao.ElectionGeneralDAO;
import com.soliman.online_voting_system.entity.Candidate;
import com.soliman.online_voting_system.entity.Election;
import com.soliman.online_voting_system.entity.Position;
import com.soliman.online_voting_system.entity.Vote;
import com.soliman.online_voting_system.entity.Voter;
import com.soliman.online_voting_system.model.SingleVote;
import com.soliman.online_voting_system.model.VoteModel;

@Service
public class VotingServiceImpl implements VotingService{

	@Autowired
	private ElectionGeneralDAO electionGeneralDao;
	
	@Override
	@Transactional
	public void saveVotes(VoteModel voteModel) {
		// TODO Auto-generated method stub
		String token = voteModel.getVoterToken();
		Voter voter = electionGeneralDao.getVoterByToken(token);
		Integer electionId = electionGeneralDao.getElectionIdByToken(token);
		Election election = electionGeneralDao.getElectionById(electionId);
		for ( SingleVote thisVote : voteModel.getVotes()) {
			Vote vote = new Vote();
			
			Candidate candidate = new Candidate();
			candidate.setId(thisVote.getCandidateId());
			Position position = new Position();
			position.setId(thisVote.getPositionId());
			vote.setElection(election);
			vote.setCandidate(candidate);
			vote.setPosition(position);
			electionGeneralDao.saveVote(vote);
		}
		voter.setVotingStatus(1);
		electionGeneralDao.saveVoter(voter);
	}

}
