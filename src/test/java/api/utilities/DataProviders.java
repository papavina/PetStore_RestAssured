package api.utilities;
import java.io.IOException;

import org.testng.annotations.*;

public class DataProviders {
	
	String path = System.getProperty("user.dir") + "\\testdata\\Userdata.xlsx";
	XLUtility xls_file = new XLUtility(path);
	
	
	@DataProvider(name = "data")
	public String[][] getAllData() throws IOException {	
		//getting row and column counts 
		int rowcount = xls_file.getRowCount("Sheet1");
		int clmncount = xls_file.getCellCount("Sheet1", 1);
		System.out.println("rows " + rowcount + " cols "+ clmncount);
		System.out.println("path "+path);
		String[][] apidata = new String[rowcount][clmncount]; 
		for(int i = 0 ; i <rowcount ; i++) {
			for(int j = 0 ; j < clmncount; j++) {
				apidata[i][j] = xls_file.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
		
	}
	
	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException {
		//getting row and column counts 
		int rowcount = xls_file.getRowCount("Sheet1");
		String[] userNameData = new String[rowcount];
		for(int i = 0 ; i < rowcount ; i ++) {
			userNameData[i] = this.xls_file.getCellData("Sheet1", i, 1);
		}
		return userNameData;
	}
}


