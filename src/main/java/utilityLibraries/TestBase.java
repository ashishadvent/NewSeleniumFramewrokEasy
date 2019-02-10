/**
 * 
 */
package utilityLibraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * @author ashish
 *
 */
public class TestBase {

	
	private WebDriver driver;
	private static String chromeDriverPath= System.getProperty("user.dir")+"\\chromedriver.exe";
	private static String IEDriverPath= System.getProperty("user.dir")+"\\IEdriver.exe";
	private static String geckoDriverPath= System.getProperty("user.dir")+"\\geckodriver.exe";
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setDriver(String BrowserType,String AppURL) {
		
		switch(BrowserType.toLowerCase()) {
		
		case "chrome":
			 driver = initchromeDriver(AppURL);
			 break;	
		case "friefox":
			 driver = initfirefoxDriver(AppURL);
			 break;	
		case "IE":
			 driver = initIEDriver(AppURL);
			 break;	
		default :
			
			System.out.println("wrong Browser details passed");
			 break;	
			
		}
	}
		
	public static WebDriver initfirefoxDriver(String AppURL) {
		
		System.setProperty("webdriver.chrome.driver", geckoDriverPath);
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(AppURL);
		
		
		return driver ;
	}

	public static WebDriver initchromeDriver(String AppURL) {
		
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(AppURL);
		
		
		return driver;
	
	}
	
	public static WebDriver initIEDriver(String AppURL) {
		System.getProperty("webdriver.interexplorer.driver",IEDriverPath);
		WebDriver driver = new InternetExplorerDriver();
		driver.get(AppURL);
		return driver;
	}
	
	@Parameters({"BrowserType","AppURL"})
	
	@BeforeClass
	public void intializeTestBaseSetUp(String BrowserType,String AppURL) {
		try {
			setDriver(BrowserType, AppURL);
		}catch(Exception e) {
			System.out.println("Error..."+e.getStackTrace());
		}
	} 
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
}
