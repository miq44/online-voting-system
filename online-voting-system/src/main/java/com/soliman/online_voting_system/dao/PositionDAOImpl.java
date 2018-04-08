package com.soliman.online_voting_system.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.soliman.online_voting_system.entity.Election;
import com.soliman.online_voting_system.entity.Position;


@Repository
public class PositionDAOImpl implements PositionDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Position> getAllPositions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePosition(Position position) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Position> getPositionByElectionId(int electionId) {
		Session session = sessionFactory.getCurrentSession();
		Election election = session.get(Election.class,electionId);
		return election.getPositions();
	}

}
