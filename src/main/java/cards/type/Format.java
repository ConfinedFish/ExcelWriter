package cards.type;

import java.util.ArrayList;
import java.util.Arrays;

public enum Format{
	brawl, commander, duel, frontier, future, legacy, modern, mtgo1v1, pauper, penny, standard, vintage,
	;
	public static final ArrayList<String> errorFormatTypes = new ArrayList<>();
	
	public static ArrayList<Format> parseString(String string){
		ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(string.trim().split("\\W+")));
		ArrayList<Format> formats = new ArrayList<>();
		for (String strings : stringArrayList){
			if (strings != null && strings.length() > 0)
				try{
					if (strings.equals("1v1")){
						formats.add(mtgo1v1);
					} else{
						formats.add(valueOf(strings));
					}
				} catch (Exception e){
					if (!errorFormatTypes.contains(strings))
						errorFormatTypes.add(strings);
				}
		}
		return formats;
	}
}
