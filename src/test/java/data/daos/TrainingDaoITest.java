package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
	
	
//	@Test
//	public void testFindTrainingsByFirstClassDate(){
//		List<Training> training = 
////		Calendar firstClassDate
//	}
//	
	@Test
	public void testCaddPlayerToTraining(){
		User player = (User) daosService.getMap().get("u2");
		User trainer = (User) daosService.getMap().get("u9");
		assertEquals(trainer, trainingDao.findTrainerByPlayer(player));
	}
	
	@Test
	public void testDremovePlayerFromTraining(){
		User deletedPlayer = (User) daosService.getMap().get("u3");
		assertNull(trainingDao.findTrainerByPlayer(deletedPlayer));
		User signedUpPlayer = (User) daosService.getMap().get("u2");
		assertNotNull(trainingDao.findTrainerByPlayer(signedUpPlayer));
		
	}
	
	@Test
	public void testEdeleteTraining(){
		assertNotNull(trainingDao.findOne(1));
		trainingDao.deleteTrainingById(1);
		assertNull(trainingDao.findOne(1));
	}
	
}
