package data.daos;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Court;
import data.entities.Training;
import data.entities.User;

public interface TrainingDao extends JpaRepository<Training, Integer>, TrainingDaoExtended {
	
	Training findById(int id);

	@Query("select training.trainer from Training training where training.trainerName = ?1")
	public User findByTrainerName(String trainerName);

	@Query("select training.trainer from Training training where training.court = ?1")
	public User findTrainerByCourt(Court court);

	@Query("select training.trainer from Training training where ?1 member of training.players")
	public User findTrainerByPlayer(User player);
	
	@Query("select training.id from Training training where ?1 member of training.players")
	public int findTraininigIdByPlayer(User player);
	
	@Query("select training.trainer from Training training where training.firstClassDate = ?1")
	public User findTrainerByFirstClassDate(Calendar firstClassDate);

}