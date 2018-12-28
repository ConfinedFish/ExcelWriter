package cards.type;

import java.util.ArrayList;
import java.util.Arrays;

public enum Color {
	B("BLACK"), C("COLORLESS"), G("GREEN"), R("RED"), U("BLUE"), W("WHITE");
	public static final ArrayList<String> errorColors = new ArrayList<>();
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
	final String name;
	Color(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
