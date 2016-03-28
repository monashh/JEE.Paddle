package data.daos;

import java.util.Calendar;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Court;
import data.entities.Training;
import data.entities.User;

public interface TrainingDao extends JpaRepository<Training, Integer> {

	@Query("select training.trainer from Training training where training.trainerName = ?1")
	public User findByTrainerName(String trainerName);

	@Query("select training.trainer from Training training where training.court = ?1")
	public User findTrainerByCourt(Court court);

	@Query("select training from Training training where training.firstClassDate = ?1")
	public List<Training> findTrainingsByFirstClassDate(Calendar firstClassDate);
	
	@Query("select training.trainer from Training training where ?1 member of training.players")
	public User findTrainerByPlayer(User player);
	
	@Query("select training.players from Training training where ?1 member of training.players")
	public List<User> findTrainingPlayers(User player);

}