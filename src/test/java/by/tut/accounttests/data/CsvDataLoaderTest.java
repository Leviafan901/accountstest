package by.tut.accounttests.data;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.tut.accounttests.domain.Accounts;
import by.tut.accounttests.domain.UserAccount;

public class CsvDataLoaderTest {

	private static final String RIGHT_CSV_PATH = "./src/test/resources/unittestsdata/testaccountscsv.csv";
    private Accounts expectedAccoutList = new Accounts();
    private DataLoader dataLoader;

    @BeforeClass
    public void setup() {
    	dataLoader = new CsvDataLoader();
        UserAccount firstUser = new UserAccount("leha.sosenkov", "leha.sosenkov@tut.by", "223223223");
        expectedAccoutList.add(firstUser);
        UserAccount secondUser = new UserAccount("alexei.sosenkov", "alexei.sosenkov@tut.by", "223223223223");
        expectedAccoutList.add(secondUser);
    }

    @Test
    public void shouldParseCSVFileAndGetAccountsWhenGetRightFile() {
        //when
        Accounts actualAccounts = dataLoader.load(RIGHT_CSV_PATH);
        List<UserAccount> actualAccountList = actualAccounts.getAccountList();
        List<UserAccount> expectedAccountList = expectedAccoutList.getAccountList();
        //then
        Assert.assertEquals(actualAccountList, expectedAccountList);
        Assert.assertEquals(actualAccounts, expectedAccoutList);
    }
}
