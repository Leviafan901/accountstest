package by.tut.accounttests.data;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.tut.accounttests.domain.Accounts;
import by.tut.accounttests.domain.UserAccount;
import by.tut.accounttests.jdbc.UserAccountDao;

public class SqlDataLoader implements DataLoader {

	private final static String CONFIG_FILE = "/testproperties/jdbctemplate-useraccount-config.xml";
	
	public Accounts loadAccounts(String configFile) {
		ApplicationContext context = new ClassPathXmlApplicationContext(configFile);
        UserAccountDao userAccountDao = (UserAccountDao) context.getBean("jdbcTemplateUserAccountDao");
		List<UserAccount> accountList = userAccountDao.getAll();
		Accounts accounts = new Accounts();
		accounts.addAll(accountList);
		return accounts;
	}

	@Override
	public Accounts load(String dataOrConfigFilePath) {
		Accounts accounts = loadAccounts(CONFIG_FILE);
		return accounts;
	}
}
