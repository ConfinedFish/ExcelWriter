package main.java.deckeditor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import main.java.cards.type.Color;
import main.java.cards.type.Format;
import main.java.cards.type.Rarity;
import main.java.cards.type.SubType;
import main.java.cards.type.SuperType;
import main.java.gui.GUI;
import main.java.json.Jason;

public class DeckEditor {
	public static void main(String[] args) {
		new DeckEditor().run();
	}
	public void run() {
		Jason.readFileForSets("AllSets.json");
		new GUI();
		println("Removed " + Jason.dictonary.removeDoup() + " duplicates");
		printErrorTypes();
	}
	private void printErrorTypes(){
		ArrayList<String> error = Format.errorFormatTypes;
		if (error.size() != 0) {
			StringBuilder build = new StringBuilder();
			build.append("Error in subtype types: ").append("\n");
			for (String f : error) {
				build.append(f).append("\n");;
			}
			println(build.toString());
		}
		error = Rarity.errorRarity;
		if (error.size() != 0) {
			StringBuilder build = new StringBuilder();
			build.append("Error in subtype types: ").append("\n");
			for (String f : error) {
				build.append(f).append("\n");;
			}
			println(build.toString());
		}
		error = Color.errorColors;
		if (error.size() != 0) {
			StringBuilder build = new StringBuilder();
			build.append("Error in subtype types: ").append("\n");
			for (String f : error) {
				build.append(f).append("\n");
			}
			println(build.toString());
		}
		error = SubType.errorSubTypes;
		if (error.size() != 0) {
			StringBuilder build = new StringBuilder();
			build.append("Error in subtype types: ").append("\n");
			for (String f : error) {
				build.append(f).append("\n");
			}
			println(build.toString());
		}
		error = SuperType.errorTypes;
		if (error.size() != 0) {
			StringBuilder build = new StringBuilder();
			build.append("Error in subtype types: ").append("\n");
			for (String f : error) {
				build.append(f).append("\n");
			}
			println(build.toString());
		}
	}
	public static void print(Object obj) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.print(dtf.format(now) + " : " + obj);
	}
	public static void println(Object obj) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now) + " : " + obj);
	}
}
