package main.java.cards.type;

import java.util.ArrayList;
import java.util.Arrays;

public enum Rarity {
	common,
	uncommon,
	rare,
	mythic;
	public static ArrayList<String> errorRarity = new ArrayList<>();
	public static ArrayList<Rarity> parseString(String string) {
		ArrayList<String> tokens = new ArrayList<>(Arrays.asList(string.trim().split("\\W+")));
		ArrayList<Rarity> raritys = new ArrayList<>();
		for (String strings : tokens) {
			if (strings != null && strings.length() > 0)
				try {
					raritys.add(valueOf(strings.toUpperCase()));
				} catch (Exception e) {
					if (!errorRarity.contains(strings))
						errorRarity.add(strings);
				}
		}
		return raritys;
	}
}
