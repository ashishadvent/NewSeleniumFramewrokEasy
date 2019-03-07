/**
 * 
 */
package utilityLibraries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;

import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;




/**
 * @author ashish 
 *
 */

// To add webDriver Listeners and Test NG Listeners
// webdriver listeners will be  triggered when any webdriver related operation will be executed like 'on Click' ,sendKeys,etc
// TestNG Listeners will be triggered when TestNG realted like OnFinish,OnStart and OnTestFailed, etc.

public class TestListener extends AbstractWebDriverEventListener implements ITestListener{
	
	
	
	
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to: '" + url + "'");
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("Navigated to:'" + url + "'");
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("Value of the:" + element.toString()
				+ " before any changes made");
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("Element value changed to: " + element.toString());
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Trying to click on: " + element.toString());
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("Clicked on: " + element.toString());
	}

	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("Navigating back to previous page");
	}

	public void afterNavigateBack(WebDriver driver) {
		System.out.println("Navigated back to previous page");
	}

	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("Navigating forward to next page");
	}

	public void afterNavigateForward(WebDriver driver) {
		System.out.println("Navigated forward to next page");
	}

	public void onException(Throwable error, WebDriver driver) {
		System.out.println("Exception occured: " + error);
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Trying to find Element By : " + by.toString());
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Found Element By : " + by.toString());
	}

	/*
	 * non overridden methods of WebListener class
	 */
	public void beforeScript(String script, WebDriver driver) {
	}

	public void afterScript(String script, WebDriver driver) {
	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		System.out.println(("*** Test Suite " + arg0.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		System.out.println("*** Test Suite " + arg0.getName() + " started ***");
		 
		 
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println("*** Test Falied but within percentage % " + arg0.getMethod().getMethodName());
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println("*** Test Execution failed  "+ arg0.getMethod().getMethodName()+ "now taking screenshot");
		ITestContext context= arg0.getTestContext();
		WebDriver driver=(WebDriver) context.getAttribute("driver");
		
		String targetLocation=null;
		String  testClassName= arg0.getInstanceName().toString().trim();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String testMethodName= arg0.getName().toString().trim();
		String screenShotName= testMethodName+timeStamp +".png";
		String fileSeparator=System.getProperty("file.separator");
		String reportPath=System.getProperty("user.dir")+fileSeparator +"TestReport"+fileSeparator+"ScreenShots";
		System.out.println("ScreenShot Report Path =" +reportPath );
		
		try {
			File file=new File(reportPath+fileSeparator+testClassName);
			if (!file.exists()) {
				if(file.mkdirs()) {
					System.out.println("Directory "+file.getAbsolutePath()+"crated successfully");
				}
				else {
					System.out.println("Error occurs while creating Directory "+file.getAbsolutePath());
				}
			  	
			}
			File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			targetLocation= reportPath+fileSeparator+testClassName+fileSeparator+screenShotName;
			
			File targetFile=new File(targetLocation);
			
			System.out.println("Screenshot location"+screenshotFile.getAbsolutePath() );
			System.out.println("Saved screenshot location "+ file.getAbsolutePath());
			FileUtils.copyFile(screenshotFile,targetFile);
			
			
			//locate the location
			
			
			
		}catch(FileNotFoundException e) {
			System.out.println("File not found Exception occurs while taking screenshot"+e.getMessage());
						
		}
		catch(Exception e) {
			System.out.println("Exception occurs while taking screenshot"+e.getMessage());
			
		}
		//attach screenshot to report
		
		try {
			ExtentTestManager.getTest().fail("Screenshot",MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());
			
		}
		catch(IOException e){
			System.out.println("An Exception occurs while taking screenshot"+e.getCause());
			ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
			
		}
		
		
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println("*** Test -" + arg0.getMethod().getMethodName() +"Skipped ");
		ExtentTestManager.getTest().log(Status.SKIP, "test Skipped");
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println(("*** Running test method " + arg0.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(arg0.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println("*** Executed Test " + arg0.getMethod().getMethodName() + "Successfully");
		ExtentTestManager.getTest().log(Status.PASS, "Test Passed ");
			
	}
	
	
	
	

}
