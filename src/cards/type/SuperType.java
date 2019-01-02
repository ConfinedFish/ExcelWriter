package cards.type;

import java.util.ArrayList;
import java.util.Arrays;

public enum SuperType{
	Artifact,
	Basic,
	Creature,
	Emblem,
	Enchantment,
	Instant,
	Land,
	Legendary,
	Ongoing,
	Planeswalker,
	Scheme,
	Snow,
	Sorcery,
	Summon,
	Token,
	Tribal,
	World;
	public static final ArrayList<String> errorTypes = new ArrayList<>();
	
	public static ArrayList<SuperType> parseString(String string){
		ArrayList<String> tokens = new ArrayList<>(Arrays.asList(string.trim().split("\\W+")));
		ArrayList<SuperType> superTypes = new ArrayList<>();
		for (String strings : tokens){
			if (strings != null && strings.length() > 0)
				try{
					superTypes.add(valueOf(strings));
				} catch (IllegalArgumentException e){
					if (!errorTypes.contains(strings))
						errorTypes.add(strings);
				}
		}
		return superTypes;
	}
}
