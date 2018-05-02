package by.tut.accounttests.data;

import by.tut.accounttests.data.SqlDataLoader;
import by.tut.accounttests.domain.Accounts;
import by.tut.accounttests.domain.UserAccount;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SqlDataLoaderTest {

    private Accounts expectedAccouts = new Accounts();
    private DataLoader dataLoader;

    @BeforeClass
    public void setup() {
    	dataLoader = new SqlDataLoader();
        UserAccount firstUser = new UserAccount("leha.sosenkov", "leha.sosenkov@tut.by", "223223223");
        expectedAccouts.add(firstUser);
        UserAccount secondUser = new UserAccount("alexei.sosenkov", "alexei.sosenkov@tut.by", "223223223223");
        expectedAccouts.add(secondUser);
    }

    @Test
    public void shouldConnectToDataBaseAndGetAccounts() {
    	//when
        Accounts actualAccounts = dataLoader.load("someFile");
        List<UserAccount> actualAccountList = actualAccounts.getAccountList();
        List<UserAccount> expectedAccountList = expectedAccouts.getAccountList();
        //then
        Assert.assertEquals(actualAccountList.get(0), expectedAccountList.get(0));
        Assert.assertEquals(actualAccountList.get(1), expectedAccountList.get(1));
    }
}