package com.automation.util;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class writeLocationInfoToExcel {
	
	XSSFWorkbook workbook = new XSSFWorkbook(); 
	
    XSSFSheet sheet = workbook.createSheet("LocationInfo"); 
    XSSFRow row;
    XSSFCell cell;
	
    public int writeInfoToExcelMethod(String LocationInfo,int rowNumber)
	{
	    
	    
		/*
		 * for(int i=0;i<LocationInfo.size();i++) { int cellnum = 0; XSSFRow row =
		 * sheet.createRow(rownum++); XSSFCell cell=row.createCell(cellnum++);
		 * cell.setCellValue(LocationInfo.get(i)); }
		 */
	    //int cellnum = 0; 
    	int getRowNumber=0;
	    boolean flag=true;
	    if(rowNumber==0)
	    {
		    row = sheet.createRow(0);
		    cell=row.createCell(0);
		    cell.setCellValue(LocationInfo);
		    flag=false;
	    }

	    else if(rowNumber>0)
	    {
		    row = sheet.createRow(rowNumber);
		    cell= row.createCell(0);
		   	cell.setCellValue(LocationInfo); 
		   	flag=true;
		   	getRowNumber=row.getRowNum();
	    }
	   
	    try { 
	    	
	         FileOutputStream out = new FileOutputStream(new File("Location_Information.xlsx")); 
	         workbook.write(out); 
	         out.close(); 
	         if(flag)
	         System.out.println("Location Information.xlsx written successfully on disk."); 
	         else
	         System.out.println("Header added to Location Information.xlsx successfully."); 
        } 
	    
        catch (Exception e) { 
            e.printStackTrace(); 
        }
		return (getRowNumber); 
	    
	}
    
    

}
