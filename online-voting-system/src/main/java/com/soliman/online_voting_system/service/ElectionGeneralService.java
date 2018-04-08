package com.soliman.online_voting_system.service;

import java.util.List;
import java.util.Map;

import com.soliman.online_voting_system.model.CandidateDetails;
import com.soliman.online_voting_system.model.CandidateInfo;
import com.soliman.online_voting_system.model.ElectionModel;
import com.soliman.online_voting_system.model.PositionListModel;
import com.soliman.online_voting_system.model.VoteModel;
import com.soliman.online_voting_system.model.VoterInfo;



public interface ElectionGeneralService {

	public void saveELection(PositionListModel positionListModel, CandidateDetails candidateDetailsModel);

	public List<ElectionModel> getMyELections(String userName);

	public Map<String, List<CandidateInfo>> getCandidateMapByElectionId(Integer electionId);

	public void beginElection(Integer electionId, String baseUrl);

	public Integer getElectionIdByToken(String token);

	public String getElectionResultByElectionId(Integer electionId);

	public void endElection(Integer electionId, String baseUrl);

	public Integer getElectionIdByElectionToken(String token);

	public String getElectionNameById(Integer electionId);
}
