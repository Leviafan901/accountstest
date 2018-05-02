package by.tut.accounttests.data;

import by.tut.accounttests.domain.Accounts;
import by.tut.accounttests.parsers.csv.CsvParser;

public class CsvDataLoader implements DataLoader {

	@Override
	public Accounts load(String dataOrConfigFilePath) {
		Accounts accounts = CsvParser.parse(dataOrConfigFilePath);
		return accounts;
	}
}
