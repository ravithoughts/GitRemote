package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDriven {
	
	
	public ArrayList<String> getData(String testcasename, String Sheetname) throws IOException
	{
		ArrayList<String> arr = new ArrayList<String>();
	
	FileInputStream fis = new FileInputStream("C:\\Users\\Dhya\\Downloads\\RestAssured_ExcelDrivenData.xlsx");
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	int sheets = workbook.getNumberOfSheets(); // to get number of sheets
	
	for (int i=0; i<sheets; i++)
	{
		String sheetname = workbook.getSheetName(i); // to get sheet name
		if (sheetname.equalsIgnoreCase(Sheetname)) 
		{
			XSSFSheet sheet = workbook.getSheetAt(i); //to access the particular sheet 
			Iterator<Row> rows = sheet.iterator(); // to iterate all rows in a sheet
			Row firstrow = rows.next();
			
			//System.out.println(sheet.getRow(1));
			//System.out.println(sheet.getFirstRowNum());
			//System.out.println(sheet.getLastRowNum());
			
			Iterator<Cell> cells = firstrow.cellIterator(); // to iterate all cells in a row
			int k = 0;
			int column = 0;
			while(cells.hasNext())
			{
				Cell value = cells.next();
				if (value.getStringCellValue().equalsIgnoreCase("TestCases"))
				{
					column = k;
				}
				k++;
			}
			//System.out.println(column);
			
			while(rows.hasNext())
			{
				Row r = rows.next();
				if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename))
						{
					Iterator<Cell> cv = r.cellIterator();
					while(cv.hasNext()) 
					{
						
						Cell c = cv.next();
						if(c.getCellType()==CellType.STRING)
						{
							arr.add(c.getStringCellValue());
						}
						else //convert the numeric cell value into string value
						{
							arr.add(NumberToTextConverter.toText(c.getNumericCellValue()));
														
						}
						
						
						//System.out.println(arr);
					}
						}
			}
		}
		
	}
	workbook.close();
	System.out.println(arr);
	return arr;
	
	}
	
	
}
