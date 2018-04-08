package com.soliman.online_voting_system.service;

import java.util.List;

import com.soliman.online_voting_system.model.VoterInfo;

public interface VoterService {

	String getElectionNameByElectionId(Integer electionId);

	List<VoterInfo> getVotersByElectionId(Integer electionId);

	void saveVoters(List<String> validEmailList, Integer electionId);

	boolean updateVoter(Integer electionId, Integer voterId, String voterEmailId);

	void deleteVoter(Integer electionId, Integer voterId);

}
