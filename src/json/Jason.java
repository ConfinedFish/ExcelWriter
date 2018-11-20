package json;

import cards.Library;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class Jason {
	public static Library readFile(String jsonname){
		Library library = new Library();
		try {
			JSONParser parser = new JSONParser();
			Object o = parser.parse(new FileReader(jsonname));
			JSONObject cardObject = (JSONObject) o;
			for (Object obj : cardObject.keys())
				cardObject.get(obj);
			library = new Library();
			//Card card = new Card(name, desc,manaCost,cmc,type,subType,color,power,toughness);
			//library.addCard(card);
		} catch (Exception e){
			e.printStackTrace();
		}
		return library;
	}

}
