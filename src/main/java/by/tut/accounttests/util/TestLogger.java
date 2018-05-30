package by.tut.accounttests.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class TestLogger {

	private final static Logger LOGGER = Logger.getLogger(TestLogger.class);
	
	public static Logger getLogger(Class classForLogin) {
		Logger logger = Logger.getLogger(classForLogin);
		SimpleLayout layout = new SimpleLayout();    
	    FileAppender appender = null;
	    String filePath = "test-output/reports-logs/" + classForLogin + "_"
                + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()) + ".log";
		try {
			appender = new FileAppender(layout, filePath, false);
		} catch (IOException e) {
			LOGGER.error("Can't inizialize test logger!", e);
		}    
	      logger.addAppender(appender);

	      logger.setLevel((Level) Level.INFO);
	      return logger;
	}
}
