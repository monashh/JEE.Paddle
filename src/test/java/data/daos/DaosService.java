package data.daos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.entities.Authorization;
import data.entities.Court;
import data.entities.Reserve;
import data.entities.Role;
import data.entities.Token;
import data.entities.Training;
import data.entities.User;
import data.services.DataService;

@Service
public class DaosService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private TokenDao tokenDao;

	@Autowired
	private AuthorizationDao authorizationDao;

	@Autowired
	private CourtDao courtDao;

	@Autowired
	private ReserveDao reserveDao;

	@Autowired
	private TrainingDao trainingDao;

	@Autowired
	private DataService genericService;

	private Map<String, Object> map;

//	private Map<String, Object> mapa;

	@PostConstruct
	public void populate() {
		map = new HashMap<>();
		User[] users = this.createPlayersOrTrainers(0, 4, Role.PLAYER);
		for (User user : users) {
			map.put(user.getUsername(), user);
		}
		for (Token token : this.createTokens(users)) {
			map.put("t" + token.getUser().getUsername(), token);
		}
		for (User user : this.createPlayersOrTrainers(4, 4, Role.PLAYER)) {
			map.put(user.getUsername(), user);
		}
		this.createCourts(1, 4);
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_YEAR, 1);
		date.set(Calendar.HOUR_OF_DAY, 9);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		for (int i = 0; i < 4; i++) {
			date.add(Calendar.HOUR_OF_DAY, 1);
			reserveDao.save(new Reserve(courtDao.findOne(i + 1), users[i], date));
		}
		
		User[] trainers = this.createPlayersOrTrainers(9, 1, Role.TRAINER);
		this.createCourts(5, 1);
		Calendar firstClassDate = Calendar.getInstance();
		firstClassDate.add(Calendar.DAY_OF_YEAR, 1);
		firstClassDate.set(Calendar.HOUR_OF_DAY, 9);
		firstClassDate.set(Calendar.MINUTE, 0);
		firstClassDate.set(Calendar.SECOND, 0);
		firstClassDate.set(Calendar.MILLISECOND, 0);
		Calendar lastClassDate = (Calendar) firstClassDate.clone();
		lastClassDate.add(Calendar.WEEK_OF_YEAR, 4);
		for (User trainer : trainers) {
			map.put(trainer.getUsername(), trainer);
			for (Token token : this.createTokens(trainers)) {
				map.put("t" + token.getUser().getUsername(), token);
			}
			map.put(trainer.getUsername(), trainer);
			trainingDao.save(new Training(trainer, firstClassDate, lastClassDate, courtDao.findOne(4)));
		}
		
		users = this.createPlayersOrTrainers(10, 4, Role.PLAYER);
		for (User user : users) {
			map.put(user.getUsername(), user);
		}
		for (Token token : this.createTokens(users)) {
			Calendar currentTime = Calendar.getInstance();
	        currentTime.add(Calendar.MINUTE, -70);
	        token.setTokenCreationTime(currentTime.getTime());
	        tokenDao.save(token);
			map.put("t" + token.getUser().getUsername(), token);
		}
//		for (int i = 10; i < 14; i++) {
//			
//		}

	}

	public User[] createPlayersOrTrainers(int initial, int size, Role role) {
		User[] users = new User[size];
		for (int i = 0; i < size; i++) {
			users[i] = new User("u" + (i + initial), "u" + (i + initial) + "@gmail.com", "p", Calendar.getInstance());
			userDao.save(users[i]);
			authorizationDao.save(new Authorization(users[i], role));
		}
		return users;
	}

	public List<Token> createTokens(User[] users) {
		List<Token> tokenList = new ArrayList<>();
		Token token;
		for (User user : users) {
			token = new Token(user);
			tokenDao.save(token);
			tokenList.add(token);
		}
		return tokenList;
	}

	public void createCourts(int initial, int size) {
		for (int id = 0; id < size; id++) {
			courtDao.save(new Court(id + initial));
		}
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void deleteAll() {
		genericService.deleteAllExceptAdmin();
	}
}
