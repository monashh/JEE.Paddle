package data.entities;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Training {
	
    @Id
    @GeneratedValue
    private int id;

    private String trainer;
    
    private Calendar firtsClassDate;
    
    private Calendar lastClassDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> userList;

    public Training() {
    }

    public Training(User trainer, List<User> userList, Calendar firtsClassDate, Calendar lastClassDate) {
        this.trainer = trainer.getUsername();
        this.userList = userList;
        this.firtsClassDate = firtsClassDate;
        this.lastClassDate = lastClassDate;
    }

    @Override
    public String toString() {
        return "Training [id=" + id + ", trainer=" + trainer + ", userList=" + userList + ", firtsClassDate=" + firtsClassDate + 
        		", lastClassDate=" + lastClassDate + "]";
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

    public String getTrainer() {
        return trainer;
    }

    public List<User> getUserList() {
        return userList;
    }

}
