package data.daos;

import static org.junit.Assert.assertEquals;

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
}
