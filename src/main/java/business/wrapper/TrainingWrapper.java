package business.wrapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import data.entities.Training;

public class TrainingWrapper {

	private UserWrapper trainer;

	private List<UserWrapper> players;

	private Calendar firstClassDate;
	private Calendar lastClassDate;
	private CourtState court;

	public TrainingWrapper() {

	}

	public TrainingWrapper(Training training) {
		this.trainer = new UserWrapper(training.getTrainer().getUsername(), training.getTrainer().getEmail(),
				training.getTrainer().getPassword(), training.getTrainer().getBirthDate());
		this.firstClassDate = training.getFirstClassDate();
		this.lastClassDate = training.getLastClassDate();
		this.court = new CourtState(training.getCourt().getId(), training.getCourt().isActive());
	}

	public TrainingWrapper(UserWrapper trainer, Calendar firstClassDate, Calendar lastClassDate, CourtState court) {
		this.trainer = trainer;
		this.firstClassDate = firstClassDate;
		this.lastClassDate = lastClassDate;
		this.court = court;
		this.players = new ArrayList<UserWrapper>();
	}

	public UserWrapper getTrainer() {
		return trainer;
	}

	public void setTrainer(UserWrapper trainer) {
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

	public CourtState getCourt() {
		return court;
	}

	public void setCourt(CourtState court) {
		this.court = court;
	}
	
	public List<UserWrapper> getPlayers() {
        return players;
    }

    public void setPlayers(List<UserWrapper> players) {
        this.players = players;
    }

	@Override
	public String toString() {
		String firstClassTime = new SimpleDateFormat("dd-MMM-yyyy ").format(firstClassDate.getTime());
		String lastClassTime = new SimpleDateFormat("dd-MMM-yyyy ").format(lastClassDate.getTime());
		return "UserWrapper [trainer=" + trainer.getUsername() + ", firstClassTime=" + firstClassTime
				+ ", lastClassTime=" + lastClassTime + ", court=" + court.getCourtId() + ", players=" + players + "]";
	}

}
