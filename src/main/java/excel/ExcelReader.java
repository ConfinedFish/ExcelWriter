package excel;

import excelwriter.DeckEditor;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

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
