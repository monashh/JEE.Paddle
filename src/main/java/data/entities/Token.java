package data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Token {

	private final int EXPIRATION_TIME = 3600000;
	
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn
    private User user;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenCreationTime;

    public Token() {
    }

    public Token(User user) {
        assert user != null;
        this.user = user;
        this.value = new Encrypt().encryptInBase64UrlSafe("" + user.getId() + user.getUsername() + Long.toString(new Date().getTime())
                + user.getPassword());
        this.tokenCreationTime = new Date();
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public User getUser() {
        return user;
    }
    
    public Date getTokenCreationTime() {
        return tokenCreationTime;
    }
    
    public void setTokenCreationTime(Date tokenCreationTime) {
        this.tokenCreationTime = tokenCreationTime;
    }
    
    public boolean isTokenExpired(){
    	long currentTime = System.currentTimeMillis(); 
    	return ((currentTime - tokenCreationTime.getTime()) > EXPIRATION_TIME); 
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return id == ((Token) obj).id;
    }

    @Override
    public String toString() {
        return "Token [id=" + id + ", value=" + value + ", userId=" + user.getId() + "]";
    }
}
