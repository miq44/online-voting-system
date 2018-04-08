package com.soliman.online_voting_system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soliman.online_voting_system.dao.ElectionGeneralDAO;
import com.soliman.online_voting_system.entity.Election;
import com.soliman.online_voting_system.entity.Voter;
import com.soliman.online_voting_system.model.VoterInfo;

@Service
public class VoterServiceImpl implements VoterService{

	
	@Autowired
	private ElectionGeneralDAO electionGeneralDao;
	
	@Autowired
	private ValidationService validationService;
	@Autowired
	private ApplicationUtilityService applicationService;
	
	@Override
	@Transactional
	public String getElectionNameByElectionId(Integer electionId) {
		Election election = electionGeneralDao.getElectionById(electionId);
		return election.getElectionName();
	}

	@Override
	@Transactional
	public List<VoterInfo> getVotersByElectionId(Integer electionId) {
		Election election = electionGeneralDao.getElectionById(electionId);
		List<VoterInfo> voters = new ArrayList<>();
		for(Voter voter : election.getVoters()) {
			VoterInfo info = new VoterInfo();
			info.setVoterId(voter.getId());
			info.setElectionId(electionId);
			info.setVoterList(voter.getEmailId());
			info.setElectionStatus(election.getElectionStatus());
			info.setVotingStatus(voter.getVotingStatus());
			voters.add(info);
		}
		return voters;
	}

	@Override
	@Transactional
	public void saveVoters(List<String> emails, Integer electionId){
		Election election = electionGeneralDao.getElectionById(electionId);
		int numberOfVoter = emails.size();
		election.setNumOfVoter(election.getNumOfVoter()+numberOfVoter);
		List<Voter> voters = new ArrayList<>();
		for(String email:emails) {
			Voter voter = new Voter();
			String token = applicationService.createSalt()+applicationService.createSalt(); 
			voter.setEmailId(email);
			voter.setElection(election);
			voter.setToken(token);
			voter.setVotingStatus(0);
			voters.add(voter);
		}
		electionGeneralDao.saveVoters(voters);
	}

	@Override
	@Transactional
	public boolean updateVoter(Integer electionId, Integer voterid, String voterEmailId) {
		boolean status = true;
		if(validationService.isValidAndUnique(electionId, voterEmailId)) {
			Voter voter = electionGeneralDao.getVoterById(voterid);
			voter.setEmailId(voterEmailId);
			electionGeneralDao.saveVoter(voter);
		}else {
			status = false;
		}
		
		return status;
		
	}

	@Override
	@Transactional
	public void deleteVoter(Integer electionId, Integer voterId) {
		Election election = electionGeneralDao.getElectionById(electionId);
		election.setNumOfVoter(election.getNumOfVoter()-1);
		Voter voter = electionGeneralDao.getVoterById(voterId);
		electionGeneralDao.deleteVoter(voter);
		electionGeneralDao.saveELection(election);
	}
	
	

}
