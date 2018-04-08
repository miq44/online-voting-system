package com.soliman.online_voting_system.dao;

import java.util.List;

import com.soliman.online_voting_system.entity.Election;
import com.soliman.online_voting_system.entity.Vote;
import com.soliman.online_voting_system.entity.Voter;



public interface ElectionGeneralDAO {
  public void saveELection(Election election);

public Election getElectionById(Integer electionId);

public void saveVoters(List<Voter> voters);

public Voter getVoterById(Integer voterid);

public void saveVoter(Voter voter);

public void deleteVoter(Voter voter);

public Integer getElectionIdByToken(String token);

public void saveVote(Vote vote);

public Voter getVoterByToken(String token);

public String getElectionResultByElectionId(Integer electionId);

public Integer getElectionIdByElectionToken(String token);


}
