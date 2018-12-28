package cards.type;

public enum Rarity {
	Common("C"), Mythic("M"), Rare("R"), Uncommon("");
	String code;
	Rarity(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
}
