package data.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.entities.Token;

@Repository
public class TokenDaoImpl implements TokenDaoExtended{

	@Autowired
	private TokenDao tokenDao;
	
	@Override
	public void deleteExpiredTokens(){
		List<Token> tokens = tokenDao.findAll();
		
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i).isTokenExpired()){
				tokenDao.delete(tokens.get(i));
			}
		}
		
	}
}
