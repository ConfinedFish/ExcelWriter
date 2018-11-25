package excelwriter;

import excel.ExcelReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeckEditor{
	//private static Library library = Jason.readFile("AllCards.json");
    public static void main(String[] args) {
        new DeckEditor().run();
    }
    public void run(){
		//excel.ExcelMain.writeExcelFile(library);
		ExcelReader.readFile("Cards.xlsx");
    }
	public static void print(Object obj){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
        System.out.print(dtf.format(now) + " : " + obj);
    }
    public static void println(Object obj){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now) + " : " + obj);
    }
}
