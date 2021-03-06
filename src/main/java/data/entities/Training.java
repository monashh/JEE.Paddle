package data.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Training {

	private final int TOTAL_PLAYERS = 4;

	@Id
	@GeneratedValue
	private int id;

	private String trainerName;

	@ManyToOne
	@JoinColumn
	private User trainer;

	private Calendar firstClassDate;

	private Calendar lastClassDate;

	@ManyToOne
	@JoinColumn
	private Court court;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<User> players;

	public Training() {
	}

	public Training(User trainer, Calendar firstClassDate, Calendar lastClassDate, Court court) {
		this.trainerName = trainer.getUsername();
		this.trainer = trainer;
		this.firstClassDate = firstClassDate;
		this.lastClassDate = lastClassDate;
		this.court = court;
		this.players = new ArrayList<User>(TOTAL_PLAYERS);
	}

	public int getId() {
		return id;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(User trainer) {
		this.trainerName = trainer.getUsername();
	}
	
	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}

	public Calendar getFirstClassDate() {
		return firstClassDate;
	}

	public void setFirstClassDate(Calendar firstClassDate) {
		this.firstClassDate = firstClassDate;
	}

	public Calendar getLastClassDate() {
		return lastClassDate;
	}

	public void setLastClassDate(Calendar lastClassDate) {
		this.lastClassDate = lastClassDate;
	}

	public Court getCourt() {
		return court;
	}
	
	public void setCourt(Court court) {
		this.court = court;
	}

	public List<User> getPlayers() {
		return players;
	}

	public void addPlayer(User player) {
		this.players.add(player);
	}

	public void removePlayer(User player) {
		this.players.remove(player);
	}
	
	public int getSignedUpPlayersNum(){
		return this.players.size();
	}

	@Override
	public String toString() {
		return "Training [id=" + id + ", trainerName=" + trainerName + ", trainer=" + trainer + ", court=" + court
				+ ", players=" + players + ", firstClassDate=" + firstClassDate + ", lastClassDate=" + lastClassDate
				+ "]";
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			return id == ((Training) obj).id;
		}
	}

}
