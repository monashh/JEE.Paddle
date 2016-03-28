package data.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Token;
import data.entities.User;

public interface TokenDao extends JpaRepository<Token, Integer>, TokenDaoExtended {

	Token findByUser(User user);

	@Query("select token from Token token where token.user = ?1 and token.value = ?2")
	public Token findByUserAndValue(User user, String value);

}
