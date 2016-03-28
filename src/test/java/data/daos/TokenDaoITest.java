package data.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Token;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TokenDaoITest {

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private DaosService daosService;

    @Test
    public void testFindByUser() {
        Token token = (Token) daosService.getMap().get("tu1");
        User user = (User) daosService.getMap().get("u4");
        assertEquals(token, tokenDao.findByUser(token.getUser()));
        assertNull(tokenDao.findByUser(user));
    }
    
    @Test
    public void testFindByUserAndValue() {
        Token token = (Token) daosService.getMap().get("tu1");
        assertEquals(token, tokenDao.findByUserAndValue(token.getUser(),token.getValue()));
    }
    
    @Test
    public void testDeleteExpiredTokens() {
    	int inicialTokenNumber = tokenDao.findAll().size();
    	assertEquals(inicialTokenNumber,9);
    	tokenDao.deleteExpiredTokens();
    	int ResultTokenNumber = tokenDao.findAll().size();
    	List<Token> resultTokens = tokenDao.findAll();
    	int expiredTokenNum = 0;
    	for (int i = 0; i < ResultTokenNumber; i++) {
    		if (resultTokens.get(i).isTokenExpired()) {
				expiredTokenNum++;
    		}
    	}
    	assertEquals(ResultTokenNumber,5);
    	assertFalse("",inicialTokenNumber < ResultTokenNumber);
    	assertTrue("",inicialTokenNumber >= ResultTokenNumber);
    	assertEquals(expiredTokenNum,0);
    }

}
