package business.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;

import business.wrapper.TrainingWrapper;
import data.daos.CourtDao;
import data.daos.TrainingDao;
import data.daos.UserDao;
import data.entities.Court;
import data.entities.Training;
import data.entities.User;

@Controller
public class TrainingController {

	private TrainingDao trainingDao;
	
	private UserDao userDao;
	
	private CourtDao courtDao;

	public List<TrainingWrapper> showTrainings(Calendar baseDay) {
		List<TrainingWrapper> trainingList = new ArrayList<>();
		for (Training training : trainingDao.findAll()) {
			if ((training.getFirstClassDate().after(baseDay)) && (training.getSignedUpPlayersNum() < 4)) {
				trainingList.add(new TrainingWrapper(training));
			}
		}
		return trainingList;
	}

	public boolean createTraining(TrainingWrapper trainingWrapper) {
		User trainer = userDao.findByUsernameOrEmail(trainingWrapper.getTrainer().toString());
		if (trainingDao.findTrainerByFirstClassDate(trainingWrapper.getFirstClassDate()) != trainer) {
			Court court = courtDao.findOne(trainingWrapper.getCourt().getCourtId());
			Training training = new Training(trainer,trainingWrapper.getFirstClassDate(), trainingWrapper.getLastClassDate(), court);
			trainingDao.createTrainingAndReserves(training);
			return true;
		} else {
			return false;
		}
	}

	public boolean exist(int trainingId) {
		return trainingDao.findById(trainingId) != null;
	}

	public boolean deleteTraining(int trainingId) {
		if (trainingDao.findById(trainingId) != null) {
			trainingDao.deleteTrainingAndReserves(trainingDao.findById(trainingId));
			return true;
		} else {
			return false;
		}
	}

	public boolean deletePlayerFromTraining(int trainingId, int playerId) {
		if (this.existPlayerInTraining(trainingId, playerId)) {
			trainingDao.deletePlayerFromTraining(trainingId, playerId);
			return true;
		} else {
			return false;
		}
	}

	public boolean existPlayerInTraining(int trainingId, int playerId) {
		for (User player : trainingDao.findOne(trainingId).getPlayers()) {
			if (player.getId() == playerId) {
				return true;
			}
		}
		return false;
	}

	public int getSignedUpPlayersNum(int trainingId) {
		return trainingDao.findOne(trainingId).getSignedUpPlayersNum();
	}

	public boolean registerTraining(int trainingId, String playerName) {
		if (this.getSignedUpPlayersNum(trainingId) < 4) {
			trainingDao.findOne(trainingId).addPlayer(userDao.findByUsernameOrEmail(playerName));
			return true;
		} else {
			return false;
		}
	}

}