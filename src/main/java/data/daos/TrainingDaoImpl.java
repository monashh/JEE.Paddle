package data.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingDaoImpl implements TrainingDaoExtended {

	@Autowired
	private TrainingDao trainingDao;

	@Override
	public void deleteTrainingById(int id) {
		trainingDao.delete(trainingDao.findOne(id));
	}

}
