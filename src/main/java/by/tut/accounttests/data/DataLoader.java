package by.tut.accounttests.data;

import by.tut.accounttests.domain.Accounts;

public interface DataLoader {

	/***
	 * Method allows to load(parse) data from file into Account object
	 * 
	 * @param dataOrConfigFilePath
	 * @return Accounts
	 */
	public Accounts load(String dataOrConfigFilePath);
}
