package by.tut.accounttests.parsers;

import by.tut.accounttests.domain.Accounts;
import by.tut.accounttests.domain.UserAccount;
import by.tut.accounttests.parsers.csv.CsvParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class CsvParserTest {

    private static final String RIGHT_CSV_PATH = "./src/test/resources/unittestsdata/testaccountscsv.csv";
    private Accounts expectedAccoutList = new Accounts();

    @BeforeClass
    public void setup() {
        UserAccount firstUser = new UserAccount("leha.sosenkov", "leha.sosenkov@tut.by", "223223223");
        expectedAccoutList.add(firstUser);
        UserAccount secondUser = new UserAccount("alexei.sosenkov", "alexei.sosenkov@tut.by", "223223223223");
        expectedAccoutList.add(secondUser);
    }

    @Test
    public void shouldParseCSVFileAndGetAccountsWhenGetRightFile() {
        //when
        Accounts actualAccounts = CsvParser.parse(RIGHT_CSV_PATH);
        List<UserAccount> actualAccountList = actualAccounts.getAccountList();
        List<UserAccount> expectedAccountList = expectedAccoutList.getAccountList();
        //then
        Assert.assertEquals(actualAccountList, expectedAccountList);
        Assert.assertEquals(actualAccounts, expectedAccoutList);
    }
}
