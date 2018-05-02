package by.tut.accounttests.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.tut.accounttests.domain.UserAccount;

public class UserAccountMapper implements RowMapper {

	@Override
	public UserAccount mapRow(ResultSet resultSet, int rowCount) throws SQLException {
		UserAccount newAccount = new UserAccount();
		newAccount.setEmail(resultSet.getString("email"));
		newAccount.setPassword(resultSet.getString("password"));
		newAccount.setLogin(resultSet.getString("login"));;
		return newAccount;
	}
}
