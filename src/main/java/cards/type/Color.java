package cards.type;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Color {
	A("artifact"),
	B("black"),
	C("colorless"),
	G("green"),
	K("token"),
	L("land"),
	M("Emblem"),
	P("phyrexian"),
	R("red"),
	U("blue"),
	W("white"),
	X("variable");
	public static final ArrayList<String> errorColors = new ArrayList<>();
	final String name;
	
	Color(String name) {
		this.name = name;
	}
	
	public static ArrayList<Color> findColorArray(String string) {
		if (string == null || StringUtils.isBlank(string)) {
			return new ArrayList<>();
		}
		ArrayList<Color> colors = new ArrayList<>();
		if (string.contains("(")) {
			Matcher matcher = Pattern.compile("\\(([^)]+)\\)").matcher(string);
			while (matcher.find()) {
				String s = matcher.group(1);
				if (s.length() > 0) {
					char c = s.charAt(0);
					if (c == 'X' || c == 'R' || c == 'B' || c == 'C' || c == 'G' || c == 'U' || c == 'W' || c == 'L' || c == 'K' || c == 'M')
						try {
							colors.add(valueOf(Character.toString(c)));
						} catch (Exception e) {
							if (!errorColors.contains(Character.toString(c)))
								errorColors.add(Character.toString(c));
						}
					if (Character.isDigit(c)) {
						int num = Integer.parseInt(Character.toString(c));
						if (num == 0)
							colors.add(C);
						else {
							for (int i = 0; i < num; i++) {
								colors.add(C);
							}
						}
					}
				}
				
			}
		} else {
			String[] strings = string.trim().split("\\W+");
			for (String s : strings) {
				if (s.length() > 0) {
					char c = s.charAt(0);
					if (c == 'X' || c == 'R' || c == 'B' || c == 'C' || c == 'G' || c == 'U' || c == 'W' || c == 'L' || c == 'K' || c == 'M' || c == 'A' || c == 'P')
						try {
							colors.add(valueOf(Character.toString(c)));
						} catch (Exception e) {
							if (!errorColors.contains(Character.toString(c)))
								errorColors.add(Character.toString(c));
						}
					if (Character.isDigit(c)) {
						int num = Integer.parseInt(Character.toString(c));
						if (num == 0)
							colors.add(C);
						else {
							for (int i = 0; i < num; i++) {
								colors.add(C);
							}
						}
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
