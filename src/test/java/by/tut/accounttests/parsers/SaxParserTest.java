package by.tut.accounttests.parsers;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.tut.accounttests.exceptions.ParsingException;
import by.tut.accounttests.domain.Accounts;
import by.tut.accounttests.domain.UserAccount;
import by.tut.accounttests.parsers.xml.SaxParser;

public class SaxParserTest {

	private static final String RIGHT_XML_PATH = "./src/test/resources/unittestsdata/testaccountsxml.xml";
	private Accounts expectedAccoutList = new Accounts();
	
	@BeforeClass
	public void setup() {
		UserAccount firstUser = new UserAccount("leha.sosenkov", "leha.sosenkov@tut.by", "223223223");
		expectedAccoutList.add(firstUser);
		UserAccount secondUser = new UserAccount("alexei.sosenkov", "alexei.sosenkov@tut.by", "223223223223");
		expectedAccoutList.add(secondUser);
	}
	
	@Test
	public void shouldParseXMLFileAndGetAccountsWhenGetRightFile() 
    		throws ParsingException {
		//when
		Accounts actualAccounts = SaxParser.parse(RIGHT_XML_PATH);
		List<UserAccount> actualAccountList = actualAccounts.getAccountList();
		List<UserAccount> expectedAccountList = expectedAccoutList.getAccountList();
		//then
		Assert.assertEquals(actualAccountList, expectedAccountList);
		Assert.assertEquals(actualAccounts, expectedAccoutList);
	}
}
