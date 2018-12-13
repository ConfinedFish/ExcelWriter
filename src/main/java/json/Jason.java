package main.java.json;

import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import main.java.cards.ColNameComparator;
import main.java.cards.type.Color;
import main.java.cards.type.Format;
import main.java.cards.type.SubType;
import main.java.cards.type.SuperType;
import main.java.deckeditor.DeckEditor;

public class Jason extends DeckEditor {
	public static ArrayList<Card> cards;
	public static ArrayList<CardSet> sets;
	public static CardDictonary dictonary;
	public static ArrayList<String> cardColumnNames, columnNames;

	public static void readFileForSets(String jsonname) {
		ArrayList<CardSet> listOfSets = new ArrayList<>();
		dictonary = new CardDictonary();
		try {
			println("Started loading from JSON");
			JsonParser parser = new JsonParser();
			JsonReader reader = new JsonReader(new FileReader(jsonname));
			JsonObject str = parser.parse(reader).getAsJsonObject();
			getColumns();
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
						set.setTotalSetSize(value.getValue().getAsInt());
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
						getCardColumns();
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

	private static void getColumns() {
		columnNames = new ArrayList<>();
		List<String> listOfSorted = Arrays.asList("name", "code", "totalSetSize", "cards", "releaseDate", "type", "block");
		ArrayList<Field> fields = new ArrayList<>(Arrays.asList(CardSet.class.getDeclaredFields()));
		for (Field f : fields) {
			columnNames.add(f.getName());
		}
		Map<String, Integer> mapOfColName = new HashMap<String, Integer>();
		for (int i = 0; i < listOfSorted.size(); i++) {
			String colName = listOfSorted.get(i);
			mapOfColName.put(colName, i);
		}
		Collections.sort(columnNames, new ColNameComparator(mapOfColName));
	}

	private static void getCardColumns() {
		cardColumnNames = new ArrayList<>();
		List<String> listOfSorted = Arrays.asList("name", "convertedManaCost", "manaCost", "originalText", "type",
				"power", "toughness", "supertypes", "subtypes", "rarity", "legalities", "printings", "colorIdentity",
				"artist", "borderColor", "UUID", "isReserved");
		ArrayList<Field> fields = new ArrayList<>(Arrays.asList(Card.class.getDeclaredFields()));
		for (Field f : fields) {
			cardColumnNames.add(f.getName());
		}
		Map<String, Integer> mapOfColName = new HashMap<String, Integer>();
		for (int i = 0; i < listOfSorted.size(); i++) {
			String colName = listOfSorted.get(i);
			mapOfColName.put(colName, i);
		}
		Collections.sort(cardColumnNames, new ColNameComparator(mapOfColName));
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
							card.setOriginalText(entry.getValue().toString().replaceAll("\"", ""));
							break;
						case "name":
							card.setName(entry.getValue().toString().replaceAll("\"", ""));
							break;
						case "artist":
							card.setArtist(entry.getValue().toString().replaceAll("\"", ""));
							break;
						case "borderColor":
							card.setBorderColor(entry.getValue().toString().replaceAll("\"", ""));
							break;
						case "UUID":
							card.setUUID(entry.getValue().toString().replaceAll("\"", ""));
							break;
						case "isReserved":
							card.setIsReserved(entry.getValue().getAsBoolean());
							break;
						case "convertedManaCost":
							card.setConvertedManaCost(entry.getValue().getAsDouble());
							break;
						case "manaCost":
							card.setManaCost(entry.getValue().toString().replaceAll("\"", ""));
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
						case "power":
							card.setPower(entry.getValue().toString().replaceAll("\"", ""));
							break;
						case "subtypes":
							card.setSubtypes(getSubTypesFromValue(entry.getValue().getAsJsonArray()));
							break;
						case "toughness":
							card.setToughness(entry.getValue().toString().replaceAll("\"", ""));
							break;
						case "types":
							card.setSupertypes(getTypesFromValue(entry.getValue().getAsJsonArray()));
							break;
					}
				}
			}
			dictonary.add(card);
			cards.add(card);
		}
		return cards;
	}

	private static ArrayList<SuperType> getTypesFromValue(JsonArray array) {
		Iterator<JsonElement> it = array.iterator();
		ArrayList<SuperType> list = new ArrayList<>();
		while (it.hasNext()) {
			list.addAll(SuperType.parseString(it.next().toString()));
		}
		return list;
	}

	private static ArrayList<SubType> getSubTypesFromValue(JsonArray array) {
		Iterator<JsonElement> it = array.iterator();
		ArrayList<SubType> list = new ArrayList<>();
		while (it.hasNext()) {
			list.addAll(SubType.parseString(it.next().toString()));
		}
		return list;
	}

	private static ArrayList<Color> getColorFromValue(JsonArray array) {
		Iterator<JsonElement> it = array.iterator();
		ArrayList<Color> list = new ArrayList<>();
		while (it.hasNext()) {
			list.addAll(Color.parseString(it.next().toString()));
		}
		return list;
	}

	private static ArrayList<Format> getFormatFromValue(JsonObject jsonObject) {
		ArrayList<Format> list = new ArrayList<>();
		Set<Entry<String, JsonElement>> set = jsonObject.entrySet();
		for (Entry<String, JsonElement> entry : set) {
			list.addAll(Format.parseString(entry.getKey()));
		}
		return list;
	}
}
