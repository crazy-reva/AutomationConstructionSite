package com.automation.util;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readLocationInfoExcel {

	private static final String FILE_PATH = "D:\\eclipse_projects\\constructionsite\\Location_Information.xlsx";
	FileInputStream fis;

	public boolean readInfoFromExcelMethod(String LocationInfo,int rowNumber)
	{
		boolean valueAddToExcelFlag=false;
		if(FILE_PATH!=null)
		{
			try {
				fis = new FileInputStream(FILE_PATH);
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				XSSFSheet sheet = workbook.getSheetAt(0);
				
				if(rowNumber>1)
				{
					XSSFRow row = sheet.getRow(rowNumber-1);
						if(row != null)
						{
							XSSFCell cell = row.getCell(0);
							if(cell!=null)
							{
								System.out.println("cell value : "+ cell.getStringCellValue());
								if(!cell.getStringCellValue().equalsIgnoreCase(LocationInfo))
								{
									valueAddToExcelFlag=true;
								}
								else
								{
									valueAddToExcelFlag=false;
									System.out.println("The location is already present in the excel so skipping writing to xls");
								}
							}
					    }
				}
				if(rowNumber==1)
				{
					valueAddToExcelFlag=true;
				}
	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Please check the input file provided");
		}
		return valueAddToExcelFlag;
	}
}