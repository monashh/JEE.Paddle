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
	
	@ManyToOne
    @JoinColumn
    private Court court;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<User> players;

	public Training() {
	}

	public Training(User trainer, Calendar firtsClassDate, Calendar lastClassDate, Court court) {
		this.trainerName = trainer.getUsername();
		this.trainer = trainer;
		this.firtsClassDate = firtsClassDate;
		this.lastClassDate = lastClassDate;
		this.court = court;
		this.players = new ArrayList<User>(4);
	}

	@Override
	public String toString() {
		return "Training [id=" + id + ", trainerName=" + trainerName + ", trainer=" + trainer + ", court=" + court + 
				", players=" + players + ", firtsClassDate=" + firtsClassDate + ", lastClassDate=" + lastClassDate + "]";
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
	
	public Court getCourt() {
        return court;
    }

	public List<User> getPlayers() {
		return players;
	}

}
