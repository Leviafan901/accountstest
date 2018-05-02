package by.tut.accounttests.data;

import by.tut.accounttests.domain.Accounts;
import by.tut.accounttests.parsers.xml.SaxParser;

public class XmlDataLoader implements DataLoader {

	public Accounts load(String dataOrConfigFilePath) {
		Accounts accounts = SaxParser.parse(dataOrConfigFilePath);
		return accounts;
	}
}