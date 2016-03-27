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

	@Id
	@GeneratedValue
	private int id;

	private String trainerName;

	@ManyToOne
    @JoinColumn
    private User trainer;
	
	private Calendar firtsClassDate;

	private Calendar lastClassDate;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<User> players;

	public Training() {
	}

	public Training(User trainer, Calendar firtsClassDate, Calendar lastClassDate) {
		this.trainerName = trainer.getUsername();
		this.trainer = trainer;
		this.players = new ArrayList<User>(4);
		this.firtsClassDate = firtsClassDate;
		this.lastClassDate = lastClassDate;
	}

	@Override
	public String toString() {
		return "Training [id=" + id + ", trainer=" + trainerName + ", players=" + players + ", firtsClassDate="
				+ firtsClassDate + ", lastClassDate=" + lastClassDate + "]";
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

	public int getId() {
		return id;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(User trainer) {
		this.trainerName = trainer.getUsername();
	}

	public List<User> getPlayers() {
		return players;
	}

}
