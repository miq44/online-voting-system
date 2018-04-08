package com.soliman.online_voting_system.dao;

import java.util.List;

import com.soliman.online_voting_system.entity.Position;



public interface PositionDAO {



		public List<Position> getAllPositions();
		
		public void savePosition(Position position);
		
		public  List<Position> getPositionByElectionId(int electionId);


}
