package com.Comcast.crm.generic.FileUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.ClosedFileSystemException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcel(String sheetName, int rowNum, int celNum) throws Exception, IOException {
		FileInputStream fis=new FileInputStream("./testdata/TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		wb.close();
		return data;
	}
	
	public int getRowCount(String sheetName) throws Throwable {
		FileInputStream fis=new FileInputStream("./testdata/TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		return wb.getSheet(sheetName).getLastRowNum();
	}
	
	public void setdataIntoExcel(String sheetName, int rowNum, int cellNum, String data) throws Throwable, IOException {
		FileInputStream fis=new FileInputStream("./testdata/TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).setCellValue(data);
		
		FileOutputStream fos=new FileOutputStream("./testdata/TestScriptData.xlsx");
		wb.write(fos);
		wb.close();
		
	}
}
