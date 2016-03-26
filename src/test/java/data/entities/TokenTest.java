package data.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import data.entities.Token;
import data.entities.User;

public class TokenTest {

    @Test
    public void testTokenUser() {
        User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        assertTrue(token.getValue().length() > 20);
    }
    
    @Test
    public void testTokenExpired() {
        User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        
        Calendar currentTime = Calendar.getInstance();
        currentTime.add(Calendar.MINUTE, -70);
        token.setTokenCreationTime(currentTime.getTime());
        assertTrue(token.isTokenExpired());
    }
    
    @Test
    public void testTokenNotExpired() {
        User user = new User("u", "u@gmail.com", "p", Calendar.getInstance());
        Token token = new Token(user);
        
        Calendar currentTime = Calendar.getInstance();
        currentTime.add(Calendar.MINUTE, -30);
        token.setTokenCreationTime(currentTime.getTime());
        assertFalse(token.isTokenExpired());
    }

}
