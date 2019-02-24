/**
 * 
 */
package utilityLibraries;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * @author ashish
 *
 */
public class ExtentTestManager {
	
	static Map<Integer,ExtentTest> extentTestMap=new HashMap<Integer,ExtentTest>();
	static ExtentReports extent=ExtentManager.createInstance();
	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int)(long)(Thread.currentThread().getId()));
		
	}
   
	public static synchronized void endTest() {
		extent.flush();
	}
	
	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest extenttest=extent.createTest(testName);
		extentTestMap.put((int)(long)Thread.currentThread().getId(), extenttest);
		return extenttest;
	}
	
	
}
