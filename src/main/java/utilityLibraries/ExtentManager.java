/**
 * 
 */
package utilityLibraries;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author ashish
 *
 */
public class ExtentManager {
	private static ExtentReports extentreport;
	private static String reportFileName="Test-Automation-report"+".html";
	private static String fileSeparator=System.getProperty("file.separator");
	private static String fileLocation=System.getProperty("user.dir")+fileSeparator+"TestReport";
	private static String FileCompletePath=fileLocation+fileSeparator+reportFileName;
	
	public ExtentReports getInstance() {
		if(extentreport==null)
			createInstance();
		return extentreport;
	}

	public static ExtentReports createInstance() {
		// TODO Auto-generated method stub
		String fileName=getReportPath(fileLocation);
		
		ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(fileName);
		
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
       // htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        
        
		extentreport=new ExtentReports();
		extentreport.attachReporter(htmlReporter);
		
		    extentreport.setSystemInfo("OS","Window");
		    extentreport.setSystemInfo("AUT","QA");
		
		return extentreport;
	}

	private static String getReportPath(String fileLocation2) {
		// TODO Auto-generated method stub
		return null;
	}

}
