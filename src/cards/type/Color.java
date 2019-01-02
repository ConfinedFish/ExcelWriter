package cards.type;

import java.util.ArrayList;
import java.util.Arrays;

public enum Color{
	B("black"),
	C("colorless"),
	G("green"),
	R("red"),
	U("blue"),
	W("white"),
	L("land"),
	A("artifact"),
	K("Token"),
	M("Emblem");
	public static final ArrayList<String> errorColors = new ArrayList<>();
	final String name;
	
	Color(String name){
		this.name = name;
	}
	//TODO combine parseString and findColorArray if possible
	public static ArrayList<Color> parseString(String string){
		ArrayList<String> tokens = new ArrayList<>(Arrays.asList(string.trim().split("\\W+")));
		ArrayList<Color> colors = new ArrayList<>();
		for (String strings : tokens){
			if (strings != null && strings.length() > 0)
				try{
					colors.add(valueOf(strings.toUpperCase()));
				} catch (Exception e){
					if (!errorColors.contains(strings))
						errorColors.add(strings);
				}
		}
		return colors;
	}
	
	public static ArrayList<Color> findColorArray(String string){
		if (string == null){
			return null;
		}
		ArrayList<Color> colors = new ArrayList<>();
		char[] chars = string.toCharArray();
		for (char c : chars){
			if (c == 'B' || c == 'C' || c == 'G' || c == 'U' || c == 'W' || c == 'L' || c == 'K' || c == 'M')
				colors.add(valueOf(Character.toString(c)));
		}
		return colors;
	}
	
	public String getName(){
		return name;
	}
	
}
