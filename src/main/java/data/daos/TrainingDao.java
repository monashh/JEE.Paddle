package data.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Training;
import data.entities.User;

public interface TrainingDao extends JpaRepository<Training, Integer> {

	@Query("select training.trainer from Training training where training.trainerName = ?1")
	public User findByTrainerName(String trainerName);

}