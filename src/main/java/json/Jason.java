package main.java.json;

import main.java.cards.Library;
import main.java.deckeditor.DeckEditor;

public class Jason {
	public static Library readFile(String jsonname){
		Library library = new Library();
		try{
			
			DeckEditor.println("Started loading from JSON");
		}catch (Exception ignored){}
		return library;
	}
}
