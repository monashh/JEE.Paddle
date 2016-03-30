package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class, TestsPersistenceConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrainingDaoITest {

	@Autowired
	private TrainingDao trainingDao;

	@Autowired
	private DaosService daosService;

	@Autowired
	private CourtDao courtDao;

	@Test
	public void testAfindByTrainerName() {

		User trainer = (User) daosService.getMap().get("u9");
		assertEquals(trainer, trainingDao.findByTrainerName("u9"));

	}

	@Test
	public void testBfindTrainerByCourt() {
		User trainer = (User) daosService.getMap().get("u9");
		assertEquals(trainer, trainingDao.findTrainerByCourt(courtDao.findOne(4)));

	}

	@Test
	public void testCaddPlayerToTraining() {
		User player = (User) daosService.getMap().get("u2");
		User trainer = (User) daosService.getMap().get("u9");
		assertEquals(trainer, trainingDao.findTrainerByPlayer(player));
	}

	@Test
	public void testDremovePlayerFromTraining() {
		User deletedPlayer = (User) daosService.getMap().get("u3");
		assertNull(trainingDao.findTrainerByPlayer(deletedPlayer));
		User signedUpPlayer = (User) daosService.getMap().get("u2");
		assertNotNull(trainingDao.findTrainerByPlayer(signedUpPlayer));

	}

	@Test
	public void testEfindTrainerByFirstClassDate() {
		User trainer = trainingDao.findByTrainerName("u9");
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_YEAR, 1);
		date.set(Calendar.HOUR_OF_DAY, 9);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		assertEquals(trainer, trainingDao.findTrainerByFirstClassDate(date));
	}
	
	@Test
	public void testZdeleteTraining() {
		assertNotNull(trainingDao.findOne(1));
		trainingDao.deleteTrainingById(1);
		assertNull(trainingDao.findOne(1));
	}

}
