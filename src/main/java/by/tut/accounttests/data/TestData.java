package by.tut.accounttests.data;

import by.tut.accounttests.domain.Accounts;
import by.tut.accounttests.domain.UserAccount;
import by.tut.accounttests.util.TestDataPropertiesReader;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestData {

	private static final String FILEPATH_PROP = "filepath";
	private static final String DATALOADER_PROP = "dataloader";
	private final static String CONFIGE_FILE = "testdata-config.properties";
    private List<UserAccount> accoutList = new ArrayList<>();

    private void loadData() {
    	TestDataPropertiesReader configReader = new TestDataPropertiesReader();
    	Map<String, String> configMap = configReader.getPropValues(CONFIGE_FILE);
    	String dataLoaderType = configMap.get(DATALOADER_PROP);
    	DataLoader dataLoader = DataLoaderFactory.create(dataLoaderType);
    	String filePath = configMap.get(FILEPATH_PROP);
    	Accounts accounts = dataLoader.load(filePath);
    	accoutList.addAll(accounts.getAccountList());
    }
    
    @DataProvider(name = "accounts")
    public Object[][] accounts() {
    	loadData();
    	int arraySize = (accoutList.size() / 2);//get size of array cause it always would be twice smaller then getted listsize
    	Object[][] valuesArray = new Object[arraySize][2];// 2 mean that it would be a pair of the accounts
    	for (int i = 0, p = 0; p < arraySize; i++, p++) {
    		valuesArray[p][0] = accoutList.get(i);
    		valuesArray[p][1] = accoutList.get(++i);
    	}
    	return valuesArray;
    }
}
