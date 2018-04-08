package com.soliman.online_voting_system.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.soliman.online_voting_system.entity.Election;
import com.soliman.online_voting_system.entity.Vote;
import com.soliman.online_voting_system.entity.Voter;



@Repository
public class ElectionGeneralDAOImpl implements ElectionGeneralDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveELection(Election election) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(election);
	}

	@Override
	public Election getElectionById(Integer electionId) {
		Session session = sessionFactory.getCurrentSession();
		Election election = session.get(Election.class, electionId);
		return election;
	}

	@Override
	public void saveVoters(List<Voter> voters) {
		Session session = sessionFactory.getCurrentSession();
		for(Voter voter: voters) {
			session.save(voter);
		}
	}

	@Override
	public Voter getVoterById(Integer voterid) {
		Session session = sessionFactory.getCurrentSession();
		Voter voter = session.get(Voter.class, voterid);
		return voter;
	}

	@Override
	public void saveVoter(Voter voter) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(voter);
		
	}

	@Override
	public void deleteVoter(Voter voter) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(voter);
	}

	@Override
	public Integer getElectionIdByToken(String token) {
		Session session = sessionFactory.getCurrentSession();
		Query<Voter> query = session.createQuery("FROM Voter v where v.token =:token and v.votingStatus=:votingStatus",Voter.class);
		query.setParameter("token", token);
		query.setParameter("votingStatus", 0);
		List<Voter> voters = query.getResultList();
		if(voters==null || voters.size()==0) {
			return 0;
		}else {
			return voters.get(0).getElection().getId();
		}
	}

	@Override
	public void saveVote(Vote vote) {
		Session session = sessionFactory.getCurrentSession();
		session.save(vote);
		
	}

	@Override
	public Voter getVoterByToken(String token) {
		Session session = sessionFactory.getCurrentSession();
		Query<Voter> query = session.createQuery("FROM Voter v where v.token =:token",Voter.class);
		query.setParameter("token", token);
		List<Voter> voters = query.getResultList();
		return voters.get(0);
	}

	@Override
	public String getElectionResultByElectionId(Integer electionId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select  " + 
				"t1.candidate_name, " + 
				"t1.candidate_email_id, " + 
				"t1.candidate_photo, " + 
				"t1.position_name, " + 
				"ifnull(t2.vote_count,0) as vote_count " + 
				"from  " + 
				"(  " + 
				"SELECT  " + 
				"c.id, " + 
				"c.candidate_name, " + 
				"c.candidate_email_id, " + 
				"c.candidate_photo, " + 
				"p.position_name " + 
				"FROM candidates c " + 
				"join positions p " + 
				"on p.id = c.position_id  " + 
				"where c.position_id in ( " + 
				"select position_id from votes where election_id= :electionId1)  " + 
				" ) as t1 " + 
				"LEFT JOIN " + 
				"(  " + 
				"select  " + 
				"v.candidate_id, " + 
				"count(v.candidate_id) as vote_count " + 
				"from  " + 
				"votes v " + 
				"where v.election_id=:electionId1 " + 
				"group by v.position_id,v.candidate_id  " + 
				") as t2 " + 
				"ON t1.id=t2.candidate_id " + 
				"order by t1.position_name,vote_count desc";
		SQLQuery  query = session.createSQLQuery(sql);
		query.setParameter("electionId1", electionId);
		//query.setParameter("electionId2", electionId);
		List<Object[]> results = (List<Object[]>) query.list();
		
		Map<String,Map> resultSet = new HashMap<>();
		for( Object[] result:results) {
			
				Map<String,String> row = new HashMap<>();
				String candidateName = (String) result[0];
				String candidateEmailId = (String) result[1];
				String candidatePhotoLocation = (String) result[2];
				String positionName = (String) result[3];
				String voteCount = ((BigInteger) result[4])+"";
				row.put("name", candidateName);
				row.put("email", candidateEmailId);
				row.put("photo", candidatePhotoLocation);
				row.put("vote", voteCount);
				if(resultSet.containsKey(positionName)) {
					Map<Integer,Map> thisRow = resultSet.get(positionName); 
					int id = thisRow.size();
					thisRow.put(id, row);
					resultSet.put(positionName, thisRow);
				}else {
					Map<Integer,Map> thisRow = new HashMap<>();
					thisRow.put(0, row);
					resultSet.put(positionName, thisRow);
				}
			
		}
		String json = "{}";
		try {
			 json = new ObjectMapper().writeValueAsString(resultSet);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}

	@Override
	public Integer getElectionIdByElectionToken(String token) {
		Session session = sessionFactory.getCurrentSession();
		Query<Election> query = session.createQuery("FROM Election e where e.electionToken =:token and e.electionStatus=:electionStatus",Election.class);
		query.setParameter("token", token);
		query.setParameter("electionStatus", 2);
		List<Election> elections = query.getResultList();
		if(elections==null || elections.size()==0) {
			return 0;
		}else {
			return elections.get(0).getId();
		}
	}



}
