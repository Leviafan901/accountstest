package by.tut.accounttests.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import by.tut.accounttests.exceptions.ParsingException;

public class TestDataPropertiesReader {

	public Map<String, String> getPropValues(String propFileName) {
		HashMap<String, String> propertiesMap = new HashMap<>();
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);) {
			Properties properties = new Properties();
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
			}

			String loaderType = (properties.getProperty("dataloader"));
			propertiesMap.put("dataloader", loaderType);
			String filepath = properties.getProperty("filepath");
			propertiesMap.put("filepath", filepath);
		} catch (IOException e) {
			throw new ParsingException("Can't load properties  file and parse properties!", e);
		}
		return propertiesMap;
	}
}
