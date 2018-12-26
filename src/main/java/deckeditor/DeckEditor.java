package main.java.deckeditor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import main.java.cards.type.Color;
import main.java.cards.type.Format;
import main.java.cards.type.Rarity;
import main.java.cards.type.SubType;
import main.java.cards.type.SuperType;
import main.java.gui.GUI;
import main.java.json.Jason;

public class DeckEditor{
	public static void main(String[] args) {
		new DeckEditor().run();
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
	private void printErrorTypes() {
		ArrayList<String> error = Format.errorFormatTypes;
		if (error.size() != 0) {
			StringBuilder build = new StringBuilder();
			build.append("Error in subtype types: ").append("\n");
			for (String f : error) {
				build.append(f).append("\n");
			}
			println(build.toString());
		}
		error = Rarity.errorRarity;
		if (error.size() != 0) {
			StringBuilder build = new StringBuilder();
			build.append("Error in subtype types: ").append("\n");
			for (String f : error) {
				build.append(f).append("\n");
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
	public static boolean equalLists(ArrayList<Format> one, ArrayList<Format> two) {
		if (one == null && two == null) {
			return true;
		}
		if ((one == null && two != null) || one != null && two == null || one.size() != two.size()) {
			return false;
		}
		one = new ArrayList<Format>(one);
		two = new ArrayList<Format>(two);
		Collections.sort(one);
		Collections.sort(two);
		return one.equals(two);
	}
	public void run() {
		Jason.readFileForSets("AllSets.json");
		new GUI();
		println("Removed " + Jason.dictonary.removeDoup() + " duplicates");
		printErrorTypes();
	}
}
