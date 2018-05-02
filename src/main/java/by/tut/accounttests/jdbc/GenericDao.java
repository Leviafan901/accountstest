package by.tut.accounttests.jdbc;

import java.util.List;

import javax.sql.DataSource;

import by.tut.accounttests.domain.UserAccount;

public interface GenericDao {

	public void setDataSource(DataSource dataSource);
	
	/***
	 * Method allows to return List of UserAccount from BD
	 * 
	 * @return List<UserAccount>
	 */
	public List<UserAccount> getAll();
}
