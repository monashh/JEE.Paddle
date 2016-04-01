package data.daos;

import data.entities.Training;

public interface TrainingDaoExtended {
	public void deleteTrainingById(int id);
	
	public void deleteTrainingAndReserves(Training training);

	public void createTraining(Training training);
	
	public void createTrainingAndReserves(Training training);
	
	public void deletePlayerFromTraining(int trainingId, int playerId);

}
