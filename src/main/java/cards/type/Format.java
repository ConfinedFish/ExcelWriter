package main.java.cards.type;

import java.util.ArrayList;
import java.util.Arrays;

public enum Format {
	commander,
	mtgo1v1,
	duel,
	legacy,
	modern,
	penny,
	vintage,
	pauper,
	brawl,
	frontier,
	future,
	standard,;
	
	public static ArrayList<String> errorTypes = new ArrayList<String>();
	public static ArrayList<Format> parseString(String string) {
		ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(string.trim().split("\\W+")));
		ArrayList<Format> formats = new ArrayList<>();
		for (String strings : stringArrayList) {
			if (strings != null && strings.length() > 0)
				try {
					if(strings.equals("1v1")) {
						formats.add(mtgo1v1);
					}else {
						formats.add(valueOf(strings));
					}
				} catch (Exception e) {
					if (!errorTypes.contains(strings))
						errorTypes.add(strings);
				}
		}
		return formats;
	}
}
