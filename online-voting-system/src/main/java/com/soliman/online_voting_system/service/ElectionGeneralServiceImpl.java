package com.soliman.online_voting_system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.soliman.online_voting_system.dao.ElectionGeneralDAO;
import com.soliman.online_voting_system.dao.PositionDAO;
import com.soliman.online_voting_system.entity.Candidate;
import com.soliman.online_voting_system.entity.Election;
import com.soliman.online_voting_system.entity.Position;
import com.soliman.online_voting_system.entity.User;
import com.soliman.online_voting_system.entity.Vote;
import com.soliman.online_voting_system.entity.Voter;
import com.soliman.online_voting_system.model.CandidateDetails;
import com.soliman.online_voting_system.model.CandidateInfo;
import com.soliman.online_voting_system.model.ElectionModel;
import com.soliman.online_voting_system.model.PositionListModel;
import com.soliman.online_voting_system.model.PositionModel;
import com.soliman.online_voting_system.model.SingleVote;
import com.soliman.online_voting_system.model.VoteModel;
import com.soliman.online_voting_system.model.VoterInfo;

@Service
public class ElectionGeneralServiceImpl implements ElectionGeneralService {

	@Autowired
	private UserService userService;

	@Autowired
	private ElectionGeneralDAO electionGeneralDao;

	@Autowired
	private PositionDAO positionDao;

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private ApplicationUtilityService applicationService;

	@Override
	@Transactional
	public void saveELection(PositionListModel positionListModel, CandidateDetails candidateDetailsModel) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userService.getUserByUserId(userName);
		Election election = new Election(positionListModel.getElectionName(), positionListModel.getNumOfPosition(),
				user);
		election.setNumOfVoter(0);
		election.setElectionStatus(0);
		for (PositionModel positionModel : positionListModel.getPositions()) {
			Position position = new Position(positionModel.getPositionName(), positionModel.getNoOfCandidate());
			int positionId = positionModel.getPositionId();
			List<CandidateInfo> candidates = candidateDetailsModel.getCandidatesByPositionId(positionId);

			for (CandidateInfo candidate : candidates) {
				Candidate candidateEntity = new Candidate(candidate.getCandidateName(), candidate.getCandidateEmail(),
						candidate.getCandidatePhoto());
				position.addCandidate(candidateEntity);
			}

			election.addPosition(position);
		}

		electionGeneralDao.saveELection(election);

	}

	@Override
	@Transactional
	public List<ElectionModel> getMyELections(String userName) {
		// TODO Auto-generated method stub
		User user = userService.getUserByUserId(userName);
		List<ElectionModel> elections = new ArrayList<>();

		for (Election election : user.getElections()) {
			ElectionModel model = new ElectionModel();
			model.setElectionId(election.getId());
			model.setElectionName(election.getElectionName());
			model.setNumOfPosition(election.getNumOfPosition());
			model.setNumOfVoter(election.getNumOfVoter());
			model.setElectionStatus(election.getElectionStatus());
			model.setElectionToken(election.getElectionToken());
			elections.add(model);
		}

		return elections;

	}

	@Override
	@Transactional
	public Map<String, List<CandidateInfo>> getCandidateMapByElectionId(Integer electionId) {
		List<Position> positions = positionDao.getPositionByElectionId(electionId);

		Map<String, List<CandidateInfo>> candidateMap = new HashMap<>();
		for (Position position : positions) {
			String positionName = position.getPositionName();
			List<CandidateInfo> candidates = new ArrayList<>();
			for (Candidate candidate : position.getCandidates()) {
				CandidateInfo thisCandidate = new CandidateInfo();
				thisCandidate.setCandidateEmail(candidate.getCandidateEmailId());
				thisCandidate.setCandidateName(candidate.getCandidateName());
				thisCandidate.setCandidatePhoto(candidate.getCandidatePhotoLocation());
				thisCandidate.setCandidateId(candidate.getId());
				thisCandidate.setPositionId(position.getId());
				candidates.add(thisCandidate);
			}
			candidateMap.put(positionName, candidates);
		}
		return candidateMap;
	}

	@Override
	@Transactional
	public void beginElection(Integer electionId, String baseUrl) {
		// TODO Auto-generated method stub
		Election election = electionGeneralDao.getElectionById(electionId);
		election.setElectionStatus(1);
		for (Voter voter : election.getVoters()) {
			String voterEmailId = voter.getEmailId();
			String token = voter.getToken();
			sendEmail(voterEmailId, token, baseUrl);
		}

		electionGeneralDao.saveELection(election);
	}

	private void sendEmail(String emailId, String token, String baseUrl) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailId);
		message.setSubject("Cast your vote visiting following URL");
		String text = "Welcome to the Online Election System. Visit the following url to cast your vote.\n" + baseUrl
				+ "/my-vote/" + token;
		message.setText(text);
		emailSender.send(message);
	}

	@Async
	private void sendResult(String voterEmailId, String token, String baseUrl) throws InterruptedException {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(voterEmailId);
		message.setSubject("View the election result visiting following URL");
		String text = "Welcome to the Online Election System. Visit the following url to view election result.\n"
				+ baseUrl + "/my-vote-result/" + token;
		message.setText(text);
		emailSender.send(message);
	}

	@Override
	@Transactional
	public Integer getElectionIdByToken(String token) {
		Integer electionId = electionGeneralDao.getElectionIdByToken(token);
		return electionId;
	}

	@Override
	@Transactional
	public String getElectionResultByElectionId(Integer electionId) {
		String jsonResponse = electionGeneralDao.getElectionResultByElectionId(electionId);
		return jsonResponse;
	}

	@Override
	@Transactional
	public void endElection(Integer electionId, String baseUrl) {

		Election election = electionGeneralDao.getElectionById(electionId);
		election.setElectionStatus(2);
		String token = applicationService.createSalt() + applicationService.createSalt();
		election.setElectionToken(token);
		for (Voter voter : election.getVoters()) {
			String voterEmailId = voter.getEmailId();
			try {
				sendResult(voterEmailId, token, baseUrl);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		electionGeneralDao.saveELection(election);
	}

	@Override
	@Transactional
	public Integer getElectionIdByElectionToken(String token) {
		Integer electionId = electionGeneralDao.getElectionIdByElectionToken(token);
		return electionId;
	}

	@Override
	@Transactional
	public String getElectionNameById(Integer electionId) {
		Election election = electionGeneralDao.getElectionById(electionId);
		return election.getElectionName();
	}

}
