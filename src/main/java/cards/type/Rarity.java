package cards.type;

public enum Rarity{
	//TODO formalize code in this enum to match other Enums
	Common("C"), Mythic("M"), Rare("R"), Uncommon("U");
	String code;
	
	Rarity(String code){
		this.code = code;
	}
	
	public static Rarity findRarity(String code){
		for (Rarity rarity : Rarity.class.getEnumConstants()){
			if (rarity.getCode().equalsIgnoreCase(code)){
				return rarity;
			}
		}
		return null;
	}
	
	public String getCode(){
		return code;
	}
}
