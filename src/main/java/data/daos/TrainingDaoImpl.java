package data.daos;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.entities.Reserve;
import data.entities.Training;

@Repository
public class TrainingDaoImpl implements TrainingDaoExtended {

	@Autowired
	private TrainingDao trainingDao;

	@Autowired
	private ReserveDao reserveDao;

	@Autowired
	private UserDao userDao;

	@Override
	public void createTraining(Training training) {
		trainingDao.save(training);
	}

	@Override
	public void createTrainingAndReserves(Training training) {
		this.createTraining(training);
		Calendar reserveDate = training.getFirstClassDate();
		while (training.getLastClassDate().after(reserveDate)) {
			reserveDao.save(new Reserve(training.getCourt(), training.getTrainer(), reserveDate));
			reserveDate.add(Calendar.WEEK_OF_YEAR, 1);
		}

	}

	@Override
	public void deleteTrainingById(int id) {
		trainingDao.delete(trainingDao.findOne(id));
	}

	@Override
	public void deleteTrainingAndReserves(Training training) {
		Calendar reserveDate = training.getFirstClassDate();
		while (training.getLastClassDate().after(reserveDate)) {
			reserveDao.delete(reserveDao.findByCourtAndDate(training.getCourt(), reserveDate));
			reserveDate.add(Calendar.WEEK_OF_YEAR, 1);
		}
		this.deleteTrainingById(training.getId());
	}

	@Override
	public void deletePlayerFromTraining(int trainingId, int playerId) {
		trainingDao.findOne(trainingId).removePlayer(userDao.findOne(playerId));
	}

}
