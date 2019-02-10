/**
 * 
 */
package utilityLibraries;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * @author ashish
 *
 */
public class ExcelLibrary {
	static Map<String, Workbook> workbooktable = new HashMap<String, Workbook>();
	//static ReadConfigPropFile config = new ReadConfigPropFile();
	
	public static Workbook getWorkbook(String path){
		Workbook book=null;
		if(workbooktable.containsKey(path)) {
			book=workbooktable.get(path);
			return book;
		}
		else {
			
		    try {
		    	File file1 =new File(path);
				book=WorkbookFactory.create(file1);
				workbooktable.put(path, book);
		    
				
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    return book;
		}
		
		
		}
	public static List<String> getListOfSheetsInWorkbook(String path1) {
		   List<String> listOfSheets=new ArrayList<String>();
		    Workbook book1=getWorkbook(path1);
		    for(int i=0;i<book1.getNumberOfSheets();i++) {
		    	listOfSheets.add(book1.getSheetName(i));
		    }
		    return listOfSheets;
		 	}
	public static int getNumberOfColumnsInWorksheet(String path,String sheetName) {
		     
		      Workbook workbook=getWorkbook(path);
		      Sheet sheet=workbook.getSheet(sheetName);
		      
		      
		return sheet.getRow(0).getLastCellNum();
		
	}
}

