package main.java.excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader{
	public static void readFile(String filename){
		try{
			Workbook book = WorkbookFactory.create(new FileInputStream(filename));
			Sheet sheet = book.getSheetAt(0);
			DataFormatter dataFormatter = new DataFormatter();
			for (Row row : sheet){
				for (Cell cell : row){
				
					
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
}
