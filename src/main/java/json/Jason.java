package main.java.json;

import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import main.java.cards.Card;
import main.java.cards.CardDictonary;
import main.java.cards.CardSet;
import main.java.cards.type.Color;
import main.java.cards.type.Format;
import main.java.deckeditor.DeckEditor;

public class Jason extends DeckEditor {
	public static ArrayList<Card> cards;
	public static ArrayList<CardSet> sets;
	public static CardDictonary dictonary;
	public static ArrayList<Object> cardColumnNames, columnNames;
	
	public static ArrayList<Card> readFile(String jsonname) {
		ArrayList<Card> cards = new ArrayList<Card>();
		try {
			println("Started loading from JSON");
			JsonParser parser = new JsonParser();
			JsonReader reader = new JsonReader(new FileReader(jsonname));
			JsonObject str = parser.parse(reader).getAsJsonObject();
			Set<Entry<String, JsonElement>> outerMap = str.entrySet();
			for (Entry<String, JsonElement> entry : outerMap) {
				Card card = new Card();
				JsonObject element = (JsonObject) entry.getValue();
				Set<Entry<String, JsonElement>> map = element.entrySet();
				for (Entry<String, JsonElement> value : map) {
					switch (value.getKey().toString()) {
						case "originalText":
							card.setDesc(value.getValue().toString());
							break;
						case "name":
							card.setName(value.getValue().toString());
							break;
						case "manaCost":
							card.setCmc(value.getValue().getAsDouble());
							break;
						case "legalities":
							//card.setLegalities(value.getValue().);
							break;
						case "printings":
							card.setPrintings(value.getValue().toString());
							break;
						case "rarity":
							card.setName(value.getValue().toString());
							break;
						case "colorIdentity":
							println(value.getValue().getAsJsonArray());
							break;
					}
				}
				cards.add(card);
			}
			println("Finished loading from JSON");
		} catch (Exception e) {
			try {
				println("File not found... downloading");
				InputStream in = new URL("https://mtgjson.com/v4/json/AllCards.json").openStream();
				Files.copy(in, Paths.get(jsonname), StandardCopyOption.REPLACE_EXISTING);
				println("File downloaded");
				readFile(jsonname);
			} catch (Exception e1) {
				e.printStackTrace();
				e1.printStackTrace();
			}
		}
		return cards;
	}
	

	public static void readFileForSets(String jsonname) {
		ArrayList<CardSet> listOfSets = new ArrayList<>();
		dictonary = new CardDictonary();
		try {
			println("Started loading from JSON");
			JsonParser parser = new JsonParser();
			JsonReader reader = new JsonReader(new FileReader(jsonname));
			JsonObject str = parser.parse(reader).getAsJsonObject();
			getColumns(str);
			Set<Entry<String, JsonElement>> outerMap = str.entrySet();
			for (Entry<String, JsonElement> entry : outerMap) {
				CardSet set = new CardSet();
				JsonObject element = (JsonObject) entry.getValue();
				Set<Entry<String, JsonElement>> map = element.entrySet();
				for (Entry<String, JsonElement> value : map) {
					switch (value.getKey().toString()) {
						case "name":
							set.setName(value.getValue().toString().replaceAll("\"", ""));
							break;
						case "code":
							set.setCode(value.getValue().toString().replaceAll("\"", ""));
							break;
						case "totalSetSize":
							set.setTotalSize(value.getValue().getAsInt());
							break;
						case "releaseDate":
							set.setReleaseDate(value.getValue().toString().replaceAll("\"", ""));
							break;
						case "type":
							set.setType(value.getValue().toString().replaceAll("\"", ""));
							break;
						case "block":
							set.setBlock(value.getValue().toString().replaceAll("\"", ""));
							break;
						case "cards":
							getCardColumns(value.getValue().getAsJsonArray());
							set.setCards(readCards(value.getValue().getAsJsonArray()));
							break;
					}
				}
				listOfSets.add(set);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		println("Finished loading from JSON");
		sets = listOfSets;
	}
	
	private static void getColumns(JsonObject jsonObject) {
		columnNames = new ArrayList<>();
		try {
			Set<Entry<String, JsonElement>> outerMap = jsonObject.entrySet();
			for (Entry<String, JsonElement> entry : outerMap) {
				JsonObject element = (JsonObject) entry.getValue();
				Set<Entry<String, JsonElement>> map = element.entrySet();
				for (Entry<String, JsonElement> value : map) {
					String key = value.getKey();
					if (!columnNames.contains(key)) {
						ArrayList<Field> fields = new ArrayList<>(Arrays.asList(CardSet.class.getDeclaredFields()));
						ArrayList<String> fieldNames = new ArrayList<>();
						for (Field f : fields) {
							fieldNames.add(f.getName());
						}
						if (fieldNames.contains(key))
							columnNames.add(key);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void getCardColumns(JsonArray element) {
		cardColumnNames = new ArrayList<>();
		for (JsonElement obj : element) {
			if (obj.isJsonObject()) {
				JsonObject jsonObj = (JsonObject) obj;
				Set<Entry<String, JsonElement>> set = jsonObj.entrySet();
				for (Entry<String, JsonElement> entry : set) {
					String key = entry.getKey();
					if (!cardColumnNames.contains(key)) {
						ArrayList<Field> fields = new ArrayList<>(Arrays.asList(Card.class.getDeclaredFields()));
						ArrayList<String> fieldNames = new ArrayList<>();
						for (Field f : fields) {
							fieldNames.add(f.getName());
						}
						if (fieldNames.contains(key))
							cardColumnNames.add(key);
					}
				}
			}
		}
	}
	private static ArrayList<Card> readCards(JsonArray element) {
		cards = new ArrayList<>();
		for (JsonElement obj : element) {
			Card card = new Card();
			if (obj.isJsonObject()) {
				JsonObject jsonObj = (JsonObject) obj;
				Set<Entry<String, JsonElement>> set = jsonObj.entrySet();
				for (Entry<String, JsonElement> entry : set) {
					switch (entry.getKey().toString()) {
						case "originalText":
							card.setDesc(entry.getValue().toString().replaceAll("\"", ""));
							break;
						case "name":
							card.setName(entry.getValue().toString().replaceAll("\"", ""));
							break;
						case "convertedManaCost":
							card.setCmc(entry.getValue().getAsDouble());
							break;
						case "legalities":
							card.setLegalities(getFormatFromValue(entry.getValue().getAsJsonObject()));
							break;
						case "printings":
							card.setPrintings(entry.getValue().toString().replaceAll("\"", ""));
							break;
						case "rarity":
							card.setRarity(entry.getValue().toString().replaceAll("\"", ""));
							break;
						case "colorIdentity":
							card.setColorIdentity(getColorFromValue(entry.getValue().getAsJsonArray()));
							break;
					}
				}
			}
			dictonary.addCard(card);
			cards.add(card);
		}
		return cards;
	}
	private static ArrayList<Color> getColorFromValue(JsonArray array){
		Iterator<JsonElement> it = array.iterator();
		ArrayList<Color> list = new ArrayList<>();
		while (it.hasNext()) {
			list.addAll(Color.parseString(it.next().toString()));
		}
		return list;
	}
	private static ArrayList<Format> getFormatFromValue(JsonObject jsonObject){
		ArrayList<Format> list = new ArrayList<>();
		Set<Entry<String, JsonElement>> set = jsonObject.entrySet();
		for (Entry<String, JsonElement> entry : set) {
			list.addAll(Format.parseString(entry.getKey()));
		}
		return list;
		
	}
}
