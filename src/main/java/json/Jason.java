package json;

import cards.Library;
import excelwriter.DeckEditor;

public class Jason {
	public static Library readFile(String jsonname){
		Library library = new Library();
		try{
			
			DeckEditor.println("Started loading from JSON");
		}catch (Exception ignored){}
		return library;
	}
}
