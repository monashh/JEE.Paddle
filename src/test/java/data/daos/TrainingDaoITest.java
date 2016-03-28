package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Training;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class, TestsPersistenceConfig.class })
public class TrainingDaoITest {

	@Autowired
	private TrainingDao trainingDao;

	@Autowired
	private DaosService daosService;
	
	@Autowired
    private CourtDao courtDao;

	@Test
	public void testFindByTrainerName() {

		User trainer = (User) daosService.getMap().get("u9");
		assertEquals(trainer, trainingDao.findByTrainerName("u9"));

	}
	
	@Test
	public void testFindTrainerByCourt() {
		User trainer = (User) daosService.getMap().get("u9");
		assertEquals(trainer, trainingDao.findTrainerByCourt(courtDao.findOne(4)));

	}
	
//	@Test
//	public void testCreateTraining(){
//		List<Training> training = 
////		Calendar firstClassDate
//	}
	
//	@Test
//	public void testFindTrainingsByFirstClassDate(){
//		List<Training> training = 
////		Calendar firstClassDate
//	}
//	
	@Test
	public void testAddPlayerToTraining(){
		User player = (User) daosService.getMap().get("u2");
		User trainer = (User) daosService.getMap().get("u9");
		assertEquals(trainer, trainingDao.findTrainerByPlayer(player));
	}
	
	@Test
	public void testDeletePlayerFromTraining(){
		User deletedPlayer = (User) daosService.getMap().get("u3");
		assertNull(trainingDao.findTrainerByPlayer(deletedPlayer));
		User signedUpPlayer = (User) daosService.getMap().get("u2");
		assertNotNull(trainingDao.findTrainerByPlayer(signedUpPlayer));
		
	}
	
//	@Test
//	public void testDeleteTraining(){
//		List<Training> training = 
////		Calendar firstClassDate
//	}
	
}
