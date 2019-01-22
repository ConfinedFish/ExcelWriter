package test.SubType;

public enum NonBasicLandSubType{
	DESERT("Desert"),
	GATE("Gate"),
	LAIR("Lair"),
	LOCUS("Locus"),
	URZAS("Urza's"),
	MINE("Mine"),
	POWER_PLANT("Power-Plant"),
	TOWER("Tower");
	
	String desc;
	NonBasicLandSubType(String desc){
		this.desc = desc;
	}
	
}
