package main.java.cards.type;

import java.util.ArrayList;
import java.util.Arrays;

public enum Color {
	U("BLUE"), B("BLACK"), G("GREEN"), R("RED"), W("WHITE"), C("COLORLESS");
	String mame;
	Color(String name) {
		this.mame = name;
	}
	public static ArrayList<String> errorColors = new ArrayList<>();
	public static ArrayList<Color> parseString(String string) {
		ArrayList<String> tokens = new ArrayList<>(Arrays.asList(string.trim().split("\\W+")));
		ArrayList<Color> colors = new ArrayList<>();
		for (String strings : tokens) {
			if (strings != null && strings.length() > 0)
				try {
					colors.add(valueOf(strings.toUpperCase()));
				} catch (Exception e) {
					if (!errorColors.contains(strings))
						errorColors.add(strings);
				}
		}
		return colors;
	}
}
