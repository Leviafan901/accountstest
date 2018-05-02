package by.tut.accounttests.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.tut.accounttests.domain.UserAccount;

public class UserAccountDao implements GenericDao {

	private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<UserAccount> getAll() {
		String select_query = "SELECT login, email, password FROM Account";
		List<UserAccount> accountList = jdbcTemplate.query(select_query, new UserAccountMapper());		
		return accountList;
	}

	
}
