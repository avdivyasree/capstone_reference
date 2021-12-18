package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLog {

	
    // Constructor
	//MyLog () {
	//	PropertyConfigurator.configure("log4j.properties");
//	}
	
	// Initialize Log4j instance
	private static final Logger MyLogger = LogManager.getLogger(MyLog.class.getSimpleName());
	
	
    public static void info (String message) {
    	MyLogger.info(message);
    }
    //Warn Level Logs
    public static void warn (String message) {
    	MyLogger.warn(message);
    }
    
    //Error Level Logs
    public static void error (String message) {
    	MyLogger.error(message);
    }
    //Fatal Level Logs
    public static void fatal (String message) {
    	MyLogger.fatal(message);
    }
    //Debug Level Logs
    public static void debug (String message) {
    	MyLogger.debug(message);
    }	
    
    
}
