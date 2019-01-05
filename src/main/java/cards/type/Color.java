package cards.type;

import java.util.ArrayList;
import java.util.Arrays;

public enum Color {
	B("black"),
	C("colorless"),
	G("green"),
	R("red"),
	U("blue"),
	W("white"),
	X("variable"),
	L("land"),
	A("artifact"),
	K("Token"),
	M("Emblem");
	public static final ArrayList<String> errorColors = new ArrayList<>();
	final String name;

	Color(String name) {
		this.name = name;
	}

	//TODO combine parseString and findColorArray if possible
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

	public static ArrayList<Color> findColorArray(String string) {
		if (string == null) {
			return null;
		}
		ArrayList<Color> colors = new ArrayList<>();
		String[] strings = string.trim().split("\\W+");
		for (String s : strings) {
			if (s.length() > 0){
				char c = s.charAt(0);
				if (c == 'X' || c == 'R' || c == 'B' || c == 'C' || c == 'G' || c == 'U' || c == 'W' || c == 'L' || c == 'K' || c == 'M')
					try {
						colors.add(valueOf(Character.toString(c)));
					} catch (Exception e) {
						if (!errorColors.contains(Character.toString(c)))
							errorColors.add(Character.toString(c));
					}
				if (Character.isDigit(c)){
					int num = Integer.parseInt(Character.toString(c));
					for (int i = 0; i < num; i++){
						colors.add(C);
					}
				}
			}

		}
		return colors;
	}

	public String getName() {
		return name;
	}

}
