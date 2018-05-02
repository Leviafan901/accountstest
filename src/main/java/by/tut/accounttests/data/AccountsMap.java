package by.tut.accounttests.data;

import java.util.HashMap;
import java.util.Map;

/***
 * This class need's to store accounts data to use it in the Test Report Listener
 * 
 * @author Alexei Sosenkov
 *
 */
public class AccountsMap {

	private Map<String, String> accountsMap = new HashMap<>();
	
	public void add(String testMethodName, String accountEmailForMethod) {
		accountsMap.put(testMethodName, accountEmailForMethod);
	}
	
	public String getByTestMethodName(String testMethodName) {
		return accountsMap.get(testMethodName);
	}
}
