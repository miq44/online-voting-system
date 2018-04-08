package com.soliman.online_voting_system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soliman.online_voting_system.dao.ElectionGeneralDAO;
import com.soliman.online_voting_system.entity.Candidate;
import com.soliman.online_voting_system.entity.Election;
import com.soliman.online_voting_system.entity.Position;
import com.soliman.online_voting_system.entity.Voter;
import com.soliman.online_voting_system.model.SingleVote;
import com.soliman.online_voting_system.model.VoteModel;

@Service
public class ValidationService {

	@Autowired
	private ElectionGeneralDAO electionGeneralDao;

	public Map<String, List<String>> validateEmailList(String[] mailList) {
		Map<String, List<String>> mails = new HashMap<>();
		List<String> validEmailList = null;
		List<String> invalidEmailList = null;
		for (String email : mailList) {
			if (EmailValidator.getInstance().isValid(email)) {
				if (validEmailList == null) {
					validEmailList = new ArrayList<>();
					validEmailList.add(email);
				} else {
					if (!validEmailList.contains(email)) {
						validEmailList.add(email);
					}
				}

			} else {
				if (invalidEmailList == null) {
					invalidEmailList = new ArrayList<>();
				}
				invalidEmailList.add(email);
			}
		}
		mails.put("invalid", invalidEmailList);
		mails.put("valid", validEmailList);

		return mails;
	}

	@Transactional
	public boolean isValidVote(VoteModel voteModel) {
		boolean status = true;
		String token = voteModel.getVoterToken();
		Integer electionId = electionGeneralDao.getElectionIdByToken(token);
		if (electionId == 0) {
			status = false;
		} else {
			Election election = electionGeneralDao.getElectionById(electionId);
			// List<Position> positions = election.getPositions();
			Map<Integer, List<Integer>> positionCandidateMap = new HashMap<>();

			for (Position position : election.getPositions()) {
				Integer positionId = position.getId();
				List<Integer> candidateIdList = new ArrayList<>();
				for (Candidate candidate : position.getCandidates()) {
					candidateIdList.add(candidate.getId());
				}
				positionCandidateMap.put(positionId, candidateIdList);
			}
			System.out.println(voteModel);
			for (SingleVote vote : voteModel.getVotes()) {
				Integer positionId = vote.getPositionId();
				Integer candidateId = vote.getCandidateId();
				
				if (positionCandidateMap.containsKey(positionId)) {
					if (!positionCandidateMap.get(positionId).contains(candidateId)) {
						status = false;
						break;
					}
				} else {
					status = false;
					break;
				}
			}
		}
		return status;
	}

	@Transactional
	public boolean isValidAndUnique(Integer electionId, String voterEmailId) {
		boolean status = true;
		if (!EmailValidator.getInstance().isValid(voterEmailId)) {
			return false;
		}
		Election election = electionGeneralDao.getElectionById(electionId);
		List<Voter> voters = election.getVoters();
		for (Voter voter : voters) {
			if (voter.getEmailId().equalsIgnoreCase(voterEmailId)) {
				status = false;
				break;
			}
		}
		return status;
	}
}
