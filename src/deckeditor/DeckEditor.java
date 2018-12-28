package deckeditor;

import XML.XMLParse;
import cards.type.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;


public class DeckEditor{
	public static void main(String[] args) {
		new DeckEditor().run();
	}
	public static void print(Object obj) {
		System.out.print(obj);
	}
	public static void println(Object obj) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now) + " : " + obj);
	}
	private void printErrorTypes() {
		findErrorTypes(Format.errorFormatTypes);
		findErrorTypes(Rarity.errorRarity);
		findErrorTypes(Color.errorColors);
		findErrorTypes(SubType.errorSubTypes);
		findErrorTypes(SuperType.errorTypes);
	}
	
	private void findErrorTypes(ArrayList<String> error){
		if (error.size() != 0) {
			StringBuilder build = new StringBuilder();
			build.append("Error parsing data; unknown types: ").append("\n");
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
		if (one == null || two == null || one.size() != two.size()) {
			return false;
		}
		one = new ArrayList<>(one);
		two = new ArrayList<>(two);
		Collections.sort(one);
		Collections.sort(two);
		return one.equals(two);
	}
	private void run() {
//		Jason.readFileForSets("AllSets.json");
//		new GUI();
//		println("Removed " + Jason.dictonary.removeDoup() + " duplicates");
//		printErrorTypes();
		XMLParse xmlParse = new XMLParse("Set.xml");
	}
}
