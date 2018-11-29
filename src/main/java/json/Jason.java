package main.java.json;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import main.java.deckeditor.DeckEditor;

public class Jason {
	public static ArrayList<String> readFile(String jsonname){
		ArrayList<String> names = new ArrayList<String>();
		try{
			DeckEditor.println("Started loading from JSON");
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonReader reader = new JsonReader(new FileReader(jsonname));
			JsonObject str = parser.parse(reader).getAsJsonObject();
			Set set = str.keySet();
			Iterator<Entry<String, JsonElement>> map = str.entrySet().iterator();
			while (map.hasNext()) {
				names.add(map.next().getKey().toString());
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return names;
	}
}
