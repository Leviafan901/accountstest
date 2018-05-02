package by.tut.accounttests.data;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.tut.accounttests.domain.Accounts;
import by.tut.accounttests.domain.UserAccount;
import by.tut.accounttests.parsers.xml.SaxParser;

public class MicroTest {

	 private List<UserAccount> accoutList = new ArrayList<>();
	
	private void loadData() {
    	Accounts accounts = SaxParser.parse("./src/test/resources/testaccounts.xml");
        accoutList.addAll(accounts.getAccountList());
    }
	
	public Object[][] accountsNew() {
        loadData();
    	int arraySize = (accoutList.size() / 2);
    	
    	Object[][] valuesArray = new Object[arraySize][2];
    	
    	for (int i = 0, p = 0; i <= arraySize; i++, p++) {
    		valuesArray[p][0] = accoutList.get(i);
    		valuesArray[p][1] = accoutList.get(++i);
    	}
    	
    	return valuesArray;
    }
	
	private Accounts expectedAccoutList = new Accounts();

    @BeforeClass
    public void setup() {
        UserAccount firstUser = new UserAccount("leha.sosenkov", "leha.sosenkov@tut.by", "223223223");
        expectedAccoutList.add(firstUser);
        UserAccount secondUser = new UserAccount("alexei.sosenkov", "alexei.sosenkov@tut.by", "223223223223");
        expectedAccoutList.add(secondUser);
    }
	
	@Test
	public void test() {
		
		Object[][] valuesArray = accountsNew();
		
		Assert.assertEquals(valuesArray.length, 1);
		
		List<UserAccount> accountList = expectedAccoutList.getAccountList();
		
		Assert.assertEquals(valuesArray[0][0], accountList.get(0));
		Assert.assertEquals(valuesArray[0][1], accountList.get(1));
	}
	
	
}
