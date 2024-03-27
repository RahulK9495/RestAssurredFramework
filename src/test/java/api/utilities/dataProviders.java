package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class dataProviders {

	@DataProvider(name="Data")
	public String[][] getAlldata () throws IOException
	{
		
		String path=System.getProperty("user.dir")+"//testData//testdata.xlsx";
		
		XLUtil xl=new XLUtil(path);
		
		int rownum= xl.getRowCount("sheet1");
		int colnum=xl.getCellCount("sheet1", 1);
		
		String apidata[][]=new String[rownum][colnum];
		
		for(int i=1;i<=rownum; i++)
		{
			for(int j=0;j<colnum;j++)
			{
				apidata[i-1][j]=xl.getCellData("sheet1", i, j);
			}
		}
		return apidata;
		
	}
	
	@DataProvider(name="UserNames")
	public String [] getusername() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//testdata.xlsx";
		
		XLUtil xl=new XLUtil(path);
		
		int rownum= xl.getRowCount("sheet1");
		
		String username[]=new String [rownum];
		
		for(int i=1;i<=rownum;i++)
		{
			username[i-1]=xl.getCellData("sheet1", i, 1);
		}
		
		return username;
	}
}
