package main.java.deckeditor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import main.java.cards.type.Format;
import main.java.gui.GUI;

public class DeckEditor {
	public static void main(String[] args) {
		new DeckEditor().run();
	}
	public void run() {
		GUI.createAndShowGUI();
		for (String f : Format.errorTypes) {
			System.out.println(f);
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
