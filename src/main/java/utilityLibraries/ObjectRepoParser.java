/**
 * 
 */
package utilityLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

/**
 * @author ashish
 *
 */
public class ObjectRepoParser {
	private FileInputStream inputStream;
	private Properties propObj=new Properties();
	private String propFilepath;
	
	public ObjectRepoParser(String path) throws IOException {
	   this.propFilepath=path;
	   try {
		inputStream=new FileInputStream(propFilepath);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   propObj.load(inputStream);
	}

	public By getObjectLocator(String locatorName) {
		
		String locParse=propObj.getProperty(locatorName);
		String locType=locParse.split(":")[0];
		String locValue=locParse.split(":")[1];
		
		By locatorObj=null;
		
		switch(locType){
			case "id" : 
				locatorObj=By.id(locValue);
				break;
			case "name" : 
				locatorObj=By.id(locValue);
				break;
			case "xpath" : 
				locatorObj=By.id(locValue);
				break;
			case "CssSelector" : 
				locatorObj=By.id(locValue);
				break;
			case "LinkText" : 
				locatorObj=By.id(locValue);
				break;
			case "TagName" : 
				locatorObj=By.id(locValue);
				break;
		}
		  		
		  return locatorObj;
		}
		
}
