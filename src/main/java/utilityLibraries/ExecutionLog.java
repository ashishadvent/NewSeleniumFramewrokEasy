package utilityLibraries;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.model.Log;

public class ExecutionLog {
	//To create static Logger log object to collect log 
	private static Logger log=Logger.getLogger(Log.class.getName());
	
	// Need to create these methods, so that they can be called  
	 
	 public static void info(String message) {
	 
		 log.info(message);
	 
	 }
	 
	 public static void warn(String message) {
	 
		 log.warn(message);
	 
	 }
	 
	 public static void error(String message) {
	 
		 log.error(message);
	 
	 }
	 
	 public static void fatal(String message) {
	 
		 log.fatal(message);
	 
	 }
	 
	 public static void debug(String message) {
	 
		 log.debug(message);
	 
	 }
	 
	 

}
