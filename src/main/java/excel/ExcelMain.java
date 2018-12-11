package main.java.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import main.java.cards.Card;
import main.java.cards.CardDictonary;
import main.java.deckeditor.DeckEditor;

public class ExcelMain {
	public static void writeExcelFile(CardDictonary library) {
		String columns[] = { "Name", "Text", "ManaCost", "Types", "SubTypes", "Color", "Power", "Toughness", "CMC" };
		Workbook workbook = new HSSFWorkbook();
		Font font = workbook.createFont();
		Sheet sheet = workbook.createSheet();
		font.setBold(true);
		font.setColor(IndexedColors.RED.getIndex());
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(font);
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}
		int rowNum = 1;
		DeckEditor.println("Started building excel workbook");
		for (Card c : library) {
//			Row row = sheet.createRow(rowNum++);
//			row.createCell(0).setCellValue(c.getName());
//			row.createCell(1).setCellValue(c.getDesc());
//			row.createCell(2).setCellValue(c.getManaCost());
//			row.createCell(3).setCellValue(stringBuilder(c.getSupertype()));
//			row.createCell(4).setCellValue(stringBuilder(c.getSubtype()));
//			row.createCell(5).setCellValue(stringBuilder(c.getColoridentity()));
//			row.createCell(6).setCellValue(c.getPower() == null ? "" : c.getPower().toString());
//			row.createCell(7).setCellValue(c.getToughness() == null ? "" : c.getToughness().toString());
//			row.createCell(8).setCellValue(c.getSupertype().contains(SuperType.Land) ? "" : c.getCMC().toString());
//			if (rowNum % 1000 == 0 || rowNum == library.size())
//				DeckEditor.println(Math.round((double) rowNum / (double) library.size() * 100) + "%");
		}
		DeckEditor.println("Finished building excel workbook");
		try {
			DeckEditor.println("Started writing to file");
			FileOutputStream fileOut = new FileOutputStream("cards.xls");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
			DeckEditor.println("Finished writing to file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static String stringBuilder(ArrayList<?> arrayList) {
		StringBuilder builder = new StringBuilder();
		for (Object t : arrayList)
			builder.append(t.toString()).append(" ");
		return builder.toString();
	}
}
