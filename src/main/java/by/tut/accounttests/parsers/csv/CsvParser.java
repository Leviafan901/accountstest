package by.tut.accounttests.parsers.csv;

import by.tut.accounttests.domain.Accounts;
import by.tut.accounttests.domain.UserAccount;
import by.tut.accounttests.exceptions.ParsingException;

import com.opencsv.CSVReader;

import java.io.*;

public class CsvParser {

    private static final int LOGIN = 0;
    private static final int EMAIL = 1;
    private static final int PASSWORD = 2;

    public static Accounts parse(String pathToCsvFile) {
    	boolean isEmptyPath = pathToCsvFile.isEmpty();
		if (isEmptyPath) {
			throw new IllegalArgumentException("Incorrect path to XML file!");
		}
    	
    	Accounts accounts = new Accounts();
        try (BufferedReader bufferedStream = new BufferedReader(new FileReader(pathToCsvFile));
                CSVReader reader = new CSVReader(bufferedStream)) {
            String[] line = reader.readNext();
            while (line != null) {
                UserAccount newAccount = new UserAccount(line[LOGIN], line[EMAIL], line[PASSWORD]);
                accounts.add(newAccount);
                line = reader.readNext();
            }
        } catch (IOException e) {
        	throw new ParsingException("Parsing failed!", e);
        }
        return accounts;
    }
}
